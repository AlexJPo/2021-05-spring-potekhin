package ru.otus.bookapp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.bookapp.dao.book.BookDao;
import ru.otus.bookapp.domain.Author;
import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.domain.Genre;
import ru.otus.bookapp.dto.BookDTO;
import ru.otus.bookapp.exception.NotFoundRowException;
import ru.otus.bookapp.service.author.AuthorService;
import ru.otus.bookapp.service.genre.GenreService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
@SpringBootTest
class BookServiceImplTest {
  @Autowired
  private BookService bookService;

  @MockBean
  private BookDao bookDao;
  @MockBean
  private AuthorService authorService;
  @MockBean
  private GenreService genreService;

  @DisplayName("Пустой объект, если нет книги с таким id")
  @Test
  void getById_ReturnEmptyObject() {
    BookDTO emptyDTO = new BookDTO(null, null, null, null);
    when(bookDao.getById(anyLong())).thenThrow(NotFoundRowException.class);
    assertThat(bookService.getById(1L))
        .usingRecursiveComparison().isEqualTo(emptyDTO);
  }

  @DisplayName("книга найдена")
  @Test
  void getById_ReturnObject() {
    when(bookDao.getById(anyLong())).thenReturn(new Book(1L, "test book", 1L, 1L));

    final Author author = new Author(1L, "test author");
    when(authorService.getById(anyLong())).thenReturn(author);

    final Genre genre = new Genre(1L, "test genre");
    when(genreService.getById(anyLong())).thenReturn(genre);

    BookDTO result = bookService.getById(1L);
    assertNotNull(result.getId());
    assertNotNull(result.getTitle());
    assertNotNull(result.getAuthor());
    assertNotNull(result.getGenre());

    assertEquals(result.getAuthor(), author);
    assertEquals(result.getGenre(), genre);
  }

  @DisplayName("Ошибка при удалении")
  @Test
  void deleteById_ReturnError() {
    long id = 5L;
    when(bookDao.getById(anyLong())).thenThrow(NotFoundRowException.class);
    assertEquals("Book with id = " + id + " not present", bookService.deleteById(id));
  }

  @DisplayName("Успешное удаление")
  @Test
  void deleteById_Success() {
    assertEquals("Book successful remove", bookService.deleteById(1L));
  }

  @DisplayName("Не найден автор при добавлении")
  @Test
  void insert_AuthorException() {
    long authorId = 1L;
    when(authorService.getById(anyLong())).thenThrow(NotFoundRowException.class);
    assertEquals("Author with id = " + authorId + " not present",
        bookService.insert("test book", authorId, 1L));
  }

  @DisplayName("Не найден жанр при добавлении")
  @Test
  void insert_GenreException() {
    long genreId = 1L;
    when(genreService.getById(anyLong())).thenThrow(NotFoundRowException.class);
    assertEquals("Genre with id = " + genreId + " not present",
        bookService.insert("test book", 1L, genreId));
  }

  @DisplayName("Успешное добавление")
  @Test
  void insert_Success() {
    assertEquals("Successful insert",
        bookService.insert("test book", 1L, 1L));
  }

  @DisplayName("Не найдена книга при обновлении")
  @Test
  void update_BookException() {
    long bookId = 1L;
    when(bookDao.getById(anyLong())).thenThrow(NotFoundRowException.class);
    assertEquals("Book with id = " + bookId + " not present",
        bookService.update(bookId, "test book", 1L, 1L));
  }

  @DisplayName("Не найден автор при обновлении")
  @Test
  void update_AuthorException() {
    long authorId = 1L;
    when(authorService.getById(anyLong())).thenThrow(NotFoundRowException.class);
    assertEquals("Author with id = " + authorId + " not present",
        bookService.update(1L, "test book", authorId, 1L));
  }

  @DisplayName("Не найден жанр при обновлении")
  @Test
  void update_GenreException() {
    long genreId = 1L;
    when(genreService.getById(anyLong())).thenThrow(NotFoundRowException.class);
    assertEquals("Genre with id = " + genreId + " not present",
        bookService.update(1L,"test book", 1L, genreId));
  }

  @DisplayName("Успешное обновление")
  @Test
  void update_Success() {
    when(authorService.getById(anyLong())).thenReturn(new Author(1L, "Vinsbur"));
    when(genreService.getById(anyLong())).thenReturn(new Genre(1L, "Detective"));
    assertEquals("Successful update",
        bookService.update(1L,"test book", 1L, 1L));
  }
}