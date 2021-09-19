package ru.otus.lesson17.repositories.genre;

import ru.otus.lesson17.model.Genre;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface GenreRepository {
  /**
   * Поиск жанра по id
   *
   * @param id id жанра
   * @return жанр
   */
  Optional<Genre> getById(long id);

  /**
   * Получения списка всех жанров
   *
   * @return список жанров
   */
  List<Genre> findAll();

  List<Genre> getAllGenresByIds(List<Long> genreId);
}
