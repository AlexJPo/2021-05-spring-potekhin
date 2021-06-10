package ru.otus.homework.service.parser;

import ru.otus.homework.model.Quize;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 09.06.2021
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
