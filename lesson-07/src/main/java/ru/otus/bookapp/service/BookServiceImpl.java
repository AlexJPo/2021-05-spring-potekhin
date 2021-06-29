package ru.otus.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.bookapp.dao.book.BookDao;
import ru.otus.bookapp.domain.Author;
import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.domain.Genre;
import ru.otus.bookapp.dto.BookDTO;
import ru.otus.bookapp.exception.NotFoundRowException;
import ru.otus.bookapp.service.author.AuthorService;
import ru.otus.bookapp.service.genre.GenreService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookDao bookDao;
  private final AuthorService authorService;
  private final GenreService genreService;

  /**
   * {@inheritDoc}
   */
  @Override
  public BookDTO getById(final long id) {
    try {
      final Book book = bookDao.getById(id);
      final Author author = authorService.getById(book.getAuthorId());
      final Genre genre = genreService.getById(book.getGenreId());
      return new BookDTO(book.getId(), book.getTitle(), author, genre);
    } catch (NotFoundRowException re) {
      return new BookDTO(null, null, null, null);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String deleteById(final long id) {
    try {
      bookDao.getById(id);
      bookDao.deleteById(id);
      return "Book successful remove";
    } catch (NotFoundRowException re) {
      return "Book with id = " + id + " not present";
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String insert(final String bookTitle, final long authorId, final long genreId) {
    final Author author = authorService.getById(authorId);
    if (author == null) {
      return "Author with id = " + authorId + " not present";
    }

    final Genre genre = genreService.getById(genreId);
    if (genre == null) {
      return "Genre with id = " + genreId + " not present";
    }

    Book insertBook = new Book(bookDao.nextId(), bookTitle, authorId, genreId);
    bookDao.insert(insertBook);
    return "Successful insert";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String update(final long id, final String bookTitle, final long authorId, final long genreId) {
    try {
      bookDao.getById(id);
    } catch (NotFoundRowException re) {
      return "Book with id = " + id + " not present";
    }

    final Author author;
    try {
      author = authorService.getById(authorId);
    } catch (NotFoundRowException re) {
      return "Author with id = " + authorId + " not present";
    }

    final Genre genre;
    try {
      genre = genreService.getById(genreId);
    } catch (NotFoundRowException re) {
      return "Genre with id = " + genreId + " not present";
    }

    bookDao.update(new Book(id, bookTitle, author.getId(), genre.getId()));
    return "Successful update";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getTotalRows() {
    return bookDao.count();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Book> getAllBooks() {
    return bookDao.getAll();
  }
}
