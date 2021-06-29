package ru.otus.bookapp.dao.genre;

import ru.otus.bookapp.domain.Genre;
import ru.otus.bookapp.exception.NotFoundRowException;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
public interface GenreDao {
  /**
   * Поиск жанра по id
   *
   * @param id id жанра
   * @return жанр
   * @throws NotFoundRowException если отсутствует запись
   */
  Genre getById(long id) throws NotFoundRowException;
}
