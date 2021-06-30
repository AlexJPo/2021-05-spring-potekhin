package ru.otus.lesson05.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@Getter
@AllArgsConstructor
public class QuizeResult {
  private final int correctAnswer;
  private final int totalQuestion;
}
