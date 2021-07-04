package ru.otus.lesson09.repositories.comment;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson09.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * @author ajp
 * @date 04.07.2021
 */
@Repository
public class CommentRepositoryJpa implements CommentRepository {
  @PersistenceContext
  private EntityManager entityManager;

  public CommentRepositoryJpa(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Comment save(final Comment comment) {
    if (comment.getId() == 0) {
      entityManager.persist(comment);
      return comment;
    }
    return entityManager.merge(comment);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Comment> getById(final long id) {
    return Optional.ofNullable(entityManager.find(Comment.class, id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Comment> findAll() {
    return entityManager
        .createQuery("select c from Comment c", Comment.class)
        .getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void updateTextById(final long id, final String text) {
    Query query = entityManager.createQuery("update Comment с set с.text = :text where с.id = :id");
    query.setParameter("text", text);
    query.setParameter("id", id);
    query.executeUpdate();
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void deleteById(final long id) {
    Query query = entityManager.createQuery("delete from Comment c where c.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }
}
