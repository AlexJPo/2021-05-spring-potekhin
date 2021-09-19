package ru.otus.lesson17.service.genre;

import ru.otus.lesson17.model.Genre;

import java.util.List;
import java.util.Optional;

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
  Optional<Genre> getById(long id);

  /**
   * Получение всех жанров
   *
   * @return список жанров
   */
  List<Genre> findAll();

  List<Genre> getAllGenresByIds(List<Long> genreId);
}