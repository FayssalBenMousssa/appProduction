package dev.fenix.application.security.repository;

import dev.fenix.application.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


  Role findByRole(String role_user);

  Role findTopByOrderByIdDesc();
}
//// todo
// https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d
