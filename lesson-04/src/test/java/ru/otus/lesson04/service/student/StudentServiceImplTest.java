package ru.otus.lesson04.service.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lesson04.model.Student;
import ru.otus.lesson04.service.reader.InputReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@SpringBootTest
class StudentServiceImplTest {
  @Autowired
  private StudentService studentService;

  @MockBean
  private InputReader inputReader;

  @Test
  void register() {
    when(inputReader.getInput()).thenReturn("test");

    Student result = studentService.register();
    assertNotNull(result.getName());
    assertNotNull(result.getSurname());

    assertEquals("test", result.getName());
    assertEquals("test", result.getSurname());
  }
}