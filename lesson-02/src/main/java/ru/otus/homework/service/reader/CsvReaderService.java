package ru.otus.homework.service.reader;

import java.io.IOException;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 09.06.2021
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
