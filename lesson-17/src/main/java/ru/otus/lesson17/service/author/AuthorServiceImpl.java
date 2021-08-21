package ru.otus.lesson17.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson17.model.Author;
import ru.otus.lesson17.repositories.author.AuthorRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository authorRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Author> getById(final long id) {
    return authorRepository.getById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Author> findAll() {
    return authorRepository.findAll();
  }

  @Override
  public Author save(final String name) {
    return authorRepository.save(new Author(0, name));
  }

  @Override
  public List<Author> getAuthorByIds(final List<Long> authorId) {
    return authorRepository.getAuthorByIds(authorId);
  }
}
