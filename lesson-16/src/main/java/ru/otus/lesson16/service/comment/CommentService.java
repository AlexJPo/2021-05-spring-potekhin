package ru.otus.lesson16.service.comment;

import ru.otus.lesson16.model.Comment;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface CommentService {
  /**
   * Сохраненеи коментария
   *
   * @param comment комментарий
   * @return комментарий
   */
  Comment save(Comment comment);

  /**
   * Поиск комментария по id
   *
   * @param id id комментария
   * @return комментарий
   */
  Comment getById(long id);

  /**
   * Получение всех комментариев
   *
   * @return все комментарии
   */
  List<Comment> findAll();

  /**
   * Обновление комментаряи
   *
   * @param comment комментарий
   */
  void update(Comment comment);

  /**
   * Удаление комментария по id
   *
   * @param id id комментария
   */
  void deleteById(long id);
}
