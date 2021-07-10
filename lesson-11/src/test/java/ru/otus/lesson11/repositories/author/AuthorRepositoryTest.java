package ru.otus.lesson11.repositories.author;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@DataJpaTest
@Import(AuthorRepository.class)
@EnableJpaRepositories
class AuthorRepositoryTest {
  private static final int EXPECTED_NUMBER_OF_AUTHORS = 4;

  @Autowired
  private AuthorRepository repositoryJpa;

  @DisplayName("Вернёт пустого автора")
  @Test
  void getById_ReturnEmpty() {
    val genre = repositoryJpa.getById(10L);
    assertNull(genre);
  }

  @DisplayName("Вернёт существующего автора")
  @Test
  void getById_ReturnPresentObject() {
    val genre = repositoryJpa.getById(1L);
    assertNotNull(genre);
  }

  @DisplayName("вернёт всех авторов авторы")
  @Test
  void getAll() {
    val genres = repositoryJpa.findAll();

    assertThat(genres)
        .isNotNull()
        .hasSize(EXPECTED_NUMBER_OF_AUTHORS)
        .allMatch(s -> !s.getName().equals(""));
  }
}