package ru.otus.lesson09.repositories.author;

import org.springframework.stereotype.Repository;
import ru.otus.lesson09.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public AuthorRepositoryJpa(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Author> getById(final long id) {
    return Optional.ofNullable(entityManager.find(Author.class, id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Author> getAll() {
    return entityManager
        .createQuery("select a from Author a", Author.class)
        .getResultList();
  }
}
