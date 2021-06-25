package ru.otus.lesson05.service.student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.lesson05.model.Student;
import ru.otus.lesson05.service.localize.LocalizeService;
import ru.otus.lesson05.service.reader.InputReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@SpringBootTest
@ContextConfiguration(classes = { StudentServiceImpl.class })
class StudentServiceImplTest {

  @Autowired
  private StudentService studentService;

  @MockBean
  private InputReader inputReader;
  @MockBean
  private LocalizeService localizeService;

  @DisplayName("Студент зарегистрирован")
  @Test
  void register() {
    final Student student = new Student("Aleksey", "Potekhin");
    when(inputReader.getInput()).thenReturn(student.getName(), student.getSurname());

    final Student result = studentService.register();
    assertNotNull(result.getName());
    assertNotNull(result.getSurname());

    assertEquals(student.getName(), result.getName());
    assertEquals(student.getSurname(), result.getSurname());
  }
}