package ru.otus.lesson04.service.validator;

import org.springframework.stereotype.Service;
import ru.otus.lesson04.config.QuizeProperties;
import ru.otus.lesson04.model.Question;
import ru.otus.lesson04.service.localize.LocalizeService;

import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Service
public class QuizeValidatorImpl implements QuizeValidator {
  private final int correctAnswerValue;
  private final LocalizeService localizeService;

  public QuizeValidatorImpl(final QuizeProperties quizeProperties,
                            LocalizeService localizeService) {
    this.correctAnswerValue = quizeProperties.getCorrectAnswerCounter();
    this.localizeService = localizeService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void validateStudentAnswers(final Map<Question, Integer> studentAnswers) {
    long correctAnswers = studentAnswers
        .entrySet()
        .stream()
        .filter(k -> k.getKey().getCorrectAnswer().equals(k.getValue()))
        .count();

    int totalQuestion = studentAnswers.size();
    System.out.println(
        localizeService.translate("validator.give-answer",
            new String[] {
                String.valueOf(correctAnswers),
                String.valueOf(totalQuestion)
        })
    );

    if (correctAnswers >= correctAnswerValue) {
      System.out.println(localizeService.translate("validator.complete.success"));
    }
  }
}