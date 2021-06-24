package ru.otus.bookapp.dao.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.exception.NotFoundRowException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author ajp
 * @date 24.06.2021
 */
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
  private static final long EXISTING_BOOK_ID = 1L;

  @Autowired
  private BookDaoJdbc bookDaoJdbc;

  @DisplayName("Выброс исключения, если нет такой книги")
  @Test
  public void findById_ThrowException() {
    assertThatThrownBy(() -> bookDaoJdbc.findById(0L))
        .isInstanceOf(NotFoundRowException.class);
  }

  @DisplayName("Выброс исключения, если нет такой книги")
  @Test
  public void findById_ReturnBook() {
    assertThatCode(() -> bookDaoJdbc.findById(EXISTING_BOOK_ID))
        .doesNotThrowAnyException();
  }

  @DisplayName("Добавление книги")
  @Test
  public void insertBook() {
    final Book insertBook = new Book(bookDaoJdbc.nextId(), "Book insert test", 1L, 1L);
    bookDaoJdbc.insert(insertBook);

    final Book findBook = bookDaoJdbc.findById(insertBook.getId());
    assertThat(findBook).usingRecursiveComparison().isEqualTo(insertBook);
  }

  @DisplayName("Изменение данных книги")
  @Test
  public void updateBook() {
    assertThatCode(() -> bookDaoJdbc.findById(EXISTING_BOOK_ID))
        .doesNotThrowAnyException();

    final Book oldBook = bookDaoJdbc.findById(EXISTING_BOOK_ID);
    final Book book = new Book(oldBook.getId(), "Book update test", 2L, 2L);
    bookDaoJdbc.update(book);

    final Book newBook = bookDaoJdbc.findById(oldBook.getId());
    assertThat(newBook).usingRecursiveComparison().isNotEqualTo(oldBook);
  }

  @DisplayName("Удаление книги")
  @Test
  public void deleteBook() {
    assertThatCode(() -> bookDaoJdbc.findById(EXISTING_BOOK_ID))
        .doesNotThrowAnyException();

    bookDaoJdbc.deleteById(EXISTING_BOOK_ID);

    assertThatCode(() -> bookDaoJdbc.findById(EXISTING_BOOK_ID))
        .isInstanceOf(NotFoundRowException.class);
  }

  @DisplayName("Все книги")
  @Test
  public void getAll() {
    final Book expectedPerson = bookDaoJdbc.findById(EXISTING_BOOK_ID);
    List<Book> actualPersonList = bookDaoJdbc.getAll();
    assertThat(actualPersonList)
        .usingFieldByFieldElementComparator()
        .containsExactlyInAnyOrder(expectedPerson);
  }

}