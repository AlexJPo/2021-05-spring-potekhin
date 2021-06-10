package ru.otus.homework.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 09.06.2021
 */
@Getter
@Setter
public class Quize {
  private List<Question> questions;
}
