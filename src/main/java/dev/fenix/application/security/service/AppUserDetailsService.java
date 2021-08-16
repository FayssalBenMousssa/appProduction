package dev.fenix.application.security.service;

import dev.fenix.application.security.model.AppUserDetails;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

  @Autowired UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserName(userName);
    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
    return user.map(AppUserDetails::new).get();
  }
}
