package ru.otus.lesson04.service.student;

import ru.otus.lesson04.model.Student;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
public interface StudentService {
  /**
   * Регистрация студента для опроса
   *
   * @return студент
   */
  Student register();
}
