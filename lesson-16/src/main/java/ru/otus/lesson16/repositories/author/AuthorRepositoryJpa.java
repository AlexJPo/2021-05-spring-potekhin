package ru.otus.lesson16.repositories.author;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson16.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
  public List<Author> findAll() {
    return entityManager
        .createQuery("select a from Author a", Author.class)
        .getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Author save(final Author author) {
    if (author.getId() == 0) {
      entityManager.persist(author);
      return author;
    }
    return entityManager.merge(author);
  }

  @Override
  public List<Author> getAuthorByIds(final List<Long> ids) {
    return findAll()
        .stream()
        .filter(author -> ids.contains(author.getId()))
        .collect(Collectors.toList());
  }
}
