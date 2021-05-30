package ru.otus.homework.service.csv.reader;

import java.io.IOException;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
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
