package dev.fenix.application.accounting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.product.model.ProductAttachment;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.model.DocumentProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounting__lettering_customer")
public class LetteringCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Fetch(value = FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lettering_id")
    private Set<Document> documents;

    @Fetch(value = FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lettering_id")
    private Set<PaymentCustomer> paymentsCustomer;


    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "letter_id", referencedColumnName = "id")
    private Letter letter;

    @NotNull(message = "Please enter the customer")
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;



    public Double getTotalPaymentsCustomer(){
          Double  total =  0d ;
       for(PaymentCustomer paymentCustomer : this.getPaymentsCustomer()){
           total+=paymentCustomer.getMontant();
       }
        return total ;
    }

    public Double getTotalDocuments(){
        Double  total =  0d ;
        for(Document document : this.getDocuments()){
             total+=document.totalDocument();
        }
        return total ;
    }

    public boolean isBalanced() {
        return Math.abs(this.getTotalDocuments() - this.getTotalPaymentsCustomer()) < 0.009  ;
    }

    public JSONObject toJson() {
        JSONObject letteringCustomerJSON = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            letteringCustomerJSON.put("id", this.getId());
         if (this.getCustomer() != null) {
                letteringCustomerJSON.put("customer", this.getCustomer().toJson());
            }
            if (this.getDocuments() != null) {
                JSONArray documentsList = new JSONArray();
                for (Document document : this.getDocuments()) {
                    if (document != null) {
                        documentsList.put(document.getId());
                    }
                }
                letteringCustomerJSON.put("documents", documentsList);
            }
            if (this.getPaymentsCustomer() != null) {
                JSONArray paymentsCustomerList = new JSONArray();
                for (PaymentCustomer paymentCustomer : this.getPaymentsCustomer()) {
                    if (paymentCustomer != null) {
                        paymentsCustomerList.put(paymentCustomer.toJson());
                    }
                }
                letteringCustomerJSON.put("paymentsCustomer", paymentsCustomerList);
            }
            if (this.getLetter() != null) {
                letteringCustomerJSON.put("letter", this.getLetter().toJson());
            }

            if (this.getModifyDate() != null) {
                letteringCustomerJSON.put("modifyDate", formatter.format(this.getModifyDate()));
            }
            if (this.getCreateDate() != null) {
                letteringCustomerJSON.put("createDate", formatter.format(this.getCreateDate()));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return letteringCustomerJSON;
    }
}
