package dev.fenix.application.security.repository;

import dev.fenix.application.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserName(String userName);

  /* @CacheEvict(value="twenty-second-cache", key = "'UserInCache'+#userId",  beforeInvocation = true)
  @Cacheable(value="twenty-second-cache", key = "'UserInCache'+#userId")*/
  List<User> findAll();

  User getUserById(Integer id);

  @Query("SELECT u FROM User u WHERE u.email = ?1")
  User findByEmail(String email);

  User findByResetPasswordToken(String token);

   User findOneByUserName(String name);

  User findOneByEmail(String email);
  User findByEmailIgnoreCase(String email);
}
