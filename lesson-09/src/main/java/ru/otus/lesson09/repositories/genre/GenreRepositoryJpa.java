package ru.otus.lesson09.repositories.genre;

import org.springframework.stereotype.Repository;
import ru.otus.lesson09.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author ajp
 * @date 04.07.2021
 */
@Repository
public class GenreRepositoryJpa implements GenreRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public GenreRepositoryJpa(final EntityManager entityManager) {
   this.entityManager = entityManager;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Genre> getById(long id) {
    return Optional.ofNullable(entityManager.find(Genre.class, id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Genre> getAll() {
    return entityManager
        .createQuery("select g from Genre g", Genre.class)
        .getResultList();
  }
}
