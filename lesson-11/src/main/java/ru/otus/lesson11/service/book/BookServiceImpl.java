package ru.otus.lesson11.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Comment;
import ru.otus.lesson11.model.Genre;
import ru.otus.lesson11.repositories.book.BookRepository;
import ru.otus.lesson11.repositories.comment.CommentRepository;
import ru.otus.lesson11.service.comment.CommentService;
import ru.otus.lesson11.service.mapper.BookEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final BookEntityMapper bookEntityMapper;
  private final CommentService commentSreService;

  /**
   * {@inheritDoc}
   */
  @Override
  public String getById(final long id) {
    final Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      return bookEntityMapper.map(book.get());
    }
    return "Book with id = " + id + " not present";
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public String deleteById(final long id) {
    final Optional<Book> book = bookRepository.findById(id);
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
  public String save(final String bookTitle, final String[] genres, final String[] authors) {
    Book book = new Book(0L, bookTitle, null, null, null);

    if (authors.length > 0) {
      final List<Author> bookAuthors = new ArrayList<>();
      for (String author : authors) {
        bookAuthors.add(new Author(0L, author));
      }
      book.setAuthors(bookAuthors);
    }

    if (genres.length > 0) {
      final List<Genre> bookGenres = new ArrayList<>();
      for (String genre : genres) {
        bookGenres.add(new Genre(0L, genre));
      }
      book.setGenres(bookGenres);
    }

    bookRepository.save(book);
    return "Book '" + bookTitle + "' successful save";
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public String update(final long id, final String bookTitle, String[] genres, String[] authors) {
    final Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      final Book updatedBook = book.get();
      updatedBook.setTitle(bookTitle);
      bookRepository.save(updatedBook);
      return "Book successful update";
    }
    return "Book with id = " + id + " not present";
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public List<String> getAllBooks() {
    final List<Book> bookList = bookRepository.findAll();
    final List<String> result = new ArrayList<>();
    result.add("\n");

    bookList.forEach(book -> result.add(bookEntityMapper.map(book)));
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public String getComments(final long id) {
    final Optional<Book> finedBook = bookRepository.findById(id);
    if (finedBook.isPresent()) {
      final Book book = finedBook.get();
      String comments;
      if (CollectionUtils.isEmpty(book.getComments())) {
        comments = "no book comments";
      } else {
        comments = book.getComments()
            .stream()
            .map(Comment::getText)
            .collect(Collectors.joining(",\n"));
      }

      return String.format("Book: %s\nComments:\n%s\n----------------\n",
          book.getTitle(),
          comments);
    }
    return "Book with id = " + id + " not present";
  }
}
