package ru.otus.lesson09.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson09.model.Genre;
import ru.otus.lesson09.repositories.genre.GenreRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ajp
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
  public List<Genre> getAll() {
    return genreRepository.getAll();
  }
}
