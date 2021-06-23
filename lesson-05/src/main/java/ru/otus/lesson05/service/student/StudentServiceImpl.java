package ru.otus.lesson05.service.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson05.model.Student;
import ru.otus.lesson05.service.localize.LocalizeService;
import ru.otus.lesson05.service.reader.InputReader;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
  private final LocalizeService localizeService;
  private final InputReader inputReader;

  /**
   * {@inheritDoc}
   */
  @Override
  public Student register() {
    localizeService.showMessage("student.name");
    String name = inputReader.getInput();

    localizeService.showMessage("student.surname");
    String surname = inputReader.getInput();

    return new Student(name, surname);
  }
}
