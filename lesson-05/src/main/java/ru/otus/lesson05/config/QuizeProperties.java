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
@ConfigurationProperties(prefix = "quize")
@Getter
@Setter
public class QuizeProperties {
  private int correctAnswerCounter;
  private String fileName;
}
