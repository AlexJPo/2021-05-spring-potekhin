package ru.otus.lesson05.service.reader;

import java.io.IOException;
import java.util.List;

/**
 * @author ajp
 * @date 14.06.2021
 */
public interface CsvReaderService {
  /**
   * Чтение вопросов из файла
   *
   * @return список вопросов
   * @throws IOException ошибка чтения файла
   */
  List<String> read();
}
