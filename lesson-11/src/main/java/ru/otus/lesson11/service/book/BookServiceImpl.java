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

  /**
   * {@inheritDoc}
   */
  @Override
  public String getById(final long id) {
    final Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      return mapBook(book.get());
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
  public String save(final String bookTitle) {
    Book book = new Book(0L, bookTitle, null, null, null);
    bookRepository.save(book);
    return "Book '" + bookTitle + "' successful save";
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public String update(final long id, final String bookTitle) {
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

    bookList.forEach(book -> result.add(mapBook(book)));
    return result;
  }

  private String mapBook(final Book book) {
    String authors;
    if (CollectionUtils.isEmpty(book.getAuthors())) {
      authors = "no authors";
    } else {
      authors = book.getAuthors()
          .stream()
          .map(Author::getName)
          .collect(Collectors.joining(", "));
    }

    String genres;
    if (CollectionUtils.isEmpty(book.getGenres())) {
      genres = "no genres";
    } else {
      genres = book.getGenres()
          .stream()
          .map(Genre::getTitle)
          .collect(Collectors.joining(", "));
    }

    return String.format("Book title: %s\nAuthors: %s\nGenres: %s\n----------------\n",
        book.getTitle(),
        authors,
        genres);
  }

  /**
   * {@inheritDoc}
   */
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
