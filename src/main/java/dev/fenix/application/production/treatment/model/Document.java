package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.*;
import dev.fenix.application.business.model.Company;
import lombok.*;
import org.hibernate.annotations.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
@Table(name = "trt__doc" , indexes = {
        @Index(columnList = "code", name = "code_idx")
})


@ToString
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Document {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @Column(length = 12)
  private String code;

  @NotNull(message = "Please enter the type")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "type_id", referencedColumnName = "id")
  private Type type;



  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(cascade = {CascadeType.ALL} , orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "document_id") // parent's foreign key
  @JsonManagedReference(value="document-log")
  private List<DocumentLog> logs = new ArrayList<>();


  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(cascade = {CascadeType.ALL} , orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "document_id") // parent's foreign key
  @JsonManagedReference(value="document-product")
  private List<DocumentProduct> documentProduct = new ArrayList<>();




  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "document_id") // parent's foreign key
  @JsonManagedReference(value="document-data-values")
  private List<DocumentDataValue> documentDataValues = new ArrayList<>();

  @NotNull(message = "Please enter the date")
  @Column(name = "doc_date" , columnDefinition =" date default '2000-01-01'")
  private Date date;

  @NotNull(message = "Please enter the active")
  private Boolean active;

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
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "source_id", referencedColumnName = "id")
  private Company source;


  @NotNull(message = "Please enter the destination")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "destination_id", referencedColumnName = "id")
  private Company destination;

  @JsonBackReference(value = "related-docs")
  @ManyToMany(cascade={CascadeType.ALL})
  @JoinTable(name="trt__doc_related",
          joinColumns={@JoinColumn(name="document_id")},
          inverseJoinColumns={@JoinColumn(name="related_to_id")})
  private Set<Document> related = new HashSet<Document>();

  @JsonIgnore
  @JsonManagedReference(value = "related-docs")
  @ManyToMany(mappedBy="related")
  private Set<Document> relatedTo = new HashSet<Document>();




  public JSONObject toJson() {
    JSONObject documentJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      documentJSON.put("id", this.getId());
      documentJSON.put("name", this.getName());
      documentJSON.put("code", this.getCode());
      documentJSON.put("active", this.getActive());
      documentJSON.put("status", this.getStatus());
      documentJSON.put("type", this.getType().toJson());
      documentJSON.put("source", this.getSource().toJson());
      documentJSON.put("destination", this.getDestination().toJson());

      if (this.getRelated() != null) {
        JSONArray relatedDocumentsList = new JSONArray();
        for (Document  document : this.getRelated()) {
          if (document != null && document.getActive()) {
            relatedDocumentsList.put(document.toSmallJson());
          }
        }
        documentJSON.put("related", relatedDocumentsList);
      }

     if (this.getRelatedTo() != null) {
        JSONArray relatedToDocumentsList = new JSONArray();
        for (Document  document : this.getRelatedTo()) {
          if (document != null && document.getActive()) {
            relatedToDocumentsList.put(document.toSmallJson());
          }
        }
        documentJSON.put("relatedTo",  relatedToDocumentsList);
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
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      documentJSON.put("id", this.getId());
      documentJSON.put("name", this.getName());
      documentJSON.put("code", this.getCode());
      documentJSON.put("active", this.getActive());
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
}