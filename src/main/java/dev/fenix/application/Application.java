package dev.fenix.application;

import dev.fenix.application.app.util.DatabaseUtil;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class Application {

  private final MessageSource messageSource;

  private static final Logger log = LoggerFactory.getLogger(Application.class);
  @Autowired private Environment env;

  @PostConstruct
  private void runDbBackup() {
    LocalDateTime now = LocalDateTime.now(); // current date and time
    LocalDateTime midnight = now.toLocalDate().plusDays(1).atStartOfDay();

    long delay = Duration.between(now, midnight).toMinutes();

    log.info("delay  : " + delay);

    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    exec.scheduleAtFixedRate(
        new Runnable() {
          @Override
          public void run() {
            try {
              DatabaseUtil.backup(
                  env.getProperty("spring.datasource.username"),
                  env.getProperty("spring.datasource.password"),
                  env.getProperty("DATABASE_NAME"),
                  env.getProperty("DATABASE_NAME"));
            } catch (IOException e) {
              e.printStackTrace();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        },
        delay,
        Long.parseLong(env.getProperty("app.backup.period")),
        TimeUnit.MINUTES);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
