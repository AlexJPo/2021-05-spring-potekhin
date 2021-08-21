package ru.otus.lesson17.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson17.dto.BookDTO;
import ru.otus.lesson17.exception.BadRequestException;
import ru.otus.lesson17.model.Book;
import ru.otus.lesson17.model.Comment;
import ru.otus.lesson17.repositories.book.BookRepository;
import ru.otus.lesson17.service.author.AuthorService;
import ru.otus.lesson17.service.genre.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final AuthorService authorService;
  private final GenreService genreService;

  /**
   * {@inheritDoc}
   */
  @Override
  public Book getById(final long id) {
    return bookRepository.getById(id).get();
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void deleteById(final long id) {
    final Optional<Book> book = bookRepository.getById(id);
    book.ifPresent(bookRepository::delete);
    throw new BadRequestException("Книга с id = " + id + " не найдена");
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void save(final BookDTO bookDTO) {
    validate(bookDTO);

    final Book book = new Book(0L,
        bookDTO.getTitle(),
        new ArrayList<>(bookDTO.getBookComment()),
        authorService.getAuthorByIds(bookDTO.getBookAuthors()),
        genreService.getAllGenresByIds(bookDTO.getBookGenres())
    );
    bookRepository.save(book);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void update(final long id,
                     final BookDTO bookDTO) {
    validate(bookDTO);

    final Optional<Book> book = bookRepository.getById(id);
    if (book.isPresent()) {
      final Book updatedBook = book.get();
      updatedBook.setTitle(bookDTO.getTitle());
      updatedBook.setAuthors(authorService.getAuthorByIds(bookDTO.getBookAuthors()));
      updatedBook.setGenres(genreService.getAllGenresByIds(bookDTO.getBookGenres()));
      updatedBook.setComments(new ArrayList<>(bookDTO.getBookComment()));

      bookRepository.update(updatedBook);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  /**
   * Валидация данных
   *
   * @param bookDTO книга
   * @throws BadRequestException если не заданы обязательные параметры
   */
  private void validate(final BookDTO bookDTO) {
    if (bookDTO == null ) {
      throw new BadRequestException("Некорректные данные для сохранения");
    }

    if (bookDTO.getTitle() == null || "".equals(bookDTO.getTitle().trim())) {
      throw new BadRequestException("Не задано название книги");
    }

    if (bookDTO.getBookAuthors().isEmpty()) {
      throw new BadRequestException("У книги должен быть хотя бы один автор");
    }

    if (bookDTO.getBookGenres().isEmpty()) {
      throw new BadRequestException("У книги должен быть хотя бы один жанр");
    }
  }
}
