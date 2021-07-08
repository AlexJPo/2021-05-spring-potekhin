package ru.otus.lesson09.repositories.author;

import ru.otus.lesson09.model.Author;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface AuthorRepository {
  /**
   * Поиск автора по id
   *
   * @param id id автора
   * @return автор
   */
  Optional<Author> getById(long id);

  /**
   * Получения списка всех авторов
   *
   * @return список авторов
   */
  List<Author> getAll();
}
