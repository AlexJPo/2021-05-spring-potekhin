package ru.otus.lesson11.service.book;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface BookService {
  /**
   * Добавление книги
   *
   * @param bookTitle название книги
   */
  String save(String bookTitle, String[] genres, String[] authors);

  /**
   * Поиск книги по id
   *
   * @param id id книги
   * @return DTO книги
   */
  String getById(long id);

  /**
   * Получение списка всех книг
   */
  List<String> getAllBooks();

  /**
   * Изменение данных о книге
   *
   * @param id id книги
   * @param bookTitle название
   */
  String update(long id, String bookTitle, String[] genres, String[] authors);

  /**
   * Удаление книги по id
   *
   * @param id id книги
   */
  String deleteById(long id);

  /**
   * Поиск всех комментария по id книги
   *
   * @param id id книги
   * @return списоку комментариев
   */
  String getComments(long id);
}
