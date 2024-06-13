package dev.fenix.application.production.visit.model;


import dev.fenix.application.business.model.Staff;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.treatment.model.Status;
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
@Table(name = "vt__visit")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Customer is required")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @NotNull(message = "Staff is required")
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @NotNull(message = "Please enter the scheduled visit date")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "scheduled_visit_date")
    private Date scheduledVisitDate;

    @NotNull(message = "Please enter the actual visit date")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "actual_visit_date")
    private Date actualVisitDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @NotNull(message = "Please enter the active")
    private boolean active;

    @Column(name = "visit_order")
    private Integer visitOrder;

    @Column(name = "notes")
    private String notes;


    @Column(name = "position")
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Status status;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "visit_attachment_id")
    private VisitAttachment visitAttachment;

    public JSONObject toJson() {
        JSONObject visitJSON = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
        try {
            visitJSON.put("id", this.getId());
            visitJSON.put("staff", staff.toSmallJson());
            visitJSON.put("customer", customer.toSmallJson());
            visitJSON.put("active", this.isActive());
            visitJSON.put("status",  this.getStatus());
            visitJSON.put("visitOrder",  this.getVisitOrder());
            if (this.getVisitAttachment() != null) {
                visitJSON.put("visit_attachment",  this.getVisitAttachment());
            }


            if (this.getPosition() != null) {
                visitJSON.put("position",  this.getPosition());
            }
            if (this.getNotes() != null) {
                visitJSON.put("notes",  this.getNotes());
            }
            if (this.getScheduledVisitDate() != null) {
                visitJSON.put("scheduledVisitDate", formatter.format(this.getScheduledVisitDate()));
            }
            if (this.getActualVisitDate() != null) {
                visitJSON.put("actualVisitDate", formatter.format(this.getActualVisitDate()));
            }
            if (this.getModifyDate() != null) {
                visitJSON.put("modifyDate", formatter.format(this.getModifyDate()));
            }
            if (this.getCreateDate() != null) {
                visitJSON.put("createDate", formatter.format(this.getCreateDate()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return visitJSON;

    }

}

