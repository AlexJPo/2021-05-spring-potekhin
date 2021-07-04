package ru.otus.lesson09.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson09.model.Author;
import ru.otus.lesson09.model.Book;
import ru.otus.lesson09.model.Comment;
import ru.otus.lesson09.model.Genre;
import ru.otus.lesson09.repositories.book.BookRepository;
import ru.otus.lesson09.service.author.AuthorService;
import ru.otus.lesson09.service.genre.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ajp
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
  @Transactional
  @Override
  public String getById(final long id) {
    final Optional<Book> book = bookRepository.getById(id);
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
    final Optional<Book> book = bookRepository.getById(id);
    if (book.isPresent()) {
      bookRepository.deleteById(id);
      return "Book successful remove";
    }
    return "Book with id = " + id + " not present";
  }

  @Override
  public long getTotalRows() {
    return 0;
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
    final Optional<Book> book = bookRepository.getById(id);
    if (book.isPresent()) {
      bookRepository.updateTitleById(id, bookTitle);
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
    final String authors = book.getAuthors()
        .stream()
        .map(Author::getName)
        .collect(Collectors.joining(", "));

    final String genres = book.getGenres()
        .stream()
        .map(Genre::getTitle)
        .collect(Collectors.joining(", "));

    final String comments = book.getComments()
        .stream()
        .map(Comment::getText)
        .collect(Collectors.joining(", "));

    return String.format("Book title: %s\nAuthors: %s\nGenres: %s\nComments: %s\n----------------\n",
        book.getTitle(),
        authors,
        genres,
        comments);
  }

}
