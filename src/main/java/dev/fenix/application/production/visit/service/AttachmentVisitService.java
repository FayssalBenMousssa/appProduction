package dev.fenix.application.production.visit.service;


import dev.fenix.application.production.visit.model.Visit;
import dev.fenix.application.production.visit.model.VisitAttachment;
import dev.fenix.application.production.visit.repository.VisitAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class AttachmentVisitService {

    @Autowired
    VisitAttachmentRepository attachmentRepository;


    public VisitAttachment saveAttachment(MultipartFile file, Visit visit, String attachmentType) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new Exception("File name contains invalid path sequence: " + fileName);
            }
            VisitAttachment attachment = new VisitAttachment(fileName, file.getContentType(), file.getBytes(), visit, attachmentType);
            attachment.setActive(true);
            return attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Could not save file: " + fileName);
        }
    }


    public VisitAttachment getAttachment(String fileId) throws Exception {
        return null;
    }


}
