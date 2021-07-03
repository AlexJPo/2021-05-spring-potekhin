package ru.otus.bookapp.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Aleksey.Potekhin
 * @date 23.06.2021
 */
@Data
@RequiredArgsConstructor
public class Author {
  private final long id;
  private final String name;
}
