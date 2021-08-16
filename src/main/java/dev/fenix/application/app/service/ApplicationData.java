package dev.fenix.application.app.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class ApplicationData {
  private final Authentication auth;

  public ApplicationData() {
    this.auth = SecurityContextHolder.getContext().getAuthentication();
  }

  public Authentication getAuth() {
    return auth;
  }

  public String getAuthName() {
    return auth.getName();
  }

  public Boolean isAuth() {
    return SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser";
  }

  public Boolean isAllow() {
    Collection<SimpleGrantedAuthority> authorities =
        (Collection<SimpleGrantedAuthority>)
            SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    return true;
  }
}
