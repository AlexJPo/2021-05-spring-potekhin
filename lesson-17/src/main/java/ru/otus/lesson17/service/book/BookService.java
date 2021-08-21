package ru.otus.lesson17.service.book;

import ru.otus.lesson17.dto.BookDTO;
import ru.otus.lesson17.model.Book;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface BookService {
  /**
   * Добавление книги
   *
   * @param bookDTO книга
   */
  void save(BookDTO bookDTO);

  /**
   * Поиск книги по id
   *
   * @param id id книги
   * @return DTO книги
   */
  Book getById(long id);

  /**
   * Изменение данных о книге
   *
   * @param id id книги
   * @param bookDTO книга
   */
  void update(long id, BookDTO bookDTO);

  /**
   * Получение списка всех книг
   */
  List<Book> getAllBooks();

  /**
   * Удаление книги по id
   *
   * @param id id книги
   */
  void deleteById(long id);
}
