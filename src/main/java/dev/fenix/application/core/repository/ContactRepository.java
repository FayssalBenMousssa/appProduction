package dev.fenix.application.core.repository;


import dev.fenix.application.core.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository <Contact,Long> {

}
