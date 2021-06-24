package ru.otus.bookapp.dao.author;

import ru.otus.bookapp.domain.Author;
import ru.otus.bookapp.exception.NotFoundRowException;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
public interface AuthorDao {
  /**
   * Поиск автора по id
   *
   * @param id id автора
   * @return автор
   * @throws NotFoundRowException если отсутствует запись
   */
  Author findById(long id) throws NotFoundRowException;
}
