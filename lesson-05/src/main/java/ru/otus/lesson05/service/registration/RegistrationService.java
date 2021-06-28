package ru.otus.lesson05.service.registration;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
public interface RegistrationService {
  /**
   * Регистрация для опроса
   *
   * @return зарегистрированный пользователь
   */
  <T> T register();

  /**
   * Проверяем, что была выполнена регистрация дял опроса
   *
   * @return true, если была выполнена, иначе false
   */
  boolean isRegistered();
}
