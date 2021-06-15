package ru.otus.lesson04.service.quize;

import org.springframework.stereotype.Service;
import ru.otus.lesson04.model.Question;
import ru.otus.lesson04.service.localize.LocalizeService;
import ru.otus.lesson04.service.reader.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@Service
public class QuizeServiceImpl implements QuizeService {
  private static final String NEXT_POSITION = "   %s) %s,";
  private static final String LAST_POSITION = "   %s) %s\n";

  private final LocalizeService localizeService;
  private final InputReader inputReader;

  public QuizeServiceImpl(LocalizeService localizeService,
                          InputReader inputReader) {
    this.localizeService = localizeService;
    this.inputReader = inputReader;
  }

  @Override
  public Map<Question, Integer> start(final List<Question> questions) {
    int questionCounter = 1;
    localizeService.translate("quize.start");

    final Map<Question, Integer> studentAnswers = new HashMap<>();
    for (Question question : questions) {
      printQuestionVariants(question, questionCounter);

      String userInput = inputReader.getInput();
      if (stringIsNumber(userInput)) {
        studentAnswers.put(question, Integer.valueOf(userInput));
        localizeService.translate("quize.your.answer", new String[] { userInput });
      } else {
        while (true) {
          localizeService.translate("quize.incorrect.answer");
          printQuestionVariants(question, questionCounter);

          userInput = inputReader.getInput();
          if (stringIsNumber(userInput)) {
            localizeService.translate("quize.your.answer", new String[] { userInput });
            break;
          }
        }
      }

      questionCounter++;
      System.out.println("---------------------------------------------------------\n");
    }

    return studentAnswers;
  }

  /**
   * Вывод вопрос и вариантов ответа
   *
   * @param question вопрос
   * @param questionNumber номер вопроса
   */
  private void printQuestionVariants(final Question question, final int questionNumber) {
    System.out.println(questionNumber + ". " + question.getQuestion());
    final List<String> variants = question.getVariantAnswers();
    for (int i = 0, l = variants.size(); i < l; i++) {
      System.out.println(prepareVariantString(i, l, variants.get(i)));
    }
  }

  /**
   * Проверяем, что ответ, который ввёл пользователья является числом, а не строкой
   *
   * @param userInput ответ пользователя
   * @return true, если число, иначе false
   */
  private boolean stringIsNumber(String userInput) {
    try {
      Integer.parseInt(userInput);
      return true;
    } catch (NumberFormatException ex) {
      return false;
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
