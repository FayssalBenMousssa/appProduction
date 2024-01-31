package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "prds__classification")
@AllArgsConstructor
@NoArgsConstructor
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter the name")
    private String name;

    @NotNull(message = "Please enter the code")
    private String code;


    @NotNull(message = "Please enter the imageUrl")
    @Column(name = "image_url")
    private String imageUrl;


    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Classification parent;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ProductType type;


    /* fetch = FetchType.LAZY is default in one:many */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "parent")
    @JsonIgnore
    private Set<Classification> children;

    @Column(columnDefinition = "int default 1")
    private long level;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean active;

    public Classification(
            Long id,
            @NotNull(message = "Please enter the Name") String name,
            @NotNull(message = "Please enter the Code") String code,
            Classification parent) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parent = parent;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;


    public JSONObject toJson() {
        JSONObject classificationJSON = new JSONObject();
        try {
            classificationJSON.put("id", this.getId());
            classificationJSON.put("name", this.getName());

            if (this.getType() != null) {
                classificationJSON.put("type", this.getType().toJson());
            }
            
            classificationJSON.put("active", this.isActive());
            if (this.getCode() != null) {
                classificationJSON.put("code", this.getCode());
            }
            if (this.getImageUrl() != null) {
                classificationJSON.put("imageUrl", this.getImageUrl());
            }
            classificationJSON.put("level", this.getLevel());
            if (this.getParent() != null) {
                JSONObject parent = new JSONObject();
                parent.put("id", this.getParent().getId());
                parent.put("name", this.getParent().getName());
                parent.put("code", this.getParent().getCode());
                if (this.getParent().getType() != null) {
                    parent.put("type", this.getParent().getType().toJson());
                }

                parent.put("level", this.getParent().getLevel());
                parent.put("active", this.getParent().isActive());
                if (this.getImageUrl() != null) {
                    parent.put("imageUrl", this.getImageUrl());
                }

                if (this.getParent().getParent()!= null ) {
                    JSONObject grandParent = new JSONObject();
                    grandParent.put("id", this.getParent().getParent().getId());
                    grandParent.put("name", this.getParent().getParent().getName());
                    grandParent.put("code", this.getParent().getParent().getCode());
                    grandParent.put("level", this.getParent().getParent().getLevel());
                    if(this.getParent().getParent().getType()!= null) {
                        grandParent.put("type", this.getParent().getParent().getType().toJson());
                    }
                    grandParent.put("active", this.getParent().getParent().isActive());
                    parent.put("parent", grandParent);
                    if (this.getImageUrl()!= null) {
                        grandParent.put("imageUrl", this.getParent().getParent().getImageUrl());
                    }
                }


                classificationJSON.put("parent", parent);
            }
            if (this.getChildren() != null) {
                JSONArray children = new JSONArray();
                for (Classification classification : this.getChildren()) {
                    if (classification.isActive()) {
                        JSONObject child = new JSONObject();
                        child.put("id", classification.getId());
                        child.put("name", classification.getName());
                        child.put("code", classification.getCode());
                        child.put("level", classification.getLevel());
                        child.put("active", classification.isActive());
                        child.put("type", classification.getType().toJson());
                        if (this.getImageUrl() != null) {
                            child.put("imageUrl", classification.getImageUrl());
                        }

                        if (classification.getChildren() != null) {
                            JSONArray childrenChild = new JSONArray();
                            for (Classification childClassification : classification.getChildren()) {
                                if (childClassification.isActive()) {
                                    JSONObject childOfChild = new JSONObject();
                                    childOfChild.put("id", childClassification.getId());
                                    childOfChild.put("name", childClassification.getName());
                                    childOfChild.put("code", childClassification.getCode());
                                    childOfChild.put("level", childClassification.getLevel());
                                    childOfChild.put("active", childClassification.isActive());
                                    childOfChild.put("type", childClassification.toJson());
                                    if (this.getImageUrl() != null) {
                                        childOfChild.put("imageUrl", childClassification.getImageUrl());
                                    }
                                    childrenChild.put(childOfChild);
                                }
                            }
                            child.put("children", childrenChild);
                        }
                        children.put(child);
                    }
                }
                classificationJSON.put("children", children);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return classificationJSON;
    }
}
