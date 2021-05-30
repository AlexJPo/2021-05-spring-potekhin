package ru.otus.homework.model;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 */
public class Question {
  private String question;
  private List<String> variantAnswers;
  private String correctAnswer;

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public List<String> getVariantAnswers() {
    return variantAnswers;
  }

  public void setVariantAnswers(List<String> variantAnswers) {
    this.variantAnswers = variantAnswers;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(String correctAnswer) {
    this.correctAnswer = correctAnswer;
  }
}
