package ru.otus.bookapp.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.bookapp.dao.genre.GenreDao;
import ru.otus.bookapp.domain.Genre;
import ru.otus.bookapp.exception.NotFoundRowException;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 29.06.2021
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
  private final GenreDao genreDao;

  @Override
  public Genre getById(long genreId) throws NotFoundRowException {
    return genreDao.getById(genreId);
  }

  @Override
  public List<Genre> getAll() {
    return genreDao.getAll();
  }
}
