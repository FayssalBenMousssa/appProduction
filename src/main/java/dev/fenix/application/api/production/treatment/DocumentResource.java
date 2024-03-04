package dev.fenix.application.api.production.treatment;

import dev.fenix.application.configuration.database.DBContextHolder;
import dev.fenix.application.configuration.database.DBEnum;
import dev.fenix.application.core.model.MetaData;
import dev.fenix.application.core.repository.MetaDataRepository;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.payment.repository.PaymentCustomerRepository;
import dev.fenix.application.production.treatment.model.*;
import dev.fenix.application.production.treatment.repository.DocumentLogRepository;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import dev.fenix.application.production.treatment.service.DocumentService;
import dev.fenix.application.security.model.Setting;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.SettingRepository;
import dev.fenix.application.security.repository.UserRepository;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

@RestController()
@RequestMapping("/api/document")
public class DocumentResource {
    private static final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    private final DocumentRepository documentRepository;


    private final DocumentService documentService;
    private final UserRepository userRepository;

    private final SettingRepository settingRepository;

    private final DocumentLogRepository documentLogRepository;
    private final PaymentCustomerRepository paymentCustomerRepository;
    private final MetaDataRepository metaDataRepository;

    public DocumentResource(DocumentRepository documentRepository, DocumentService documentService, UserRepository userRepository, SettingRepository settingRepository, DocumentLogRepository documentLogRepository, MetaDataRepository metaDataRepository, PaymentCustomerRepository paymentCustomerRepository) {
        this.documentRepository = documentRepository;
        this.documentService = documentService;
        this.userRepository = userRepository;
        this.settingRepository = settingRepository;
        this.documentLogRepository = documentLogRepository;
        this.metaDataRepository = metaDataRepository;
        this.paymentCustomerRepository = paymentCustomerRepository;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() {
        return JSONObject.quote("Api :" + this.getClass().getSimpleName());
    }

    @RequestMapping(
            value = "/index",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index(
            HttpServletRequest request,
            @RequestParam(required = false) Long type,
            @RequestParam(required = false) Long category,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "200") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(defaultValue = "false") boolean toInvoice,
            @RequestParam(required = false) String[] query)
            throws InterruptedException, UnsupportedEncodingException {


        long startTime = System.nanoTime();
        JSONArray jArray = new JSONArray();
        List<Document> documents = documentService.getAllDocuments(page, size, sort, query, type, category, toInvoice, this.getCurrentUser());
        long queryTime = System.nanoTime();
        //log.info("queryTime : " + String.valueOf((queryTime - startTime) / 1000000));
        for (Document document : documents) {
            jArray.put(document.toSmallJson());
        }
        JSONObject response = new JSONObject();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        //log.info("durationTime : " + String.valueOf((duration) / 1000000));

        try {
            response.put("results", jArray);
            response.put("total_type", documentService.getCount());
            response.put("count", jArray.length());
            response.put("total", documentService.getCountAll());
            response.put("duration", (endTime - startTime));
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(
            value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
            throws NotFoundException, JSONException, ParseException {
        //log.trace("DocumentResource.get method accessed");
        Document document =
                documentRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Document  not found"));






        return new ResponseEntity<>(document.toJson().toString(), HttpStatus.OK);
    }

    public void updateUserSequence(Document savedDocument) {
        User user = this.getCurrentUser();
        if (user.getSettings()!= null && user.getSettings().size() > 0) {
            String[] parts = savedDocument.getCode().split("\\.");
            String lastPart = parts[2];
            int sequenceNumber = Integer.parseInt(lastPart);
           // document"Updating user sequence for document with code: " + savedDocument.getCode());
            for (Setting setting : user.getSettings()) {
                String settingName = setting.getName();
               // document"Checking setting with name: " + settingName);
                if (Objects.equals(savedDocument.getType().getCode(), "bon_livraison") && settingName.equals("sequence_bon_livraison")) {
                   // document"Updating sequence_bon_livraison setting with last part: " + lastPart);
                    setting.setValue(String.valueOf(sequenceNumber));
                } else if (Objects.equals(savedDocument.getType().getCode(), "bon_transfert") && settingName.equals("sequence_bon_transfert")) {
                   // document"Updating sequence_bon_transfert setting with last part: " + lastPart);
                    setting.setValue(String.valueOf(sequenceNumber));
                } else if (Objects.equals(savedDocument.getType().getCode(), "prepa_bon_charge") && settingName.equals("sequence_prepa_bon_charge")) {
                   // document"Updating sequence_prepa_bon_charge setting with last part: " + lastPart);
                    setting.setValue(String.valueOf(sequenceNumber));
                }
            }
           // document"Saving updated settings for user: " + user.getId());
            settingRepository.saveAll(user.getSettings());
           // document"Settings saved successfully");
        }
    }




    public void runSyncDocument(Document savedDocument) {
        try {
           // document"runSync method called with document ID " + savedDocument.getId() + " and code " + savedDocument.getCode());
            if (!savedDocument.getIsSynchronised()) {
               // document"Document is not yet synchronized");
               // document"Sending request to other server");
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                Map<String, Object> data = new HashMap<>();
                DocumentService documentService = new DocumentService();
                String userName = savedDocument.getLogs().get(0).getUserName();
                User user = userRepository.findOneByUserName(userName);
                List<PaymentCustomer> payments = this.paymentCustomerRepository.findByCodeContainsAndActiveTrue(savedDocument.getCode());
                data.put("data", documentService.toOldJSON(savedDocument, user, payments));
                HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(data, headers);
               // http://192.168.3.3:75/
                // http://105.155.251.128:75/
                ResponseEntity<String> response = restTemplate.postForEntity("http://105.155.251.128/api/azure", requestEntity, String.class);
                if (!response.getStatusCode().is2xxSuccessful()) {
                    log.error("Error response from other server: " + response.getStatusCode());
                    return;
                }
                String responseBody = response.getBody();
               // document"Received response from other server: " + responseBody);
                savedDocument.setIsSynchronised(true);
                documentRepository.save(savedDocument);
               // document"Document with ID " + savedDocument.getId() + " and code " + savedDocument.getCode() + " updated to isSynchronised = true");
            } else {
               // document"Document with ID " + savedDocument.getId() + " and code " + savedDocument.getCode() + " already has isSynchronised = true");
            }
        } catch (Exception e) {
            log.error("Error sending request to other server: " + e.getMessage());
        }
    }


    @RequestMapping(
            value = "/business_folder/get/{value}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBusinessFolder(HttpServletRequest request, @PathVariable String value)
            throws NotFoundException {
        //log.trace("DocumentResource.get method accessed");
        String code = "code_affaire";
        List<Document> documents = documentRepository.findByActiveTrueAndType_MetaData_CodeAndDocumentDataValues_Value(code, value);

        JSONArray jArray = new JSONArray();
        for (Document document : documents) {
            jArray.put(document.toSmallJson());
        }
        return new ResponseEntity<>(jArray.toString(), HttpStatus.OK);
    }


    @RequestMapping(
            value = "/related/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRelated(HttpServletRequest request, @PathVariable Long id)
            throws NotFoundException {
        //log.trace("DocumentResource.get method accessed");
        Document document =
                documentRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Document  not found"));

        Set<Document> relatedDocs = new HashSet<Document>();
        for (Document relatedDoc : document.getRelated()) {
            // log.error(relatedDoc.getName());
            Document newRelatedDoc = documentRepository.findById(relatedDoc.getId()).orElseThrow(() -> new NotFoundException("Document  not found"));
            relatedDocs.add(newRelatedDoc);

        }
        document.setRelated(relatedDocs);
        // Pause for 5 seconds before returning the response
       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return new ResponseEntity<>(document.toJsonRelated().toString(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/get/logs/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTrace(HttpServletRequest request, @PathVariable Long id)
            throws NotFoundException {
        List<DocumentLog> documentLogs = documentLogRepository.findByDocumentId(id);
        JSONArray jArray = new JSONArray();
        for (DocumentLog documentLog : documentLogs) {
            jArray.put(documentLog.toJson());
        }
        JSONObject response = new JSONObject();
        try {
            response.put("results", jArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> save(@Valid @RequestBody Document document, HttpServletRequest request) {
        try {

            if (document.getCode() == null) {
                document.setCode(this.getNewCode(document.getType()));
                document.setName(document.getType().getName() + " " + this.getNewCode(document.getType()));
            }

            document.setActive(true);
            document.setIsSynchronised(false);
            document.setLogs(getDocumentLogs(document, Action.ADD, null));
            this.setCodeBuz(document);
            Document savedDocument = documentRepository.save(document);
            if(DBContextHolder.getCurrentDb() == DBEnum.CANELIA){
                runSyncDocument(savedDocument);
                updateUserSequence(document);
            }
            this.changeStatusRelated(document);
            return new ResponseEntity<>(savedDocument.toJson().toString(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    public Document setCodeBuz(Document document) {

        String targetCode = "code_affaire";
        if (document.getType().getMetaData() != null) {
            for (MetaData metaDta : document.getType().getMetaData()) {
                if (metaDta.getCode().equals(targetCode)) {
                   // document"find metaDta! " + metaDta.getCode());
                    DocumentDataValue documentDataValue = new DocumentDataValue();
                    documentDataValue.setMetaData(metaDta);
                    documentDataValue.setActive(true);
                    documentDataValue.setValue(document.getCode());
                    List<DocumentDataValue> metaDataValue = document.getDocumentDataValues();
                    if (metaDataValue == null) {
                        metaDataValue = new ArrayList<>();
                    }
                    for (DocumentDataValue dataValue : metaDataValue) {
                        MetaData metaData = metaDataRepository.getOne(dataValue.getMetaData().getId());
                       // document"metaData : " + metaDta.getCode());
                        if (metaData.getCode().equals(targetCode)) {
                           // document"CodeBuz is exist ! " + metaDta.getCode());
                            document.setDocumentDataValues(metaDataValue);
                            return document;
                        }
                    }
                    metaDataValue.add(documentDataValue);
                    document.setDocumentDataValues(metaDataValue);

                    break; // No need to continue searching after finding the target code
                }
            }
        }
        return document;
    }


    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(
            @Valid @RequestBody Document document, HttpServletRequest request) {
        //log.trace("DocumentResource.update method accessed");
        try {
            document.setLogs(getDocumentLogs(document, Action.EDIT, null));
            document.setActive(true);
            if (document.getRelated() != null) {
                for (Document documentRelated : document.getRelated()) {
                    if (documentRelated.getCode() == null) {
                        documentRelated.setCode(this.getNewCode(documentRelated.getType()));
                    }
                }
            }
            document.setIsSynchronised(false);
            Document updatedDocument = documentRepository.save(document);

            return new ResponseEntity<>(updatedDocument.toJson().toString(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        //log.trace("DocumentResource.delete method accessed");
        Document document = documentRepository.getOne(id);
        try {

            document.setLogs(getDocumentLogs(document, Action.DELETE, null));
            document.setActive(false);
            Document savedDocument = documentRepository.save(document);
            //// activation of documents
            this.activateStatusRelated(savedDocument);
            return ResponseEntity.ok(savedDocument.toJson().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(
            value = "/unique/{code}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> isUnique(HttpServletRequest request, @PathVariable String code) throws NotFoundException {
        //log.trace("DocumentResource.isUnique method accessed");
        int count = documentRepository.countByActiveTrueAndCode(code);
        if (count > 0) {
            return new ResponseEntity<>(String.valueOf(false), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.valueOf(true), HttpStatus.OK);
    }

    private String getNewCode(Type type) {
        int count = documentRepository.countByType(type);
        return type.getAbbreviation() + "." + String.format("%07d", count + 1);
    }

    private List<DocumentLog> getDocumentLogs(Document document, Action action, Document relatedTo) {
        List<DocumentLog> logs = new ArrayList();
        if (action == Action.DELETE || action == Action.EDIT) {
            Document oldDocument = documentRepository.getOne(document.getId());
            logs = oldDocument.getLogs();
            String messages = document.getDifferenceJson(oldDocument).toString();
            DocumentLog documentLog = new DocumentLog(action, document, messages, this.getCurrentUser().getUserName());
            logs.add(documentLog);
        } else if (action == Action.ADD) {
            DocumentLog documentLog = new DocumentLog(action, document, null, this.getCurrentUser().getUserName());
            logs.add(documentLog);
        } else if (action == Action.REACTIVATION) {
            DocumentLog documentLog = new DocumentLog(action, document, "Reactivation by " + relatedTo.getCode(), this.getCurrentUser().getUserName());
            logs.add(documentLog);
        }
        return logs;
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findOneByUserName(username);
        return user;
    }


    public void changeStatusRelated(Document document) {
        if (document.getRelated() != null && document.getRelated().size() > 0) {
            document.getRelated().forEach(doc -> {
                Document updateDoc = documentRepository.getOne(doc.getId());
                updateDoc.setStatus(doc.getStatus());
                updateDoc.setLogs(getDocumentLogs(document, Action.DELETE, null));
                documentRepository.save(updateDoc);
            });
        }

    }

    public void activateStatusRelated(Document document) {
        if (document.getRelated().size() > 0) {
            document.getRelated().forEach(doc -> {
                Document updateDoc = documentRepository.getOne(doc.getId());
                updateDoc.setStatus(Status.INITIATED);
                updateDoc.setLogs(getDocumentLogs(document, Action.REACTIVATION, document));
                documentRepository.save(updateDoc);
            });
        }
    }
}
