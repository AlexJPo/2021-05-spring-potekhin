package ru.otus.bookapp.dao.book;

import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.exception.NotFoundRowException;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 23.06.2021
 */
public interface BookDao {
  /**
   * Получение следующего id в таблице
   *
   * @return  id книги
   */
  long nextId();

  /**
   * Получение количества записей
   *
   * @return количество записей
   */
  long count();

  /**
   * Поиск книги по id
   *
   * @param id id книги
   * @return книга
   * @throws NotFoundRowException если отсутвует запись
   */
  Book findById(Long id) throws NotFoundRowException;

  /**
   * Добавление книги
   *
   * @param book книга
   */
  void insert(Book book);

  /**
   * Добавление книги
   *
   * @param book книга
   */
  void update(Book book);

  /**
   * Удаление книги по id
   *
   * @param id книга
   */
  void deleteById(Long id);

  /**
   * Получения списка всех книг
   *
   * @return список книг
   */
  List<Book> getAll();
}
