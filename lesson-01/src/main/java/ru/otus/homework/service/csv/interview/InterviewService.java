package ru.otus.homework.service.csv.interview;

import ru.otus.homework.model.Quize;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 */
public interface InterviewService {
  /**
   * Запуск процесса опроса
   *
   * @param quize даныне для опроса
   */
  void start(Quize quize);
}
