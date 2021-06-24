package ru.otus.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.bookapp.dao.author.AuthorDao;
import ru.otus.bookapp.dao.book.BookDao;
import ru.otus.bookapp.dao.gener.GenreDao;
import ru.otus.bookapp.domain.Author;
import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.domain.Genre;
import ru.otus.bookapp.dto.BookDTO;
import ru.otus.bookapp.exception.NotFoundRowException;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookDao bookDao;
  private final AuthorDao authorDao;
  private final GenreDao genreDao;

  /**
   * {@inheritDoc}
   */
  @Override
  public BookDTO getById(final long id) {
    try {
      final Book book = bookDao.findById(id);
      final Author author = authorDao.findById(book.getAuthorId());
      final Genre genre = genreDao.findById(book.getGenreId());
      return new BookDTO(book.getId(), book.getTitle(), author, genre);
    } catch (NotFoundRowException re) {
      return new BookDTO(null, null, null, null);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteById(final long id) {
    System.out.println("Remove book with 'id' = " + id);

    try {
      bookDao.findById(id);
      bookDao.deleteById(id);
      System.out.println("Book successful remove");
    } catch (NotFoundRowException re) {
      System.out.println("Book with id = " + id + " not present");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insert(final String bookTitle, final long authorId, final long genreId) {
    final Author author = authorDao.findById(authorId);
    if (author == null) {
      System.out.println("Author with id = " + authorId + " not present");
      return;
    }

    final Genre genre = genreDao.findById(genreId);
    if (genre == null) {
      System.out.println("Genre with id = " + genreId + " not present");
      return;
    }

    Book insertBook = new Book(bookDao.nextId(), bookTitle, authorId, genreId);
    bookDao.insert(insertBook);
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
  public void updateAuthor(final long bookId, final long authorId) {
    final Author author = authorDao.findById(authorId);
    if (author == null) {
      System.out.println("Author with id = " + authorId + " not present");
      return;
    }

    Book currentBook;
    try {
      currentBook = bookDao.findById(bookId);
    } catch (NotFoundRowException re) {
      System.out.println("Book with id = " + bookId + " not present");
      return;
    }

    if (currentBook.getAuthorId() == authorId) {
      return;
    }

    final Book insertBook = new Book(currentBook.getId(), currentBook.getTitle(), authorId, currentBook.getGenreId());
    bookDao.update(insertBook);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateGenre(long bookId, long genreId) {
    final Genre author = genreDao.findById(genreId);
    if (author == null) {
      System.out.println("Genre with id = " + genreId + " not present");
      return;
    }

    Book currentBook;
    try {
      currentBook = bookDao.findById(bookId);
    } catch (NotFoundRowException re) {
      System.out.println("Book with id = " + bookId + " not present");
      return;
    }

    if (currentBook.getGenreId() == genreId) {
      return;
    }

    final Book insertBook = new Book(currentBook.getId(), currentBook.getTitle(), currentBook.getAuthorId(), genreId);
    bookDao.update(insertBook);
  }
}
