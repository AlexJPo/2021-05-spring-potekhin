package ru.otus.lesson09.repositories.author;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {
  private static final int EXPECTED_NUMBER_OF_AUTHORS = 4;

  @Autowired
  private AuthorRepositoryJpa repositoryJpa;

  @DisplayName("Вернёт пустого автора")
  @Test
  void getById_ReturnEmpty() {
    val genre = repositoryJpa.getById(10L);
    assertFalse(genre.isPresent());
  }

  @DisplayName("Вернёт существующего автора")
  @Test
  void getById_ReturnPresentObject() {
    val genre = repositoryJpa.getById(1L);
    assertTrue(genre.isPresent());
  }

  @DisplayName("вернёт всех авторов авторы")
  @Test
  void getAll() {
    val genres = repositoryJpa.getAll();

    assertThat(genres)
        .isNotNull()
        .hasSize(EXPECTED_NUMBER_OF_AUTHORS)
        .allMatch(s -> !s.getName().equals(""));
  }
}