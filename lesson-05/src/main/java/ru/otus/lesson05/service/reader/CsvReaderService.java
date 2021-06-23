package ru.otus.lesson05.service.reader;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
public interface CsvReaderService {
  /**
   * Чтение вопросов из файла
   *
   * @return список вопросов
   */
  List<String> read();
}
