package ru.otus.lesson11.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.repositories.author.AuthorRepository;

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
    return authorRepository.findById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Author> getAll() {
    return authorRepository.findAll();
  }
}
