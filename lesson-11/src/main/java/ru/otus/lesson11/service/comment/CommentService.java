package ru.otus.lesson11.service.comment;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface CommentService {
  /**
   * Сохраненеи коментария
   *
   * @param text комментарий
   * @return комментарий
   */
  String save(String text);

  /**
   * Поиск комментария по id
   *
   * @param id id комментария
   * @return комментарий
   */
  String getById(long id);

  /**
   * Обновление комментаряи
   *
   * @param id id комментария
   * @param text комментарий
   */
  String update(long id, String text);

  /**
   * Удаление комментария по id
   *
   * @param id id комментария
   */
  String deleteById(long id);
}
