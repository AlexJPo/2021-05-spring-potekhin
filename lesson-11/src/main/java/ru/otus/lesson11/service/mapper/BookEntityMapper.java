package ru.otus.lesson11.service.mapper;

import ru.otus.lesson11.model.Book;

/**
 * @author Aleksey.Potekhin
 * @date 12.07.2021
 */
public interface BookEntityMapper {
  /**
   * Конвертация инфомрации о книге
   *
   * @param book книга
   */
  <T> T map(Book book);
}
