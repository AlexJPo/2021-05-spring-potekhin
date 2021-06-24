package ru.otus.bookapp.service;

import ru.otus.bookapp.dto.BookDTO;

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
  void deleteById(long id);

  /**
   * Добавление книги
   *
   * @param bookTitle название книги
   * @param authorId id автора
   * @param genreId id книги
   */
  void insert(String bookTitle, long authorId, long genreId);

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
  void updateAuthor(long bookId, long authorId);

  /**
   * Изменение жанра книги
   *
   * @param bookId id книги
   * @param genreId id жанра
   */
  void updateGenre(long bookId, long genreId);
}
