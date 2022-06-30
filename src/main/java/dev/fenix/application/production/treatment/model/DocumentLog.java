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
@Table(name = "trt__doc_log" )


@ToString
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DocumentLog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinColumn(name = "document_id", referencedColumnName = "id")
  @JsonBackReference(value="document-log")
  private Document document;

}
