package ru.otus.lesson04.service.localize;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author ajp
 * @date 14.06.2021
 */
@Service
public class LocalizeServiceImpl implements LocalizeService {
  private final MessageSource messageSource;
  private final String applicationLocale;

  public LocalizeServiceImpl(MessageSource msg,
                             @Value("${localization}") String locale) {
    this.messageSource = msg;
    this.applicationLocale = locale;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String translate(String propName) {
    return messageSource.getMessage(propName, new Object[] {}, Locale.forLanguageTag(applicationLocale));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String translate(String propName, Object[] values) {
    return messageSource.getMessage(propName, values, Locale.forLanguageTag(applicationLocale));
  }
}
