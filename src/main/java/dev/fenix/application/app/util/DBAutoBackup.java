package dev.fenix.application.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class DBAutoBackup {
  private static final Logger log = LoggerFactory.getLogger(DBAutoBackup.class);
  @Autowired private Environment env;

  // @Scheduled(cron = "0 30 1 * * ?")
 // @Scheduled(fixedDelay = 50000000)
 /* public void schedule() {
    //log.warn("Backup Started at " + new Date());
    try {
      DatabaseUtil.backup(
              env.getProperty("spring.datasource.username"),
              env.getProperty("spring.datasource.password"),
              env.getProperty("DATABASE_NAME"),
              env.getProperty("DATABASE_NAME"));
    } catch (IOException e) {
      //log.error("catch Exception");
      e.printStackTrace();
    } catch (InterruptedException e) {
      //log.error("catch Exception");
      e.printStackTrace();
    }
    //log.warn("Backup Ended at " + new Date());
  }*/


}
