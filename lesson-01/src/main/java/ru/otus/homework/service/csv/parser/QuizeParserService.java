package ru.otus.homework.service.csv.parser;

import ru.otus.homework.model.Quize;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 */
public interface QuizeParserService {
  /**
   * Конвертация данных для опроса
   *
   * @param data данные
   * @return класс опроса
   */
  Quize parse(List<String> data);
}
