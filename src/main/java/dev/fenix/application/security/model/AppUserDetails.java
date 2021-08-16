package dev.fenix.application.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AppUserDetails implements UserDetails {

  private final String userName;
  private final String password;
  private final boolean active;
  private final List<GrantedAuthority> authorities;

  public AppUserDetails(User user) {
    this.userName = user.getUserName();
    this.password = user.getPassword();
    this.active = user.isActive();
    this.authorities = this.getUserAuthority(user.getRoles());
  }

  private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
    Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
    for (Role role : userRoles) {
      roles.add(new SimpleGrantedAuthority(role.getRole()));
    }
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
    return grantedAuthorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return active;
  }
}
