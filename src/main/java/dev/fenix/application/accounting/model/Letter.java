package dev.fenix.application.accounting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounting__letter")
public class Letter {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

 @NotNull(message = "Please enter the code as string")
  private String code;

 @NotNull(message = "Please enter the code as string")
 @Enumerated(EnumType.STRING)
 @Column(name = "letter_case")
 private LetterCase letterCase;


  public JSONObject toJson() {
    JSONObject LetterJSON = new JSONObject();
    try {
        LetterJSON.put("id", this.getId());
        LetterJSON.put("code", this.getCode());
        LetterJSON.put("letterCase", this.getLetterCase());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return LetterJSON;
  }
}
