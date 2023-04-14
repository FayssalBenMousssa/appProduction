package dev.fenix.application.production.product.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "prds__attachment")
@NoArgsConstructor
public class ProductAttachment {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(length = 64)
  private String id;
  private String fileName;
  private String fileType;

  private String attachmentType;
  @Lob private byte[] data;

  public ProductAttachment(String fileName, String fileType, byte[] data , Product product ,String attachmentType) {
    this.fileName = fileName;
    this.fileType = fileType;
    this.data = data;
    this.product = product;
    this.attachmentType = attachmentType;
  }

  @ManyToOne
  @JoinColumn(
      name = "product_id",
      insertable = true,
      updatable = true,
      nullable = true,
      referencedColumnName = "id")
  private Product product;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;



  public JSONObject toJson() {
    JSONObject attachmentJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      attachmentJSON.put("id", this.getId());
      attachmentJSON.put("fileName", this.getFileName());
      attachmentJSON.put("fileType", this.getFileType());
      attachmentJSON.put("attachmentType", this.getAttachmentType());
      attachmentJSON.put("active", this.isActive());
      if (this.getModifyDate() != null) {
        attachmentJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        attachmentJSON.put("createDate", formatter.format(this.getCreateDate()));
      }

    } catch (Exception exception) {

    }

    return attachmentJSON;
  }
}
