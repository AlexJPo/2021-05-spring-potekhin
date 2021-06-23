package ru.otus.lesson05.service.validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.lesson05.model.Question;
import ru.otus.lesson05.model.QuizeResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@SpringBootTest
@ContextConfiguration(classes = QuizeCalculatorImpl.class)
class QuizeCalculatorImplTest {
  @Autowired
  private QuizeCalculator quizeCalculator;

  @Test
  void calculatePoints_returnEmpty() {
    QuizeResult result = quizeCalculator.calculatePoints(null);
    assertEquals(0, result.getCorrectAnswer());
    assertEquals(0, result.getTotalQuestion());

    result = quizeCalculator.calculatePoints(new HashMap<>());
    assertEquals(0, result.getCorrectAnswer());
    assertEquals(0, result.getTotalQuestion());
  }

  @Test
  void calculatePoints_returnResult() {
    final Map<Question, Integer> studentAnswers = new HashMap<>();
    final Question question = new Question("Java is program language?", Arrays.asList("y", "n"), 1);
    studentAnswers.put(question, 2);

    QuizeResult result = quizeCalculator.calculatePoints(studentAnswers);
    assertEquals(0, result.getCorrectAnswer());
    assertEquals(1, result.getTotalQuestion());

    studentAnswers.clear();
    studentAnswers.put(question, 1);

    result = quizeCalculator.calculatePoints(studentAnswers);
    assertEquals(1, result.getCorrectAnswer());
    assertEquals(1, result.getTotalQuestion());
  }
}