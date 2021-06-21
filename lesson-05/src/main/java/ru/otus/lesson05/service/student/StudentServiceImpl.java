package ru.otus.lesson05.service.student;

import org.springframework.stereotype.Service;
import ru.otus.lesson05.model.Student;
import ru.otus.lesson05.service.localize.LocalizeService;
import ru.otus.lesson05.service.reader.InputReader;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@Service
public class StudentServiceImpl implements StudentService {
  private final LocalizeService localizeService;
  private final InputReader inputReader;

  public StudentServiceImpl(LocalizeService localizeService,
                            InputReader inputReader) {
    this.localizeService = localizeService;
    this.inputReader = inputReader;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Student register() {
    Student student = new Student();

    localizeService.showMessage("student.name");
    student.setName(inputReader.getInput());

    localizeService.showMessage("student.surname");
    student.setSurname(inputReader.getInput());

    return student;
  }
}
