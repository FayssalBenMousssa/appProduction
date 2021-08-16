package dev.fenix.application.security.exception;

public class EmailFoundException extends Exception {
  public EmailFoundException() {
    super();
  }

  public EmailFoundException(String message) {
    super(message);
  }
}
