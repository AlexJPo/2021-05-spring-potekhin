package ru.otus.lesson05.service.interview;

import org.springframework.stereotype.Service;
import ru.otus.lesson05.model.Question;
import ru.otus.lesson05.model.Quize;
import ru.otus.lesson05.model.QuizeResult;
import ru.otus.lesson05.model.Student;
import ru.otus.lesson05.service.localize.LocalizeService;
import ru.otus.lesson05.service.parser.QuizeParserService;
import ru.otus.lesson05.service.quize.QuizeService;
import ru.otus.lesson05.service.reader.CsvReaderService;
import ru.otus.lesson05.service.student.StudentService;
import ru.otus.lesson05.service.validator.QuizeCalculator;

import java.util.List;
import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Service
public class InterviewServiceImpl implements InterviewService {
  private final QuizeParserService quizeParserService;
  private final CsvReaderService readerService;
  private final QuizeCalculator quizeValidator;
  private final LocalizeService localizeService;
  private final StudentService studentService;
  private final QuizeService quizeService;

  public InterviewServiceImpl(QuizeParserService quizeParserService,
                              CsvReaderService readerService,
                              QuizeCalculator quizeValidator,
                              LocalizeService localizeService,
                              StudentService studentService,
                              QuizeService quizeService) {
    this.quizeParserService = quizeParserService;
    this.readerService = readerService;
    this.quizeValidator = quizeValidator;
    this.localizeService = localizeService;
    this.studentService = studentService;
    this.quizeService = quizeService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start() {
    localizeService.showMessage("interview.start");
    final List<String> data = readerService.read();
    final Quize quize = quizeParserService.parse(data);
    localizeService.showMessage("interview.complete");

    final Student student = studentService.register();
    final Map<Question, Integer> studentAnswers = quizeService.start(quize.getQuestions());
    final QuizeResult result = quizeValidator.calculatePoints(studentAnswers);

    if (result.getCorrectAnswer() >= result.getTotalQuestion()) {
      localizeService.showMessage("quize.result.success");
    } else {
      localizeService.showMessage("quize.result",
          new String[] {
              student.getName(),
              student.getSurname(),
              String.valueOf(result.getCorrectAnswer()),
              String.valueOf(result.getTotalQuestion())
          });
    }
  }
}
