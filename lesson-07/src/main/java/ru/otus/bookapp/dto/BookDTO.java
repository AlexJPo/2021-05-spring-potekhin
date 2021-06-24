package ru.otus.bookapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.otus.bookapp.domain.Author;
import ru.otus.bookapp.domain.Genre;

/**
 * @author ajp
 * @date 24.06.2021
 */
@Data
@RequiredArgsConstructor
public class BookDTO {
  private final Long id;
  private final String title;
  private final Author author;
  private final Genre genre;
}
