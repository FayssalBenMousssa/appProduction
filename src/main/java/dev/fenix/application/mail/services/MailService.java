package dev.fenix.application.mail.services;

import dev.fenix.application.mail.module.Mail;

public interface MailService {
  void sendEmail(Mail mail);
}
