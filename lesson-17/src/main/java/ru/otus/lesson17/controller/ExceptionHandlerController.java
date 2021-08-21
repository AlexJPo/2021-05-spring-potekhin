package ru.otus.lesson17.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.lesson17.dto.APIExceptionResult;
import ru.otus.lesson17.exception.BadRequestException;
import ru.otus.lesson17.exception.InternalServerErrorException;

import java.time.LocalDateTime;

/**
 * @author ajp
 * @date 21.08.2021
 */
@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<APIExceptionResult> handleBadRequestException(Exception exception) {
    return convertToRequest(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<APIExceptionResult> handleInternalServerErrorException(Exception exception) {
    return convertToRequest(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<APIExceptionResult> convertToRequest(final String message, final HttpStatus status) {
    APIExceptionResult exception = new APIExceptionResult(LocalDateTime.now(), message, status.value());
    return new ResponseEntity<>(exception, status);
  }
}
