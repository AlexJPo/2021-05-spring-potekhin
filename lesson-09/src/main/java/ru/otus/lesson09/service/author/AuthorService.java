package ru.otus.lesson09.service.author;

import ru.otus.lesson09.model.Author;

import java.util.List;
import java.util.Optional;

/**
 * @author ajp
 * @date 04.07.2021
 */
public interface AuthorService {
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
