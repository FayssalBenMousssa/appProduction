package dev.fenix.application.security.service;

import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

  private static final Logger log = LoggerFactory.getLogger(UserService.class);
  @Autowired private UserRepository userRepository;

  public void updateResetPasswordToken(String token, String email) throws NotFoundException {
    User user = userRepository.findByEmail(email);

    if (user != null) {
      user.setResetPasswordToken(token);
      user.setUserpassword("No_pass0");
      userRepository.save(user);
    } else {
      //log.warn("Could not find any customer with the email " + email);
      throw new NotFoundException("Could not find any customer with the email " + email);
    }
  }

  public User getByResetPasswordToken(String token) {
    return userRepository.findByResetPasswordToken(token);
  }

  public void updatePassword(User user, String newPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(newPassword);
    user.setUserpassword("No_pass0");
    user.setPassword(encodedPassword);
    user.setResetPasswordToken(null);
    userRepository.save(user);
  }
}
