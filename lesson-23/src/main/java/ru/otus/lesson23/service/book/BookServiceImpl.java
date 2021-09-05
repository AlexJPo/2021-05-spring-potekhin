package ru.otus.lesson23.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson23.model.Book;
import ru.otus.lesson23.model.Comment;
import ru.otus.lesson23.repositories.book.BookRepository;
import ru.otus.lesson23.service.author.AuthorService;
import ru.otus.lesson23.service.genre.GenreService;

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
  public String deleteById(final long id) {
    final Optional<Book> book = bookRepository.getById(id);
    if (book.isPresent()) {
      bookRepository.delete(book.get());
      return "Book successful remove";
    }
    return "Book with id = " + id + " not present";
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void save(final String bookTitle,
                   final List<Long> authorIds,
                   final List<Long> genreIds,
                   final String comment) {
    final List<Comment> comments = new ArrayList<>();
    comments.add(new Comment(0, comment));

    final Book book = new Book(0L,
        bookTitle,
        comments,
        authorService.getAuthorByIds(authorIds),
        genreService.getAllGenresByIds(genreIds)
    );
    bookRepository.save(book);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public void update(final long id,
                     final String bookTitle,
                     final List<Long> authorIds,
                     final List<Long> genreIds,
                     final String[] bookComments) {
    final Optional<Book> book = bookRepository.getById(id);
    if (book.isPresent()) {
      final Book updatedBook = book.get();
      updatedBook.setTitle(bookTitle);
      updatedBook.setAuthors(authorService.getAuthorByIds(authorIds));
      updatedBook.setGenres(genreService.getAllGenresByIds(genreIds));

      final List<Comment> comments = new ArrayList<>();
      for (int i = 0; i < bookComments.length;) {
        comments.add(new Comment(Long.parseLong(bookComments[i]), bookComments[i + 1]));
        i += 2;
      }
      updatedBook.setComments(comments);

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

}
