package ru.otus.bookapp.service.author;

import ru.otus.bookapp.domain.Author;
import ru.otus.bookapp.exception.NotFoundRowException;

/**
 * @author Aleksey.Potekhin
 * @date 29.06.2021
 */
public interface AuthorService {
  /**
   * Поиск автора по id
   *
   * @param id id автора
   * @return автор
   * @throws NotFoundRowException если отсутствует запись
   */
  Author getById(long id) throws NotFoundRowException;


}
