package ru.otus.bookapp.service.genre;

import ru.otus.bookapp.domain.Genre;
import ru.otus.bookapp.exception.NotFoundRowException;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 29.06.2021
 */
public interface GenreService {
  /**
   * Поиск жанра по id
   *
   * @param id id жанра
   * @return жанр
   * @throws NotFoundRowException если отсутствует запись
   */
  Genre getById(long id) throws NotFoundRowException;

  List<Genre> getAll();
}
