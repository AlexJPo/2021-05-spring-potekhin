package ru.otus.lesson05.service.parser;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.otus.lesson05.config.CSVProperties;
import ru.otus.lesson05.model.Question;
import ru.otus.lesson05.model.Quize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Service
public class QuizeParserServiceImpl implements QuizeParserService {
  private final int csvFieldMinSize;
  private final String fieldSeparator;
  private final String answerVariantSeparator;

  public QuizeParserServiceImpl(final CSVProperties csvProperties) {
    csvFieldMinSize = csvProperties.getFieldMinSize();
    fieldSeparator = csvProperties.getSeparator();
    answerVariantSeparator = csvProperties.getAnswerVariantSeparator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Quize parse(List<String> data) {
    final Quize emptyQuize = new Quize(new ArrayList<>());
    if (CollectionUtils.isEmpty(data)) {
      return emptyQuize;
    }

    final List<Question> questions = data.stream()
        .filter(StringUtils::hasLength)
        .map(this::convertStrToQuestion)
        .filter(q -> !CollectionUtils.isEmpty(q.getVariantAnswers()))
        .collect(Collectors.toList());

    if (questions.isEmpty()) {
      return emptyQuize;
    }

    return new Quize(questions);
  }

  /**
   * Конвертация данных в модель
   *
   * @param str данные
   * @return объект вопроса
   * @see Question
   */
  private Question convertStrToQuestion(final String str) {
    final String[] questionTempData = str.split(fieldSeparator);
    if (questionTempData.length != csvFieldMinSize) {
      return new Question("", new ArrayList<>(), 0);
    }

    final String question = questionTempData[0];
    final List<String> variantAnswers = Arrays.asList(questionTempData[1].split(answerVariantSeparator));

    try {
      final Integer correctAnswer = Integer.valueOf(questionTempData[2]);
      return new Question(question, variantAnswers, correctAnswer);
    } catch (NumberFormatException ex) {
      return new Question("", new ArrayList<>(), 0);
    }
  }
}