package ru.otus.lesson05.service.validator;

import ru.otus.lesson05.model.Question;
import ru.otus.lesson05.model.QuizeResult;

import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
public interface QuizeCalculator {
  /**
   * Подсчёт ответов студента
   *
   * @param studentAnswers ответы на вопросы
   * @return результат опроса
   */
  QuizeResult calculatePoints(Map<Question, Integer> studentAnswers);
}
