package ru.otus.lesson05.service.quize;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.otus.lesson05.model.Question;
import ru.otus.lesson05.service.localize.LocalizeService;
import ru.otus.lesson05.service.reader.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.otus.lesson05.utils.QuizeHelper.prepareVariantString;
import static ru.otus.lesson05.utils.QuizeHelper.stringIsNumber;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@Service
public class QuizeServiceImpl implements QuizeService {
  private final LocalizeService localizeService;
  private final InputReader inputReader;

  public QuizeServiceImpl(LocalizeService localizeService,
                          InputReader inputReader) {
    this.localizeService = localizeService;
    this.inputReader = inputReader;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Question, Integer> start(final List<Question> questions) {
    if (CollectionUtils.isEmpty(questions)) {
      return new HashMap<>();
    }

    int questionCounter = 1;
    localizeService.showMessage("quize.start");

    final Map<Question, Integer> studentAnswers = new HashMap<>();
    for (Question question : questions) {
      printQuestionVariants(question, questionCounter);

      String userInput = inputReader.getInput();
      if (stringIsNumber(userInput)) {
        saveUserAnswer(studentAnswers, question, userInput);
      } else {
        while (true) {
          localizeService.showMessage("quize.incorrect.answer");
          printQuestionVariants(question, questionCounter);

          userInput = inputReader.getInput();
          if (stringIsNumber(userInput)) {
            saveUserAnswer(studentAnswers, question, userInput);
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
   * Сохранение ответа стиудента
   *
   * @param studentAnswers ответы студента на вопрос
   * @param question вопрос
   * @param val ответ студента
   */
  private void saveUserAnswer(final Map<Question, Integer> studentAnswers, final Question question, final String val) {
    studentAnswers.put(question, Integer.valueOf(val));
    localizeService.showMessage("quize.your.answer", new String[] { val });
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

}
