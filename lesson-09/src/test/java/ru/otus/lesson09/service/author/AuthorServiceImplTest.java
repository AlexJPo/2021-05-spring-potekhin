package ru.otus.lesson09.service.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lesson09.model.Author;
import ru.otus.lesson09.repositories.author.AuthorRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author ajp
 * @date 07.07.2021
 */
@SpringBootTest
class AuthorServiceImplTest {

  @Autowired
  private AuthorService authorService;
  @MockBean
  private AuthorRepository authorRepository;

  @DisplayName("Отсутвует автор при поиске по ID")
  @Test
  void getById_ReturnEmpty() {
    when(authorRepository.getById(anyLong())).thenReturn(Optional.empty());
    assertTrue(authorService.getById(0L).isEmpty());
  }

  @DisplayName("Отсутвует автор при поиске по ID")
  @Test
  void getById_ReturnEntity() {
    when(authorRepository.getById(anyLong()))
        .thenReturn(Optional.of(new Author(1L, "Test author")));

    final Optional<Author> author = authorService.getById(1L);
    assertTrue(author.isPresent());
    assertNotNull(author.get());
  }

}