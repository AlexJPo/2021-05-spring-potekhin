package ru.otus.lesson05.service.student;

import ru.otus.lesson05.model.Student;

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
