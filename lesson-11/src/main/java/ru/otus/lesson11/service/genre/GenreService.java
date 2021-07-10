package ru.otus.lesson11.service.genre;

import ru.otus.lesson11.model.Genre;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface GenreService {
  /**
   * Поиск жанра по id
   *
   * @param id id жанра
   * @return жанр
   */
  Genre getById(long id);

  /**
   * Получение всех жанров
   *
   * @return список жанров
   */
  List<Genre> getAll();
}
