package ru.otus.lesson04.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Getter
@Setter
public class Quize {
  private List<Question> questions;
}
