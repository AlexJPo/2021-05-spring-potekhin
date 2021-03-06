package ru.otus.lesson05.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Component
@ConfigurationProperties(prefix = "quize.settings")
@Getter
@Setter
public class CSVProperties {
  private int fieldMinSize;
  private String separator;
  private String answerVariantSeparator;
}
