package dev.fenix.application;

import dev.fenix.application.app.util.DatabaseUtil;
import dev.fenix.application.person.model.Gender;
import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.production.product.model.Classification;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.ClassificationRepository;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.security.model.Role;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.RoleRepository;
import dev.fenix.application.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class Application {

  private final MessageSource messageSource;
  @Autowired private PersonRepository personRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private RoleRepository roleRepository;
  @Autowired private ClassificationRepository classNameRepository;
  @Autowired private ProductRepository productRepository;

  private static final Logger log = LoggerFactory.getLogger(Application.class);
  @Autowired private Environment env;

  @PostConstruct
  private void runDbBackup() {
    log.trace("Application.runDbBackup method accessed");
    LocalDateTime now = LocalDateTime.now(); // current date and time
    LocalDateTime midnight = now.toLocalDate().plusDays(1).atStartOfDay();

    long delay = Duration.between(now, midnight).toMinutes();
    log.trace(delay + " min  to start Backup");

    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    exec.scheduleAtFixedRate(
        new Runnable() {
          @Override
          public void run() {
            log.trace("Application.runDbBackup runnable  method accessed");
            try {
              DatabaseUtil.backup(
                  env.getProperty("spring.datasource.username"),
                  env.getProperty("spring.datasource.password"),
                  env.getProperty("DATABASE_NAME"),
                  env.getProperty("DATABASE_NAME"));
            } catch (IOException e) {
              log.error("catch Exception");
              e.printStackTrace();
            } catch (InterruptedException e) {
              log.error("catch Exception");
              e.printStackTrace();
            }
          }
        },
        delay,
        Long.parseLong(env.getProperty("app.backup.period")),
        TimeUnit.MINUTES);
  }

  @PostConstruct
  private void insertDbDefault() {
    log.trace("insertDbDefault method accessed");
    Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 11).getTime();
    /** find User with Name admin */
    log.trace("find User with Name admin");
    User admin = userRepository.findOneByUserName("admin");
    /** find role name ROLE_USER */
    log.trace("find role name ROLE_USER");
    Role roleUser = roleRepository.findByRole("ROLE_USER");
    /** find role name ROLE_ADMIN */
    log.trace("find role name ROLE_ADMIN");
    Role roleAdmin = roleRepository.findByRole("ROLE_ADMIN");
    /** add ROLE_ADMIN */
    if (roleAdmin == null) {
      Role roles = new Role();
      roles.setRole("ROLE_ADMIN");
      roles.setName("admin");
      roleRepository.save(roles);
    }
    /** add ROLE_USER */
    if (roleUser == null) {
      Role role = new Role();
      role.setRole("ROLE_USER");
      role.setName("admin");
      roleRepository.save(role);
    }
    /** insert admin if not exists */
    if (admin == null) {

      /** find role */
      Role user_role = roleRepository.findByRole("ROLE_USER");
      Role admin_role = roleRepository.findByRole("ROLE_ADMIN");
      /** create array role role */
      Set<Role> vowelsSet = new HashSet<>();

      vowelsSet.add(user_role);
      vowelsSet.add(admin_role);
      /** create new User */
      User user = new User();
      user.setRoles(vowelsSet);
      user.setCreateDate(date);
      user.setPassword("password");
      user.setUserpassword("password");
      user.setUserName("admin");
      user.setEmail("admin@example.com");
      user.setActive(true);
      user.setResetPasswordToken("456454564");

      /** create new Person */
      Person person = new Person();
      person.setFirstName("admin");
      person.setLastName("admin");
      person.setBirthDate(date);
      person.setGender(Gender.MALE);
      person.setCreateDate(date);
      person.setModifyDate(date);
      user.CryptPassword();
      person.setUser(user);
      /** save all */
      Person savedPerson = personRepository.save(person);
      log.trace("Save New Person " + person.toString());
    }
  }

///  @PostConstruct
  private void cleanUp() {
    List<Classification> allClassification = classNameRepository.findAll();
    for (Classification classification : allClassification) {
      classification.setName(
          classification.getName().replaceAll("\\n", "").replaceAll(",", "").trim());

      if (classification.getLevel() != 2) {
        String initials = "";
        for (String src : classification.getName().split(" ")) {
          if (!src.isEmpty()) {
            initials += src.charAt(0);
          }
        }
        initials = initials.replaceAll(" ", "");
        log.info(initials);
        if (classification.getLevel() == 0) {
          classification.setCode("G" + initials.toUpperCase());
        } else if (classification.getLevel() == 1) {
          classification.setCode("F" + initials.toUpperCase());
        }
      }

      classNameRepository.save(classification);
    }
    ;

    Iterable<Product> allProducts = productRepository.findAll();
    for (Product product : allProducts) {
      product.setName(product.getName().replaceAll("\\n", "").replaceAll(",", "").trim());
      product.setCodeDes(product.getCodeDes().replaceAll("\\n", "").replaceAll(",", "").trim());
      productRepository.save(product);
    }
    ;
  }

  public static void main(String[] args) {
    log.trace("Application.main method accessed");
    SpringApplication.run(Application.class, args);
  }
}
