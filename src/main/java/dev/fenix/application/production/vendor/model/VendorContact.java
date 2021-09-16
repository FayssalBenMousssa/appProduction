package dev.fenix.application.production.vendor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "vnd__contact")
@AllArgsConstructor
@NoArgsConstructor
public class VendorContact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter the  name")
    private String  name;


    private String  job;

    @NotNull(message = "Please enter the telephone")
    private String telephone;


    private String email;



    private String note;

    private Boolean active;


}
