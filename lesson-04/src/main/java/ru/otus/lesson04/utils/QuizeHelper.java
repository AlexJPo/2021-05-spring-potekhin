package ru.otus.lesson04.utils;

/**
 * @author Aleksey.Potekhin
 * @date 18.06.2021
 */
public final class QuizeHelper {
  private static final String NEXT_POSITION = "   %s) %s,";
  private static final String LAST_POSITION = "   %s) %s\n";

  /**
   * Проверяем, что ответ, который ввёл пользователья является числом, а не строкой
   *
   * @param userInput ответ пользователя
   * @return true, если число, иначе false
   */
  public static boolean stringIsNumber(String userInput) {
    try {
      Integer.parseInt(userInput);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }

  /**
   * Формирование строка с вариантов ответа
   *
   * @param counter счётчик
   * @param collectionSize размер коллекции
   * @param val вариант ответа
   * @return форматированная строка
   */
  public static String prepareVariantString(final int counter, final int collectionSize, final String val) {
    final String pattern = counter < collectionSize - 1
        ? NEXT_POSITION
        : LAST_POSITION;
    return String.format(pattern, (counter + 1), val);
  }
}
