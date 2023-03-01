package dev.fenix.application.accounting.model;

import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.treatment.model.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

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


  @NotNull(message = "Please enter the document")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "document_id", referencedColumnName = "id")
  private Document document;

  @NotNull(message = "Please enter the document")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "payment_customer_id", referencedColumnName = "id")
  private PaymentCustomer paymentCustomer;

  @NotNull(message = "Please enter the document")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
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

  public JSONObject toJson() {
    JSONObject LetteringCustomerJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      LetteringCustomerJSON.put("id", this.getId());
      LetteringCustomerJSON.put("document", this.getDocument().toSmallJson());
      LetteringCustomerJSON.put("paymentCustomer", this.getPaymentCustomer().toJson());
      LetteringCustomerJSON.put("customer", this.getCustomer().toJson());
      if (this.getModifyDate() != null) {
        LetteringCustomerJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        LetteringCustomerJSON.put("createDate", formatter.format(this.getCreateDate()));
      }


    } catch (JSONException e) {
      e.printStackTrace();
    }
    return LetteringCustomerJSON;
  }
}
