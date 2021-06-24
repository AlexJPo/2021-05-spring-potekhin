package ru.otus.bookapp.service;

import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.dto.BookDTO;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
public interface BookService {
  /**
   * Поиск книги по id
   *
   * @param id id книги
   * @return DTO книги
   */
  BookDTO getById(long id);

  /**
   * Удаление книги по id
   *
   * @param id id книги
   */
  String deleteById(long id);

  /**
   * Добавление книги
   *
   * @param bookTitle название книги
   * @param authorId id автора
   * @param genreId id книги
   */
  String insert(String bookTitle, long authorId, long genreId);

  /**
   * Получение количества строк
   *
   * @return количество строк
   */
  long getTotalRows();

  /**
   * Изменение автора книги
   *
   * @param bookId id книги
   * @param authorId id автора
   */
  String updateAuthor(long bookId, long authorId);

  /**
   * Изменение жанра книги
   *
   * @param bookId id книги
   * @param genreId id жанра
   */
  String updateGenre(long bookId, long genreId);

  /**
   * Получение списка всех книг
   */
  List<Book> getAllBooks();
}
