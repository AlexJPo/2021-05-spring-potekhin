package ru.otus.lesson04.service.interview;

import org.springframework.stereotype.Service;
import ru.otus.lesson04.model.Question;
import ru.otus.lesson04.model.Quize;
import ru.otus.lesson04.service.localize.LocalizeService;
import ru.otus.lesson04.service.parser.QuizeParserService;
import ru.otus.lesson04.service.reader.CsvReaderService;
import ru.otus.lesson04.service.validator.QuizeValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Service
public class InterviewServiceImpl implements InterviewService {
  private static final String NEXT_POSITION = "   %s) %s,";
  private static final String LAST_POSITION = "   %s) %s\n";
  private static final int DEFAULT_USER_ANSWER = 1;

  private final QuizeParserService quizeParserService;
  private final CsvReaderService readerService;
  private final QuizeValidator quizeValidator;
  private final LocalizeService localizeService;

  public InterviewServiceImpl(QuizeParserService quizeParserService,
                              CsvReaderService readerService,
                              QuizeValidator quizeValidator,
                              LocalizeService localizeService) {
    this.quizeParserService = quizeParserService;
    this.readerService = readerService;
    this.quizeValidator = quizeValidator;
    this.localizeService = localizeService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start() {
    System.out.println(localizeService.translate("interview.start"));
    List<String> data = readerService.read();
    Quize quize = quizeParserService.parse(data);
    System.out.println(localizeService.translate("interview.complete"));

    int questionCounter = 1;
    System.out.println(localizeService.translate("quize.start"));

    final Map<Question, Integer> studentAnswers = new HashMap<>();
    final Scanner scanner = new Scanner(System.in);
    for (Question question : quize.getQuestions()) {
      printQuestionVariants(question, questionCounter);

      String userInput = scanner.next();
      if (stringIsNumber(userInput)) {
        System.out.println(
            localizeService.translate("quize.your.answer", new String[] { userInput })
        );
      } else {
        while (true) {
          System.out.println(localizeService.translate("quize.incorrect.answer"));
          printQuestionVariants(question, questionCounter);

          userInput = scanner.next();
          if (stringIsNumber(userInput)) {
            System.out.println(
                localizeService.translate("quize.your.answer", new String[] { userInput })
            );
            break;
          }
        }
      }

      studentAnswers.put(question, DEFAULT_USER_ANSWER);
      questionCounter++;
      System.out.println("---------------------------------------------------------\n");
    }

    quizeValidator.validateStudentAnswers(studentAnswers);
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
