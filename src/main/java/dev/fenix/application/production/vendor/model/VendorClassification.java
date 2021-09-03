package dev.fenix.application.production.vendor.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "vendor__classification")
public class VendorClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @NotNull(message = "Please enter the name")
    private String name;

    @NotNull(message = "Please enter the active")
    private String active;


}
