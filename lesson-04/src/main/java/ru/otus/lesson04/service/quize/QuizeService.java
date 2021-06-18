package ru.otus.lesson04.service.quize;

import ru.otus.lesson04.model.Question;

import java.util.List;
import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
public interface QuizeService {
  /**
   * Проведение опроса для студента
   *
   * @param questions вопросы
   * @return ответы на вопросы
   */
  Map<Question, Integer> start(List<Question> questions);
}
