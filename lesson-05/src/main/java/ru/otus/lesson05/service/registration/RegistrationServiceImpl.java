package ru.otus.lesson05.service.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson05.model.Student;
import ru.otus.lesson05.service.localize.LocalizeService;
import ru.otus.lesson05.service.reader.InputReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
  private static final List<Student> REGISTERED_USERS = new ArrayList<>();
  private final LocalizeService localizeService;
  private final InputReader inputReader;

  /**
   * {@inheritDoc}
   */
  @Override
  public Student register() {
    if (REGISTERED_USERS.isEmpty()) {
      localizeService.showMessage("student.name");
      String name = inputReader.getInput();

      localizeService.showMessage("student.surname");
      String surname = inputReader.getInput();

      REGISTERED_USERS.add(new Student(name, surname));
    }
    return REGISTERED_USERS.get(0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isRegistered() {
    return !REGISTERED_USERS.isEmpty();
  }
}
