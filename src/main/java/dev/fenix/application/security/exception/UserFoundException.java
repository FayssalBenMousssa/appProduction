package dev.fenix.application.security.exception;

public class UserFoundException extends RuntimeException {
  public UserFoundException() {
    super();
  }

  public UserFoundException(String message) {
    super(message);
  }
}
