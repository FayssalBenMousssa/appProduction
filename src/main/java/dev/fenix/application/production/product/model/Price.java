package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.fenix.application.core.model.Period;
import dev.fenix.application.production.customer.model.Customer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "prds__price")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter the name")
    private String name;

    @NotNull(message = "Please enter the category")
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")

    private CategoryPrice category;

    @NotNull(message = "Please enter the Period")
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "period_id", referencedColumnName = "id")
    private Period period;

    @NotNull(message = "Please enter the Tax")
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "tax_id", referencedColumnName = "id")
    private Tax tax;


    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonBackReference(value = "prices")
    private Product product;

    @ManyToMany(mappedBy = "prices")
    private List<Customer> customers = new ArrayList<Customer>();

    @NotNull(message = "Please enter the value")
    private Double value;

    @NotNull(message = "Please enter the maxQte")
    @Column(name = "max_qte")
    private int maxQte;

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

    public JSONObject toJson() {
        JSONObject priceJSON = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            priceJSON.put("id", this.getId());
            priceJSON.put("name", this.getName());
            if (this.getCategory() != null) {
                priceJSON.put("category", this.getCategory().toJson());
            }
            priceJSON.put("period", this.getPeriod().toJson());
            priceJSON.put("tax", this.getTax().toJson());
            priceJSON.put("value", this.getValue());
            priceJSON.put("maxQte", this.getMaxQte());
            priceJSON.put("active", this.isActive());
            priceJSON.put("product", this.getProduct().toSmallJson());
            if (this.getModifyDate() != null) {
                priceJSON.put("modifyDate", formatter.format(this.getModifyDate()));
            }
            if (this.getCreateDate() != null) {
                priceJSON.put("createDate", formatter.format(this.getCreateDate()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return priceJSON;
    }


    public JSONObject toSmallJson() {
        JSONObject priceJSON = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            priceJSON.put("id", this.getId());
            priceJSON.put("name", this.getName());
            if (this.getCategory() != null){
                priceJSON.put("category", this.getCategory().toJson());
            }

            if (this.getPeriod() != null){
                priceJSON.put("period", this.getPeriod().toJson());
            }


            priceJSON.put("tax", this.getTax().toJson());
            priceJSON.put("value", this.getValue());
            priceJSON.put("maxQte", this.getMaxQte());
            priceJSON.put("active", this.isActive());
            if (this.getModifyDate() != null) {
                priceJSON.put("modifyDate", formatter.format(this.getModifyDate()));
            }

            if (!this.getCustomers().isEmpty()) {
                JSONArray customersList = new JSONArray();
                for (Customer customer : getCustomers()) {
                    customersList.put(customer.toSmallJson());
                }
                priceJSON.put("customers", customersList);
            }

            if (this.getCreateDate() != null) {
                priceJSON.put("createDate", formatter.format(this.getCreateDate()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return priceJSON;
    }
}

//// https://www.stackchief.com/blog/One%20To%20Many%20Example%20%7C%20Spring%20Data%20JPA
