package ru.otus.bookapp.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.bookapp.dao.author.AuthorDao;
import ru.otus.bookapp.domain.Author;
import ru.otus.bookapp.exception.NotFoundRowException;

/**
 * @author Aleksey.Potekhin
 * @date 29.06.2021
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
  private final AuthorDao authorDao;

  /**
   * {@inheritDoc}
   */
  @Override
  public Author getById(long authorId) throws NotFoundRowException {
    return authorDao.getById(authorId);
  }

}
