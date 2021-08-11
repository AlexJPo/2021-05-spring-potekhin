package ru.otus.lesson11.service.mapper;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Genre;

import java.util.stream.Collectors;

/**
 * @author Aleksey.Potekhin
 * @date 12.07.2021
 */
@Service
public class BookEntityMapperImpl implements BookEntityMapper {
  private static final String STRING_JOIN_DELIMITER = ", ";

  /**
   * {@inheritDoc}
   */
  @Override
  public String map(Book book) {
    String authors;
    if (CollectionUtils.isEmpty(book.getAuthors())) {
      authors = "no authors";
    } else {
      authors = book.getAuthors()
          .stream()
          .map(Author::getName)
          .collect(Collectors.joining(STRING_JOIN_DELIMITER));
    }

    String genres;
    if (CollectionUtils.isEmpty(book.getGenres())) {
      genres = "no genres";
    } else {
      genres = book.getGenres()
          .stream()
          .map(Genre::getTitle)
          .collect(Collectors.joining(STRING_JOIN_DELIMITER));
    }

    return String.format("Book title: %s\nAuthors: %s\nGenres: %s\n----------------\n",
        book.getTitle(),
        authors,
        genres);
  }
}
