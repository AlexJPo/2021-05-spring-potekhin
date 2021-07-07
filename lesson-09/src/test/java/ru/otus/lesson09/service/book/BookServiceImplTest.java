package ru.otus.lesson09.service.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lesson09.model.Author;
import ru.otus.lesson09.model.Book;
import ru.otus.lesson09.model.Genre;
import ru.otus.lesson09.repositories.book.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * @author ajp
 * @date 07.07.2021
 */
@SpringBootTest
class BookServiceImplTest {

  @Autowired
  private BookService bookService;
  @MockBean
  private BookRepository bookRepository;

  @DisplayName("Книга не найдена по id")
  @Test
  void getById_notFoundBook() {
    when(bookRepository.getById(anyLong())).thenReturn(Optional.empty());

    final long bookId = 0L;
    assertEquals("Book with id = " + bookId + " not present", bookService.getById(bookId));
  }

  @DisplayName("Книга найдена по id")
  @Test
  void getById_FoundBook() {
    final Book book = new Book();
    book.setTitle("Test book");
    book.setAuthors(new ArrayList<>());
    book.setGenres(new ArrayList<>());
    book.setComments(new ArrayList<>());

    when(bookRepository.getById(anyLong())).thenReturn(Optional.of(book));

    final long bookId = 0L;
    assertEquals("Book title: Test book\nAuthors: no authors\nGenres: no genres\nComments: without comment\n----------------\n",
        bookService.getById(bookId));
  }

  @DisplayName("Книга не найдена по id при удалении")
  @Test
  void deleteById_Error() {
    when(bookRepository.getById(anyLong())).thenReturn(Optional.empty());

    final long bookId = 0L;
    assertEquals("Book with id = " + bookId + " not present", bookService.deleteById(bookId));
  }

  @DisplayName("Книга удалена")
  @Test
  void deleteById_Success() {
    when(bookRepository.getById(anyLong())).thenReturn(Optional.of(new Book()));

    final long bookId = 1L;
    assertEquals("Book successful remove", bookService.deleteById(bookId));
  }

  @DisplayName("Книга сохранена")
  @Test
  void save() {
    when(bookRepository.save(anyObject())).thenReturn(new Book());
    final String bookTitle = "my saved book";

    assertEquals("Book '" + bookTitle + "' successful save", bookService.save(bookTitle));
  }

  @DisplayName("Книга не была обновлена")
  @Test
  void update_Error() {
    when(bookRepository.getById(anyLong())).thenReturn(Optional.empty());

    final long bookId = 1L;
    assertEquals("Book with id = " + bookId + " not present", bookService.update(bookId, "New book title"));
  }

  @DisplayName("Книга была обновлена")
  @Test
  void update_Success() {
    when(bookRepository.getById(anyLong())).thenReturn(Optional.of(new Book()));

    final long bookId = 1L;
    assertEquals("Book successful update", bookService.update(bookId, "New book title"));
  }

  @DisplayName("Пустая книга")
  @Test
  void getAllBooks_EmptyBook() {
    final Book book = new Book();
    book.setTitle("Test book");
    when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

    assertEquals("Book title: Test book\nAuthors: no authors\nGenres: no genres\nComments: without comment\n----------------\n",
        bookService.getAllBooks().get(1));
  }

  @DisplayName("Заполненная книга")
  @Test
  void getAllBooks_FilledBook() {
    final Book book = new Book();
    book.setTitle("Test book");
    book.setAuthors(Arrays.asList(new Author(1L, "Aleksey"), new Author(2L, "Boris")));
    book.setGenres(Arrays.asList(new Genre(1L, "Comedy"), new Genre(2L, "Fantasy")));
    book.setComments(new ArrayList<>());

    when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

    assertEquals("Book title: Test book\n" +
            "Authors: Aleksey, Boris" +
            "\nGenres: Comedy, Fantasy" +
            "\nComments: without comment\n----------------\n",
        bookService.getAllBooks().get(1));
  }
}