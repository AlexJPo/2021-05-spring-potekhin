package ru.otus.lesson04.service.localize;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
public interface LocalizeService {
  /**
   * Перевод текста приложения
   *
   * @param propName названеи свойства из файла messages.properties
   * @return переведённая строка
   */
  void translate(String propName);

  /**
   * Перевод текста приложения
   *
   * @param propName названеи свойства из файла messages.properties
   * @param values значения
   * @return переведённая строка
   */
  void translate(String propName, Object[] values);

}
