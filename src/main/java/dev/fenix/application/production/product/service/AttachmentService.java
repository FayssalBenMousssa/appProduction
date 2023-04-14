package dev.fenix.application.production.product.service;

import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.ProductAttachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService  {

    ProductAttachment saveAttachment(MultipartFile file, Product product , String attachmentType) throws Exception;

    ProductAttachment getAttachment(String fileId) throws Exception;
}
