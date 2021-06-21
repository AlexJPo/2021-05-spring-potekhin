package ru.otus.lesson04.service.interview;

import org.springframework.stereotype.Service;
import ru.otus.lesson04.model.Question;
import ru.otus.lesson04.model.Quize;
import ru.otus.lesson04.model.QuizeResult;
import ru.otus.lesson04.model.Student;
import ru.otus.lesson04.service.localize.LocalizeService;
import ru.otus.lesson04.service.parser.QuizeParserService;
import ru.otus.lesson04.service.quize.QuizeService;
import ru.otus.lesson04.service.reader.CsvReaderService;
import ru.otus.lesson04.service.student.StudentService;
import ru.otus.lesson04.service.validator.QuizeCalculator;

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
    localizeService.translate("interview.start");
    final List<String> data = readerService.read();
    final Quize quize = quizeParserService.parse(data);
    localizeService.translate("interview.complete");

    final Student student = studentService.register();
    final Map<Question, Integer> studentAnswers = quizeService.start(quize.getQuestions());
    final QuizeResult result = quizeValidator.calculatePoints(studentAnswers);

    if (result.getCorrectAnswer() >= result.getTotalQuestion()) {
      localizeService.translate("quize.result.success");
    } else {
      localizeService.translate("quize.result",
          new String[] {
              student.getName(),
              student.getSurname(),
              String.valueOf(result.getCorrectAnswer()),
              String.valueOf(result.getTotalQuestion())
          });
    }
  }
}
