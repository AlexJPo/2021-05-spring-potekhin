package ru.otus.lesson04.service.parser;

import ru.otus.lesson04.model.Quize;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
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
