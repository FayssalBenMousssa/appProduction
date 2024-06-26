package dev.fenix.application.api.security.models;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

  private String username;
  private String password;

  // need default constructor for JSON Parsing
  public AuthenticationRequest() {}

  public AuthenticationRequest(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
