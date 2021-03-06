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
   * Изменение данных о книге
   *
   * @param id id книги
   * @param bookTitle название
   * @param authorId id автора
   * @param genreId id жанра
   */
  String update(long id, String bookTitle, long authorId, long genreId);

  /**
   * Получение количества строк
   *
   * @return количество строк
   */
  long getTotalRows();

  /**
   * Получение списка всех книг
   */
  List<String> getAllBooks();
}
