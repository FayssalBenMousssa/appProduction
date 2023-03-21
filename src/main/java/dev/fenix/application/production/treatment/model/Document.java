package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.*;
import dev.fenix.application.business.model.Company;
import lombok.*;
import org.hibernate.annotations.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Getter
@Setter
@Table(
    name = "trt__doc",
    indexes = {@Index(columnList = "code", name = "code_idx")})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Document {

  private static final Logger log = LoggerFactory.getLogger(Document.class);

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @Column(length = 12)
  private String code;

  @NotNull(message = "Please enter the type")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "type_id", referencedColumnName = "id")
  private Type type;

  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(
      cascade = {CascadeType.ALL},
      orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "document_id") // parent's foreign key
  @JsonManagedReference(value = "document-log")
  private List<DocumentLog> logs = new ArrayList<>();

  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(
      cascade = {CascadeType.ALL},
      orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "document_id") // parent's foreign key
  @JsonManagedReference(value = "document-product")
  private List<DocumentProduct> documentProduct = new ArrayList<>();

  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(
      cascade = {CascadeType.ALL},
      orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "document_id") // parent's foreign key
  @JsonManagedReference(value = "document-data-values")
  private List<DocumentDataValue> documentDataValues = new ArrayList<>();

  @NotNull(message = "Please enter the date")
  @Column(name = "doc_date", columnDefinition = " date default '2000-01-01'")
  @Temporal(TemporalType.DATE)
  private Date date;

  @NotNull(message = "Please enter the active")
  private boolean active;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  @Enumerated(EnumType.STRING)
  @Column(length = 30)
  private Status status;

  @NotNull(message = "Please enter the source")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "source_id", referencedColumnName = "id")
  private Company source;

  @NotNull(message = "Please enter the destination")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "destination_id", referencedColumnName = "id")
  private Company destination;

  @JsonBackReference(value = "related-docs")
  @ManyToMany(cascade = {CascadeType.MERGE})
  @JoinTable(
      name = "trt__doc_related",
      joinColumns = {@JoinColumn(name = "document_id")},
      inverseJoinColumns = {@JoinColumn(name = "related_to_id")})
  private Set<Document> related = new HashSet<Document>();

  @JsonIgnore
  @JsonManagedReference(value = "related-docs")
  @ManyToMany(mappedBy = "related")
  private Set<Document> relatedTo = new HashSet<Document>();

  @Formula("CONCAT_WS( '_', type_id, status )")
  private String access;

  @PrePersist
  @PreUpdate
  @PreRemove
  private void beforeAnyUpdate() {

    log.info("before Any Update Document");
  }

  public Double totalDocument() {
    Double total = 0d;
    for (DocumentProduct productDoc : this.getDocumentProduct()) {
      total += productDoc.totalProduct();
    }
    return total;
  }

  public List<String> getDifferent(Document newDocument) {
    List<String> difference = new ArrayList<String>();
    if (!this.name.equals(newDocument.getName()))
      difference.add("name : " + this.name + " <> " + newDocument.getName());
    if (!this.code.equals(newDocument.getCode()))
      difference.add("code : " + this.code + " <> " + newDocument.getCode());
    if (this.getStatus() != newDocument.getStatus())
      difference.add("status : " + this.getStatus() + " <> " + newDocument.getStatus());

    /// TODO

    return difference;
  }

  public JSONObject toJson() {
    JSONObject documentJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
    try {
      documentJSON.put("id", this.getId());
      documentJSON.put("name", this.getName());
      documentJSON.put("code", this.getCode());
      documentJSON.put("active", this.isActive());
      documentJSON.put("status", this.getStatus());
      documentJSON.put("type", this.getType().toJson());
      documentJSON.put("source", this.getSource().toJson());
      documentJSON.put("destination", this.getDestination().toJson());

      if (this.getRelated() != null) {
        JSONArray relatedDocumentsList = new JSONArray();
        for (Document document : this.getRelated()) {
          if (document != null && document.isActive()) {
            relatedDocumentsList.put(document.toSmallJson());
          }
        }
        documentJSON.put("related", relatedDocumentsList);
      }

      if (this.getRelatedTo() != null) {
        JSONArray relatedToDocumentsList = new JSONArray();
        for (Document document : this.getRelatedTo()) {
          if (document != null && document.isActive()) {
            relatedToDocumentsList.put(document.toSmallJson());
          }
        }
        documentJSON.put("relatedTo", relatedToDocumentsList);
      }

      if (this.getDocumentProduct() != null) {
        JSONArray documentProductsList = new JSONArray();
        for (DocumentProduct documentProduct : this.getDocumentProduct()) {
          if (documentProduct != null && documentProduct.isActive()) {
            documentProductsList.put(documentProduct.toJson());
          }
        }
        documentJSON.put("documentProduct", documentProductsList);
      }

      if (this.getDocumentDataValues() != null) {
        JSONArray documentDataValues = new JSONArray();
        for (DocumentDataValue documentDataValue : this.getDocumentDataValues()) {
          if (documentDataValue != null && documentDataValue.isActive()) {
            documentDataValues.put(documentDataValue.toJson());
          }
        }
        documentJSON.put("documentDataValues", documentDataValues);
      }

      if (this.getDate() != null) {
        documentJSON.put("date", formatter.format(this.getDate()));
      }

      if (this.getModifyDate() != null) {
        documentJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        documentJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return documentJSON;
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public JSONObject toSmallJson() {
    JSONObject documentJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      documentJSON.put("id", this.getId());
      documentJSON.put("name", this.getName());
      documentJSON.put("code", this.getCode());
      documentJSON.put("active", this.isActive());
      documentJSON.put("status", this.getStatus());

      documentJSON.put("type", this.getType().toJson());
      documentJSON.put("source", this.getSource().toJson());
      documentJSON.put("destination", this.getDestination().toJson());
      if (this.getDocumentProduct() != null) {
        JSONArray documentProductsList = new JSONArray();
        for (DocumentProduct documentProduct : this.getDocumentProduct()) {
          if (documentProduct != null && documentProduct.isActive()) {
            documentProductsList.put(documentProduct.toJson());
          }
        }
        documentJSON.put("documentProduct", documentProductsList);
      }

      if (this.getDocumentDataValues() != null) {
        JSONArray documentDataValues = new JSONArray();
        for (DocumentDataValue documentDataValue : this.getDocumentDataValues()) {
          if (documentDataValue != null && documentDataValue.isActive()) {
            documentDataValues.put(documentDataValue.toJson());
          }
        }
        documentJSON.put("documentDataValues", documentDataValues);
      }

      if (this.getDate() != null) {
        documentJSON.put("date", formatter.format(this.getDate()));
      }

      if (this.getModifyDate() != null) {
        documentJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        documentJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return documentJSON;
  }

  public String getResumeInfo() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    return "\n==========================="
        + "\nID : "
        + this.getId()
        + "\nCode : "
        + this.code
        + "\nSource : "
        + this.getSource().getSocialReason()
        + "\nDestination :"
        + this.getDestination().getSocialReason()
        + "\nStatus : "
        + this.getStatus().name()
        + "\nDate : "
        + formatter.format(this.getDate())
        + "\nmodifyDate : "
        + formatter.format(this.getModifyDate())
        + "\ncreateDate : "
        + formatter.format(this.getCreateDate());
  }

  public JSONObject getDifferenceJson(Document newDocument) {

    JSONObject differenceJSON = new JSONObject();
    try {
      if (this.getDate().compareTo(newDocument.getDate()) != 0) {
        differenceJSON.put("date", this.getDate() + "|" + newDocument.getDate());
      }
      if (!this.name.equals(newDocument.getName()))
        differenceJSON.put("name", this.name + "|" + newDocument.getName());
      if (!this.code.equals(newDocument.getCode()))
        differenceJSON.put("code", this.code + "|" + newDocument.getCode());
      if (this.getStatus() != newDocument.getStatus())
        differenceJSON.put("status", this.getStatus() + "|" + newDocument.getStatus());
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return differenceJSON;
  }
}
