package ru.otus.lesson16.service.book;

import ru.otus.lesson16.model.Book;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface BookService {
  /**
   * Добавление книги
   *
   * @param authorIds id авторов
   * @param genreIds id жанров
   * @param bookTitle название книги
   * @param bookComment комментарий к книге
   */
  void save(String bookTitle, List<Long> authorIds, List<Long> genreIds, String bookComment);

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
   * @param bookTitle название
   * @param authorIds id авторов
   * @param genreIds id жанров
   * @param bookComments комментарии к книге
   */
  void update(long id, String bookTitle, List<Long> authorIds, List<Long> genreIds, String[] bookComments);

  /**
   * Получение списка всех книг
   */
  List<Book> getAllBooks();

  /**
   * Удаление книги по id
   *
   * @param id id книги
   */
  String deleteById(long id);
}
