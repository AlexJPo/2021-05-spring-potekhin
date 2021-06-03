package ru.otus.homework.service.csv.parser;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.Quize;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 */
public class QuizeParserServiceImpl implements QuizeParserService {
  private static final int CSV_FIELD_MIN_SIZE = 3;
  private static final String FIELD_SEPARATOR = ";";
  private static final String ANSWER_VARIANT_SEPARATOR = ",";

  /**
   * Конвертация данных для опроса
   *
   * @param data данные
   * @return класс опроса
   */
  @Override
  public Quize parse(List<String> data) {
    final Quize quize = new Quize();
    if (CollectionUtils.isEmpty(data)) {
      return quize;
    }

    final List<Question> questions = data.stream()
        .filter(str -> !StringUtils.isEmpty(str))
        .map(this::convertStrToQuestion)
        .filter(q -> !CollectionUtils.isEmpty(q.getVariantAnswers()))
        .collect(Collectors.toList());

    if (questions.isEmpty()) {
      return quize;
    }

    quize.setQuestions(questions);
    return quize;
  }

  /**
   * Конвертация данных в модель
   *
   * @param str данные
   * @return объект вопроса
   * @see Question
   */
  private Question convertStrToQuestion(final String str) {
    final Question question = new Question();
    final String[] questionTempData = str.split(FIELD_SEPARATOR);

    if (questionTempData.length != CSV_FIELD_MIN_SIZE) {
      return question;
    }

    question.setQuestion(questionTempData[0]);
    question.setVariantAnswers(Arrays.asList(questionTempData[1].split(ANSWER_VARIANT_SEPARATOR)));
    question.setCorrectAnswer(questionTempData[2]);
    return question;
  }
}
