package ru.otus.homework.service.validate;

import ru.otus.homework.model.Question;

import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 09.06.2021
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
