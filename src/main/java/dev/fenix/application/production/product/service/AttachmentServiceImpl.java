package dev.fenix.application.production.product.service;

import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.ProductAttachment;
import dev.fenix.application.production.product.repository.ProductAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements  AttachmentService{

    @Autowired
    ProductAttachmentRepository attachmentRepository;
    @Override
    public ProductAttachment saveAttachment(MultipartFile file, Product product ,String attachmentType) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw  new Exception("file name contains invalid path sequence " + fileName);
            }
            ProductAttachment attachment = new ProductAttachment(fileName,file.getContentType(),file.getBytes()  ,product ,attachmentType );
            attachment.setActive(true);
            return attachmentRepository.save(attachment);
        }catch (Exception e) {
            throw new Exception("Could not save file" + fileName );
        }

    }

    @Override
    public ProductAttachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow( () -> new Exception("File not found with Id :" + fileId));
    }
}
