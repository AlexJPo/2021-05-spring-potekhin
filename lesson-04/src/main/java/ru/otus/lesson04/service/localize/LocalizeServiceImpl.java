package ru.otus.lesson04.service.localize;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Service
public class LocalizeServiceImpl implements LocalizeService {
  private final MessageSource messageSource;
  private final String applicationLocale;

  public LocalizeServiceImpl(@Value("${localization}") String locale,
                             MessageSource msg) {
    this.applicationLocale = locale;
    this.messageSource = msg;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void translate(String propName) {
    System.out.println(
        messageSource.getMessage(propName, new Object[] {}, Locale.forLanguageTag(applicationLocale))
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void translate(String propName, Object[] values) {
    System.out.println(
        messageSource.getMessage(propName, values, Locale.forLanguageTag(applicationLocale))
    );
  }
}
