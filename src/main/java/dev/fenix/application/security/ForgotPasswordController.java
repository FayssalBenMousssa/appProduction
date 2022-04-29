package dev.fenix.application.security;

import dev.fenix.application.app.service.ApplicationData;
import dev.fenix.application.core.Utility;
import dev.fenix.application.mail.module.Mail;
import dev.fenix.application.mail.services.MailService;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.service.UserService;
import javassist.NotFoundException;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/")
public class ForgotPasswordController {
  private static final Logger log = LoggerFactory.getLogger(ForgotPasswordController.class);
  @Autowired private JavaMailSender mailSender;
  @Autowired private UserService userService;
  @Autowired private ApplicationContext appContext;

  @GetMapping("/forgot_password")
  public String showForgotPasswordForm() {
    return "security/forgot_password";
  }

  @PostMapping("/forgot_password")
  public String processForgotPassword(HttpServletRequest request, Model model) {
    String email = request.getParameter("email");
    String token = RandomString.make(30);

    log.info(email);
    log.info(token);

    try {
      userService.updateResetPasswordToken(token, email);
      String resetPasswordLink =
          Utility.getSiteURL(request) + "/security/reset_password?token=" + token;
      sendEmail(email, resetPasswordLink);
      model.addAttribute(
          "message", "We have sent a reset password link to your email. Please check.");

    } catch (NotFoundException ex) {
      model.addAttribute("error", ex.getMessage());
    } catch (UnsupportedEncodingException | MessagingException e) {
      model.addAttribute("error", "Error while sending email");
    }

    return "security/forgot_password";
  }

  public void sendEmail(String recipientEmail, String link)
      throws MessagingException, UnsupportedEncodingException {

    String content =
        "<p>Hello,</p>"
            + "<p>You have requested to reset your password.</p>"
            + "<p>Click the link below to change your password:</p>"
            + "<p><a href=\""
            + link
            + "\">Change my password</a></p>"
            + "<br>"
            + "<p>Ignore this email if you do remember your password, "
            + "or you have not made the request.</p>";

    Mail mail = new Mail();
    mail.setMailFrom("fayssal.zf@gmail.com");
    mail.setMailTo(recipientEmail);
    mail.setMailSubject("Here's the link to reset your password");
    mail.setMailContent(content);

    MailService mailService = (MailService) appContext.getBean("mailService");
    mailService.sendEmail(mail);
  }

  @GetMapping("security/reset_password")
  public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
    User user = userService.getByResetPasswordToken(token);
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    model.addAttribute("token", token);

    if (user == null) {
      model.addAttribute("message", "Invalid Token");
      return "security/message";
    }

    return "security/reset_password";
  }

  @PostMapping("security/reset_password")
  public String processResetPassword(HttpServletRequest request, Model model) {
    String token = request.getParameter("token");
    String password = request.getParameter("password");

    User user = userService.getByResetPasswordToken(token);

    model.addAttribute("title", "Reset your password");
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);

    if (user == null) {
      model.addAttribute("message", "Invalid Token");
      return "security/message";
    } else {
      user.setUserpassword("Password");
      userService.updatePassword(user, password);
      model.addAttribute("message", "You have successfully changed your password.");
    }
    return "security/message";
  }
}
