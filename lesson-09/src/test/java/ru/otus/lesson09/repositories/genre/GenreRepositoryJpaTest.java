package ru.otus.lesson09.repositories.genre;

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
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {
  private static final int EXPECTED_NUMBER_OF_GENRES = 4;

  @Autowired
  private GenreRepositoryJpa repositoryJpa;

  @DisplayName("Вернёт пустой жанр")
  @Test
  void getById_ReturnEmpty() {
    val genre = repositoryJpa.getById(10L);
    assertFalse(genre.isPresent());
  }

  @DisplayName("Вернёт существующий жанр")
  @Test
  void getById_ReturnPresentObject() {
    val genre = repositoryJpa.getById(1L);
    assertTrue(genre.isPresent());
  }

  @DisplayName("Все доступные жанры")
  @Test
  void getAll() {
    val genres = repositoryJpa.getAll();

    assertThat(genres)
        .isNotNull()
        .hasSize(EXPECTED_NUMBER_OF_GENRES)
        .allMatch(s -> !s.getTitle().equals(""));
  }
}