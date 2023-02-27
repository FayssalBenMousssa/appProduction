package dev.fenix.application.production.treatment.model;

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
@Table(name = "trt__doc_file_attachment")
@NoArgsConstructor
public class documentFileAttachment {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(length = 64)
  private String id;

  private String fileName;
  private String fileType;

  @Lob private byte[] data;

  public documentFileAttachment(String fileName, String fileType, byte[] data , Document document) {
    this.fileName = fileName;
    this.fileType = fileType;
    this.data = data;
    this.document = document;
  }

  @ManyToOne
  @JoinColumn(name = "document_id", referencedColumnName = "id")
  private Document document;

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
