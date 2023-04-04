package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.fenix.application.production.product.model.Formula;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.SiUnit;
import lombok.*;
import org.hibernate.annotations.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "trt__doc_product")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentProduct {


    private static final Logger log = LoggerFactory.getLogger(DocumentProduct.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private BatchNumber batch;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    @JsonBackReference(value = "document-product")
    private Document document;


    private Double tax;
    private Double price;
    private Double discount;


    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "si_unit_id", referencedColumnName = "id")
    private SiUnit siUnit;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "formula_id", referencedColumnName = "id")
    private Formula formula;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean active;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    private float quantity;


    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id" , updatable = false)
    // @JsonBackReference(value = "document-product")

    private DocumentProduct parent;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.ALL})  // javax.persistent.CascadeType
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "parent_id") // parent's foreign key
    private List<DocumentProduct> children = new ArrayList<>();


    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate() {
     /*   //log.info( "before Any Update DocumentProduct" + (parent != null ? parent.getDocument().getId()  : " No id") );
        if (parent != null)
        this.setDocument(parent.getDocument());*/
    }


    public Double totalProduct(){
       ///  (trt__doc_product.quantity * trt__doc_product.price) * (1 - trt__doc_product.discount / 100)) * (1 + trt__doc_product.tax / 100)
        return (price * quantity) * (1 - discount / 100) * (1+tax / 100);
    }

    public JSONObject toJson() {
        JSONObject documentProductJSON = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            documentProductJSON.put("id", this.getId());
            documentProductJSON.put("quantity", this.getQuantity());


          //  if(this.getSiUnit() != null)
            documentProductJSON.put("siUnit", this.getSiUnit().toJson());


            if (this.getPrice() != null) {
                documentProductJSON.put("price", this.getPrice());
            }

            if (this.getBatch() != null) {
                documentProductJSON.put("batch", this.getBatch().toJson());
            }

            if (this.getPrice() != null) {
                documentProductJSON.put("tax", this.getTax());
            }

            if (this.getPrice() != null) {
                documentProductJSON.put("discount", this.getDiscount());
            }

            if (this.getModifyDate() != null) {
                documentProductJSON.put("modifyDate", formatter.format(this.getModifyDate()));
            }
            if (this.getCreateDate() != null) {
                documentProductJSON.put("createDate", formatter.format(this.getCreateDate()));
            }
            documentProductJSON.put("active", this.isActive());
            if (this.getProduct() != null) {
                documentProductJSON.put("product", this.getProduct().toJson());
            }

            if (this.getFormula() != null) {
                documentProductJSON.put("formula", this.getFormula().toJson());
            }
            if (this.getChildren() != null && this.getChildren().size() > 0) {
                JSONArray children = new JSONArray();
                for (DocumentProduct documentProduct : this.getChildren()) {
                    if (documentProduct.isActive()) {
                        children.put(documentProduct.toJson());
                    }
                }
                documentProductJSON.put("children", children);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return documentProductJSON;
    }
}
