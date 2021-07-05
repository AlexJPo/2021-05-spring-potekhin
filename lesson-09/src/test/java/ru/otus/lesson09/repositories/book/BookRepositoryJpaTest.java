package ru.otus.lesson09.repositories.book;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.lesson09.model.Author;
import ru.otus.lesson09.model.Book;
import ru.otus.lesson09.model.Comment;
import ru.otus.lesson09.model.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {
  private static final int EXPECTED_NUMBER_OF_BOOKS = 2;
  private static final long EXPECTED_QUERIES_COUNT = 4;
  private static final String BOOK_TITLE = "Updated book title";

  @Autowired
  private BookRepositoryJpa repositoryJpa;

  @Autowired
  private TestEntityManager em;

  @DisplayName("Вернёт пустую книгу")
  @Test
  void getById_ReturnEmpty() {
    val book = repositoryJpa.getById(10L);
    assertFalse(book.isPresent());
  }

  @DisplayName("Вернёт существующею книгу")
  @Test
  void getById_ReturnPresentObject() {
    val book = repositoryJpa.getById(1L);
    assertTrue(book.isPresent());
  }

  @DisplayName("Должен загрузисть список книг с полной информацией о ней")
  @Test
  void shouldReturnCorrectStudentsListWithAllInfo() {
    SessionFactory sessionFactory = em.getEntityManager()
        .getEntityManagerFactory()
        .unwrap(SessionFactory.class);

    sessionFactory
        .getStatistics()
        .setStatisticsEnabled(true);


    System.out.println("\n\n\n\n---------------------------------------------------");
    val books = repositoryJpa.findAll();
    assertThat(books)
        .isNotNull()
        .hasSize(EXPECTED_NUMBER_OF_BOOKS)
        .allMatch(s -> !s.getTitle().equals(""))
        .allMatch(s -> s.getAuthors() != null && s.getAuthors().size() > 0)
        .allMatch(s -> s.getGenres() != null && s.getGenres().size() > 0)
        .allMatch(s -> s.getComments() != null && s.getComments().size() > 0);
    System.out.println("-----------------------------------------------------------\n\n\n\n");
    assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
  }

  @DisplayName(" должен корректно сохранять только информацию по книге")
  @Test
  void savePartly() {
    val book = new Book(0, "test book", null, null, null);
    repositoryJpa.save(book);
    assertThat(book.getId()).isGreaterThan(0);

    val actualStudent = em.find(Book.class, book.getId());
    assertThat(actualStudent)
        .isNotNull()
        .matches(s -> !s.getTitle().equals(""))
        .matches(s -> s.getGenres() == null)
        .matches(s -> s.getAuthors() == null)
        .matches(s -> s.getComments() == null);
  }

  @DisplayName(" должен корректно сохранять всю информацию о книге")
  @Test
  void saveFull() {
    val author = Arrays.asList(new Author[] {
        new Author(0, "test author")
    });
    val genre = Arrays.asList(new Genre[] {
        new Genre(0, "test genre")
    });
    val comment = Arrays.asList(new Comment[] {
        new Comment(0, "comment first"),
        new Comment(0, "comment second")
    });

    val book = new Book(0, "full save book", comment, author, genre);
    repositoryJpa.save(book);
    assertThat(book.getId()).isGreaterThan(0);

    val actualStudent = em.find(Book.class, book.getId());
    assertThat(actualStudent)
        .isNotNull().matches(s -> !s.getTitle().equals(""))
        .matches(s -> s.getComments() != null && s.getComments().size() > 0)
        .matches(s -> s.getAuthors() != null && s.getAuthors().size() > 0)
        .matches(s -> s.getGenres() != null && s.getGenres().size() > 0);
  }

  @DisplayName("Изменение названия книги по id")
  @Test
  void update() {
    final long firstBookId = 1L;
    val firstBook = em.find(Book.class, firstBookId);
    final String oldTitle = firstBook.getTitle();
    em.detach(firstBook);

    repositoryJpa.updateTitleById(firstBookId, BOOK_TITLE);
    val updatedBook = em.find(Book.class, firstBookId);

    assertThat(updatedBook.getTitle())
        .isNotEqualTo(oldTitle)
        .isEqualTo(BOOK_TITLE);
  }

  @DisplayName("Удаление книги по id")
  @Test
  void delete() {
    final long firstBookId = 1L;
    val firstBook = em.find(Book.class, firstBookId);
    assertThat(firstBook).isNotNull();
    em.detach(firstBook);

    repositoryJpa.deleteById(firstBookId);
    val deletedBook = em.find(Book.class, firstBookId);

    assertThat(deletedBook).isNull();
  }
}