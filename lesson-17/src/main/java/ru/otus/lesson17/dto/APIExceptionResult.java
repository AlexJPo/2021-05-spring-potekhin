package ru.otus.lesson17.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author ajp
 * @date 21.08.2021
 */
@RequiredArgsConstructor
public class APIExceptionResult {
  private final LocalDateTime timestamp;
  private final String message;
  private final Integer status;
}
