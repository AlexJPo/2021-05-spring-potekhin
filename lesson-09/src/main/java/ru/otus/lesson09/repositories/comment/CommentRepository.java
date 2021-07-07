package ru.otus.lesson09.repositories.comment;

import ru.otus.lesson09.model.Comment;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface CommentRepository {
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
  Optional<Comment> getById(long id);

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
