package ru.otus.bookapp.dao.gener;

import ru.otus.bookapp.domain.Genre;

/**
 * @author ajp
 * @date 24.06.2021
 */
public interface GenreDao {
  long nextId();

  Genre findById(long id);
}
