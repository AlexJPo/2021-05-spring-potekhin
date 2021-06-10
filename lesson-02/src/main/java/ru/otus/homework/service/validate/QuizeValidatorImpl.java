package ru.otus.homework.service.validate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Question;

import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 09.06.2021
 */
@Service
public class QuizeValidatorImpl implements QuizeValidator {
  private final int correctAnswerValue;

  public QuizeValidatorImpl(@Value( "${correct.answer.counter}" ) int correctAnswerCounter) {
    this.correctAnswerValue = correctAnswerCounter;
  }

  /**
   * Валидация ответов студента
   *
   * @param studentAnswers данные
   * @return класс опроса
   */
  @Override
  public void validateStudentAnswers(final Map<Question, Integer> studentAnswers) {
    long correctAnswers = studentAnswers
        .entrySet()
        .stream()
        .filter(k -> k.getKey().getCorrectAnswer().equals(k.getValue()))
        .count();

    int totalQuestion = studentAnswers.size();
    System.out.println("You will give " + correctAnswers + "/" + totalQuestion + " correct answer.");
    if (correctAnswers >= correctAnswerValue) {
      System.out.println("Congratulation!!! All answers is right.");
    }
  }
}
