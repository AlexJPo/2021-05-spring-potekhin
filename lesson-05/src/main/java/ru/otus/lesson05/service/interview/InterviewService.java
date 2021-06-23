package ru.otus.lesson05.service.interview;

import ru.otus.lesson05.model.Student;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
public interface InterviewService {
  /**
   * Запуск процесса опроса
   */
  void start();

  /**
   * Запуск процесса опроса
   *
   * @param student опрашиваемый студент
   */
  void start(Student student);
}
