package ru.otus.bookapp.domain;

import lombok.Data;

/**
 * @author ajp
 * @date 23.06.2021
 */
@Data
public class Book {
  private final long id;
  private final String title;
  private final long authorId;
  private final long genreId;
}
