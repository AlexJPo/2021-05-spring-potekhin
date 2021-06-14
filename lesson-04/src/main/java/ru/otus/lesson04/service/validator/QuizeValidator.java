package ru.otus.lesson04.service.validator;

import ru.otus.lesson04.model.Question;

import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
public interface QuizeValidator {
  /**
   * Валидация ответов студента
   *
   * @param studentAnswers данные
   * @return класс опроса
   */
  void validateStudentAnswers(Map<Question, Integer> studentAnswers);
}
