package dev.fenix.application.security.repository;

import dev.fenix.application.security.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long > {



}

