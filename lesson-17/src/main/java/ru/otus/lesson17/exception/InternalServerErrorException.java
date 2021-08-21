package ru.otus.lesson17.exception;

/**
 * @author ajp
 * @date 21.08.2021
 */
public class InternalServerErrorException extends RuntimeException {
  public InternalServerErrorException(String error) {
    super(error);
  }
}
