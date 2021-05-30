package ru.otus.homework.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 */
public class Quize {
  private List<Question> questions;

  public Quize() {
    questions = new ArrayList<>();
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }
}
