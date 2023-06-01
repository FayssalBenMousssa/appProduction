package dev.fenix.application.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.fenix.application.security.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/// Activity on this application
@Entity
@Getter
@Setter
@Table(name = "app__intervention")
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Valid
    private User user;

    @NotNull(message = "Please enter the priority")
    private Priority priority;

    private String note;
    @NotNull(message = "Please enter the url")
    private String url;
    private String message;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;



    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true) // javax.persistent.CascadeType
    @JoinColumn(name = "intervention_id") // parent's foreign key
    @JsonManagedReference(value = "intervention-responses")

    private Set<Response> responses = new LinkedHashSet<>();


    @Enumerated(EnumType.STRING)
    @Column(name = "intervention_statuses")
    private InterventionStatuses interventionStatuses;

    public JSONObject toJson() {
        JSONObject interventionJSON = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            interventionJSON.put("id", this.getId());
            interventionJSON.put("user", this.getUser().getUserName());

            if (this.getInterventionStatuses() != null) {
                interventionJSON.put("interventionStatuses", this.getInterventionStatuses());
            }


            if (this.getResponses() != null) {
                JSONArray responsesJson = new JSONArray();
                for (Response response : this.getResponses()) {

                    responsesJson.put(response.toJson());

                }
                interventionJSON.put("responses", responsesJson);
            }


            if (this.getUrl() != null)
                interventionJSON.put("url", this.getUrl());
            if (this.getMessage() != null)
                interventionJSON.put("message", this.getMessage());
            if (this.getNote() != null)
                interventionJSON.put("note", this.getNote());
            if (this.getPriority() != null)
                interventionJSON.put("priority", this.getPriority());
            if (this.getCreateDate() != null) {
                interventionJSON.put("createDate", formatter.format(this.getCreateDate()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return interventionJSON;
    }

}
