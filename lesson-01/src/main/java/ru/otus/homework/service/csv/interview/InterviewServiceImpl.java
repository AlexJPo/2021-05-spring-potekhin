package ru.otus.homework.service.csv.interview;

import ru.otus.homework.model.Question;
import ru.otus.homework.model.Quize;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 */
public class InterviewServiceImpl implements InterviewService {
  private static final String NEXT_POSITION = "   %s) %s,";
  private static final String LAST_POSITION = "   %s) %s\n";

  /**
   * Запуск процесса опроса
   *
   * @param quize даныне для опроса
   */
  @Override
  public void start(final Quize quize) {
    int questionCounter = 1;
    System.out.println("\nStart interview");

    for (Question question : quize.getQuestions()) {
      System.out.println(questionCounter + ". " + question.getQuestion());

      List<String> variants = question.getVariantAnswers();
      for (int i = 0, l = variants.size(); i < l; i++) {
        System.out.println(prepareVariantString(i, l, variants.get(i)));
      }
      questionCounter++;
    }
  }

  /**
   * Формирование строка с вариантов ответа
   *
   * @param counter счётчик
   * @param collectionSize размер коллекции
   * @param val вариант ответа
   * @return форматированная строка
   */
  private String prepareVariantString(final int counter, final int collectionSize, final String val) {
    final String pattern = counter < collectionSize - 1
        ? NEXT_POSITION
        : LAST_POSITION;
    return String.format(pattern, (counter + 1), val);
  }
}
