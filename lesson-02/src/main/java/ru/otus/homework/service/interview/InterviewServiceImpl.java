package ru.otus.homework.service.interview;

import org.springframework.stereotype.Service;
import ru.otus.homework.model.Question;
import ru.otus.homework.model.Quize;
import ru.otus.homework.service.parser.QuizeParserService;
import ru.otus.homework.service.reader.CsvReaderService;
import ru.otus.homework.service.validate.QuizeValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Aleksey.Potekhin
 * @date 09.06.2021
 */
@Service
public class InterviewServiceImpl implements InterviewService {
  private static final String NEXT_POSITION = "   %s) %s,";
  private static final String LAST_POSITION = "   %s) %s\n";

  private final QuizeParserService quizeParserService;
  private final CsvReaderService readerService;
  private final QuizeValidator quizeValidator;

  public InterviewServiceImpl(QuizeParserService quizeParserService,
                              CsvReaderService readerService,
                              QuizeValidator quizeValidator) {
    this.quizeParserService = quizeParserService;
    this.readerService = readerService;
    this.quizeValidator = quizeValidator;
  }

  /**
   * Запуск процесса опроса
   */
  @Override
  public void start() {
    System.out.println("\nPrepare interview questions");
    List<String> data = readerService.read();
    Quize quize = quizeParserService.parse(data);
    System.out.println("Prepare complete");

    int questionCounter = 1;
    System.out.println("\nStart interview");

    final Map<Question, Integer> studentAnswers = new HashMap<>();
    final Scanner scanner = new Scanner(System.in);
    for (Question question : quize.getQuestions()) {
      System.out.println(questionCounter + ". " + question.getQuestion());

      List<String> variants = question.getVariantAnswers();
      for (int i = 0, l = variants.size(); i < l; i++) {
        System.out.println(prepareVariantString(i, l, variants.get(i)));
      }
      questionCounter++;

      String userInput = scanner.next();
      System.out.println("Your answer is " + userInput + "\n");

      studentAnswers.put(question, Integer.valueOf(userInput));
    }

    quizeValidator.validateStudentAnswers(studentAnswers);
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
