package dev.fenix.application.production.payment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.fenix.application.accounting.model.LetteringCustomer;
import dev.fenix.application.production.customer.model.Customer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "payment__customer")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull(message = "Please enter the payment_method_id")
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Please enter the payment_status_id")
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_status_id", referencedColumnName = "id")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Please enter the payment_method_id")
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @NotNull(message = "Please enter the code")
    private String code;

    private String label;
    private String numberValue;
    private String beneficiary;
    private String transmitter;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;


    //// MONTANT
    @NotNull(message = "Please enter the montant")
    private Double montant;


    @Column(name = "due_date", columnDefinition = "date NULL")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Column(name = "payment_date", columnDefinition = "date NULL")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

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

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "lettering_id", referencedColumnName = "id")
    @JsonBackReference(value = "payments-customer")
    private LetteringCustomer letteringCustomer;

    public JSONObject toJson() {
        JSONObject paymentCustomerJSON = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");

        try {
            paymentCustomerJSON.put("id", this.getId());
            paymentCustomerJSON.put("code", this.getCode());
            if (this.getCustomer() != null) {
                paymentCustomerJSON.put("customer", this.getCustomer().toJson());
            }
            if (this.getPaymentMethod() != null) {
                paymentCustomerJSON.put("paymentMethod", this.getPaymentMethod().toJson());
            }
            if (this.getPaymentMethod() != null) {
                paymentCustomerJSON.put("paymentStatus", this.getPaymentMethod().toJson());
            }

            paymentCustomerJSON.put("montant", this.getMontant());

            if (this.getLetteringCustomer() != null) {
                paymentCustomerJSON.put("letteringCustomer", this.getLetteringCustomer());
            }

            if (this.getLabel() != null) {
                paymentCustomerJSON.put("label", this.getLabel());
            }
            if (this.getNumberValue() != null) {
                paymentCustomerJSON.put("numberValue", this.getNumberValue());
            }
            if (this.getBeneficiary() != null) {
                paymentCustomerJSON.put("beneficiary", this.getBeneficiary());
            }
            if (this.getTransmitter() != null) {
                paymentCustomerJSON.put("transmitter", this.getTransmitter());
            }
            if (this.getBank() != null) {
                paymentCustomerJSON.put("bank", this.getBank().toJson());
            }
            if (this.getDueDate() != null) {
                paymentCustomerJSON.put("dueDate", formatter.format(this.getDueDate()));
            }
            if (this.getPaymentDate() != null) {
                paymentCustomerJSON.put("paymentDate", formatter.format(this.getPaymentDate()));
            }
            if (this.getModifyDate() != null) {
                paymentCustomerJSON.put("modifyDate", formatter.format(this.getModifyDate()));
            }
            if (this.getCreateDate() != null) {
                paymentCustomerJSON.put("createDate", formatter.format(this.getCreateDate()));
            }
            paymentCustomerJSON.put("active", this.getActive());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return paymentCustomerJSON;
    }
}
