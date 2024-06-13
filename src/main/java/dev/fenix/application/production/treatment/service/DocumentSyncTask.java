package dev.fenix.application.production.treatment.service;

import dev.fenix.application.GlobalVariables;
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

    @Scheduled(initialDelay = 10000, fixedDelay = 30000)
    @PostConstruct
    public void run() {
        DBContextHolder.setCurrentDb(DBEnum.CANELIA);

        if(DBContextHolder.getCurrentDb() == DBEnum.CANELIA){
            List<Document> nonSynchronisedBon_livraison = documentRepository.findByActiveTrueAndIsSynchronisedFalseAndType_CodeLike("bon_livraison");
            List<Document> nonSynchronisedPrepa_bon_charge = documentRepository.findByActiveTrueAndIsSynchronisedFalseAndType_CodeLike("prepa_bon_charge");

            List<Document>  nonSynchronisedDocs = new ArrayList<>();
            nonSynchronisedDocs.addAll(nonSynchronisedBon_livraison);
            nonSynchronisedDocs.addAll(nonSynchronisedPrepa_bon_charge);
            runSyncDocument(nonSynchronisedDocs);
        }
    }


    public void runSyncDocument(List<Document> nonSynchronisedDocs) {
       // log.info("runSyncDocument size " +  nonSynchronisedDocs.size());
        nonSynchronisedDocs.forEach(document -> {
            try {

                if (!document.getIsSynchronised()) {
                    log.info("Synchronised & Sending document code {} id {} request to other server" , document.getCode() , document.getId() );
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

                    ResponseEntity<String> response = restTemplate.postForEntity(GlobalVariables.END_POINT, requestEntity, String.class);
                    if (!response.getStatusCode().is2xxSuccessful()) {
                        log.error("Error response from other server: " + response.getStatusCode());
                        return;
                    }
                    String responseBody = response.getBody();
                    log.info("Received response from other server: " + responseBody);
                    document.setIsSynchronised(true);
                    documentRepository.save(document);
                    log.info("Document with ID " + document.getId() + " and code " + document.getCode() + " updated to isSynchronised = true");
                } else {
                    log.info("Document with ID " + document.getId() + " and code " + document.getCode() + " already has isSynchronised = true");
                }
            } catch (Exception e) {
                log.error("document code {} id {} Error sending request to other server : {} " ,document.getCode() , document.getId(), e.getMessage());
            }
        });

    }


}
