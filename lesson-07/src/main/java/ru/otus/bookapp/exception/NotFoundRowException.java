package ru.otus.bookapp.exception;

/**
 * @author ajp
 * @date 24.06.2021
 */
public class NotFoundRowException extends RuntimeException {
  public NotFoundRowException(Throwable cause) {
    super(cause);
  }
}
