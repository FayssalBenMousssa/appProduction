package dev.fenix.application.mail.services;

import dev.fenix.application.mail.module.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service("mailService")
public class MailServiceImpl implements MailService {

  @Autowired JavaMailSender mailSender;

  public void sendEmail(Mail mail) {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setSubject(mail.getMailSubject());

      mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "FenixApp.com"));
      mimeMessageHelper.setTo(mail.getMailTo());
      mimeMessageHelper.setText(mail.getMailContent(), true);
      mailSender.send(mimeMessageHelper.getMimeMessage());
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
