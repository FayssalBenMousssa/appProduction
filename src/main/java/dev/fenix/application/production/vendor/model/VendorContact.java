package dev.fenix.application.production.vendor.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "vendor__contact")
public class VendorContact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter the  name")
    private String  name;
    @NotNull(message = "Please enter the telephone")
    private String telephone;

    @NotNull(message = "Please enter the email")
    private String email;


    @NotNull(message = "Please enter the note")
    private String note;
    @NotNull(message = "Please enter the active")
    private String active;


}
