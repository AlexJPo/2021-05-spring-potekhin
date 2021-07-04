package ru.otus.lesson09.repositories.book;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson09.model.Book;

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
public class BookRepositoryJpa implements BookRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public BookRepositoryJpa(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Book save(Book book) {
    if (book.getId() == 0) {
      entityManager.persist(book);
      return book;
    }
    return entityManager.merge(book);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Book> getById(final long id) {
    return Optional.ofNullable(entityManager.find(Book.class, id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Book> findAll() {
    return entityManager
        .createQuery("select b from Book b", Book.class)
        .getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void updateTitleById(final long id, final String title) {
    Query query = entityManager.createQuery("update Book b set b.title = :title where b.id = :id");
    query.setParameter("id", id);
    query.setParameter("title", title);
    query.executeUpdate();
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void deleteById(final long id) {
    Query query = entityManager.createQuery("delete from Book b where b.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }
}
