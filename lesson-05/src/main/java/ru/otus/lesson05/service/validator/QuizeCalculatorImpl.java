package ru.otus.lesson05.service.validator;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.otus.lesson05.model.Question;
import ru.otus.lesson05.model.QuizeResult;

import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Service
public class QuizeCalculatorImpl implements QuizeCalculator {

  /**
   * {@inheritDoc}
   */
  @Override
  public QuizeResult calculatePoints(final Map<Question, Integer> studentAnswers) {
    if (CollectionUtils.isEmpty(studentAnswers)) {
      return new QuizeResult(0,0);
    }

    long correctAnswers = studentAnswers
        .entrySet()
        .stream()
        .filter(k -> k.getKey().getCorrectAnswer().equals(k.getValue()))
        .count();

    return new QuizeResult((int)correctAnswers, studentAnswers.size());
  }
}