package ru.otus.lesson05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Data
@AllArgsConstructor
public class Question {
  private final String question;
  private final List<String> variantAnswers;
  private final Integer correctAnswer;
}
