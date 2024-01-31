package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.fenix.application.security.model.User;
import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;

@Entity
@Getter
@Setter
@Table(name = "doc__print_model" )


@ToString
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PrintModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  private String name;



  @Column(name = "has_header",columnDefinition = "tinyint(1) default 1")
  private boolean hasHeader;

  @Column(name = "grouped_product",columnDefinition = "tinyint(1) default 1")
  private boolean groupedByProduct;


  @NotBlank
  @Column(columnDefinition="TEXT")
  private String parameters;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Valid
  private User userAccount;


  @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)



  @JoinColumn(name = "type_id", referencedColumnName = "id")
  private Type type;




  public JSONObject toJson() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    JSONObject PrintModelJSON = new JSONObject();
    try {
      PrintModelJSON.put("id", this.getId());
      PrintModelJSON.put("name", this.getName());
      PrintModelJSON.put("hasHeader", this.isHasHeader());
      PrintModelJSON.put("parameters", this.getParameters());
      PrintModelJSON.put("groupedByProduct", this.isGroupedByProduct());
      PrintModelJSON.put("user", this.getUserAccount().toSmallJson());
      PrintModelJSON.put("type", this.getType().toSmallJson());

       
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return PrintModelJSON;
  }
  
  

}
