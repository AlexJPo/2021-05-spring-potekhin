package ru.otus.lesson16.repositories.genre;

import org.springframework.stereotype.Repository;
import ru.otus.lesson16.model.Genre;

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
  public List<Genre> findAll() {
    return entityManager
        .createQuery("select g from Genre g", Genre.class)
        .getResultList();
  }

  @Override
  public List<Genre> getAllGenresByIds(List<Long> genreId) {
    return findAll().stream()
        .filter(genre -> genreId.contains(genre.getId()))
        .collect(Collectors.toList());
  }
}
