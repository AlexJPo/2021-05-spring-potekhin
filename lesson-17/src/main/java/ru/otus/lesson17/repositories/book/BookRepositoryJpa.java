package ru.otus.lesson17.repositories.book;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson17.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
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
  public void update(final Book book) {
    entityManager.merge(book);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void delete(final Book book) {
    entityManager.remove(book);
  }
}
