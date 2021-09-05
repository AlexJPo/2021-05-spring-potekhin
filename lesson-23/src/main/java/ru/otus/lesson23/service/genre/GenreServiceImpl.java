package ru.otus.lesson23.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson23.model.Genre;
import ru.otus.lesson23.repositories.genre.GenreRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
  private final GenreRepository genreRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Genre> getById(final long id) {
    return genreRepository.getById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Genre> findAll() {
    return genreRepository.findAll();
  }

  @Override
  public List<Genre> getAllGenresByIds(List<Long> genreId) {
    return genreRepository.getAllGenresByIds(genreId);
  }
}
