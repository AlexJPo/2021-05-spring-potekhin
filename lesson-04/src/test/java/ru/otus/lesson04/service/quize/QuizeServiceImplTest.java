package ru.otus.lesson04.service.quize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lesson04.model.Question;
import ru.otus.lesson04.service.localize.LocalizeService;
import ru.otus.lesson04.service.reader.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Aleksey.Potekhin
 * @date 18.06.2021
 */
@SpringBootTest
class QuizeServiceImplTest {

  @Autowired
  private QuizeService quizeService;

  @MockBean
  private LocalizeService localizeService;
  @MockBean
  private InputReader inputReader;

  @DisplayName("Верёнт пустой список, если отсутвуют вопросы вопросов")
  @Test
  void startReturnEmpty() {
    assertTrue(quizeService.start(new ArrayList<>()).isEmpty());
  }

  @DisplayName("Вернёт ответы студента")
  @Test
  void startReturnResult() {
    final List<Question> questions = new ArrayList<>();
    questions.add(new Question("Java is program language?", Arrays.asList("y", "n"), 1));
    questions.add(new Question("Sum two digits and write result: 5 + 10?", Arrays.asList("6", "5", "4", "15"), 4));

    when(inputReader.getInput()).thenReturn("1","4");
    Map<Question, Integer> result = quizeService.start(questions);
    assertFalse(result.isEmpty());
    verify(inputReader, times(2)).getInput();

    when(inputReader.getInput()).thenReturn("y", "1", "4");
    result = quizeService.start(questions);
    assertFalse(result.isEmpty());
    verify(inputReader, times(5)).getInput();
  }
}