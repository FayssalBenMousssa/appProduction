package dev.fenix.application.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DatabaseUtil {

  private static final Logger log = LoggerFactory.getLogger(DatabaseUtil.class);

  public static boolean backup(
      String dbUsername, String dbPassword, String dbName, String outputFile)
      throws IOException, InterruptedException {

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hhmmss");
    String strDate = dateFormat.format(date);

    //log.info("Start database backup :" + outputFile + "_" + strDate);
    String command =
        String.format(
            "docker exec appsDB sh -c \"mysqldump -u%s -p'%s' %s\" >  %s",
            dbUsername,
            dbPassword,
            dbName,
            System.getProperty("user.dir") + "/DBBackup/" + outputFile + "_" + strDate + ".sql");

    Process process = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", command});
    //log.info("End database backup :" + outputFile + "_" + strDate);

    int processComplete = process.waitFor();

    return processComplete == 0;
  }
}
