package ru.otus.lesson17.exception;

/**
 * @author ajp
 * @date 21.08.2021
 */
public class BadRequestException extends RuntimeException {
  public BadRequestException(String error) {
    super(error);
  }
}
