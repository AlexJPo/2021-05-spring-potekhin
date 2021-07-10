package ru.otus.lesson11.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson11.model.Genre;
import ru.otus.lesson11.repositories.genre.GenreRepository;

import java.util.List;

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
  public Genre getById(final long id) {
    return genreRepository.getById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Genre> getAll() {
    return genreRepository.findAll();
  }
}
