package dev.fenix.application.production.treatment.service;

import dev.fenix.application.configuration.database.DBContextHolder;
import dev.fenix.application.configuration.database.DBEnum;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.payment.repository.PaymentCustomerRepository;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DocumentSyncTask {

    private static final Logger log = LoggerFactory.getLogger(DocumentSyncTask.class);

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private PaymentCustomerRepository paymentCustomerRepository;
    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedDelay = 30000)
    @PostConstruct
    public void run() {
        DBContextHolder.setCurrentDb(DBEnum.CANELIA);
        // document"run DocumentSyncTask");
        if(DBContextHolder.getCurrentDb() == DBEnum.CANELIA){
            List<Document> nonSynchronisedBon_livraison = documentRepository.findByActiveTrueAndIsSynchronisedFalseAndType_CodeLike("bon_livraison");
            List<Document> nonSynchronisedPrepa_bon_charge = documentRepository.findByActiveTrueAndIsSynchronisedFalseAndType_CodeLike("prepa_bon_charge");

            List<Document>  nonSynchronisedDocs = new ArrayList<>();
            nonSynchronisedDocs.addAll(nonSynchronisedBon_livraison);
            nonSynchronisedDocs.addAll(nonSynchronisedPrepa_bon_charge);
            runSyncDocument(nonSynchronisedDocs);
        }

        // Your code here
    }


    public void runSyncDocument(List<Document> nonSynchronisedDocs) {

        nonSynchronisedDocs.forEach(document -> {

            try {
               // document"runSync method called with document ID " + document.getId() + " and code " + document.getCode());
                if (!document.getIsSynchronised()) {
                   // document"Document is not yet synchronized");
                   // document"Sending request to other server");
                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    Map<String, Object> data = new HashMap<>();

                    DocumentService documentService = new DocumentService();
                    String userName = document.getLogs().get(0).getUserName();
                    User user = userRepository.findOneByUserName(userName);

                    List<PaymentCustomer> payments = this.paymentCustomerRepository.findByCodeContainsAndActiveTrue(document.getCode());
                    data.put("data", documentService.toOldJSON(document, user, payments));
                    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(data, headers);

                    ResponseEntity<String> response = restTemplate.postForEntity("http://105.155.251.128/api/azure", requestEntity, String.class);
                    if (!response.getStatusCode().is2xxSuccessful()) {
                        log.error("Error response from other server: " + response.getStatusCode());
                        return;
                    }
                    String responseBody = response.getBody();
                   // document"Received response from other server: " + responseBody);
                    document.setIsSynchronised(true);
                    documentRepository.save(document);
                   // document"Document with ID " + document.getId() + " and code " + document.getCode() + " updated to isSynchronised = true");
                } else {
                   // document"Document with ID " + document.getId() + " and code " + document.getCode() + " already has isSynchronised = true");
                }
            } catch (Exception e) {
                log.error("Error sending request to other server: " + e.getMessage());
            }
        });

    }


}
