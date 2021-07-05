package ru.otus.lesson09.repositories.book;

import ru.otus.lesson09.model.Book;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface BookRepository {
  /**
   * Сохраненеи книги
   *
   * @param comment книга
   * @return книга
   */
  Book save(Book comment);

  /**
   * Поиск книги по id
   *
   * @param id id книги
   * @return книга
   */
  Optional<Book> getById(long id);

  /**
   * Получение всех книг
   *
   * @return все книги
   */
  List<Book> findAll();

  /**
   * Обновление книги по id
   *
   * @param id id книги
   * @param title название книги
   */
  void updateTitleById(long id, String title);

  /**
   * Удаление книги по id
   *
   * @param id id книги
   */
  void deleteById(long id);
}
