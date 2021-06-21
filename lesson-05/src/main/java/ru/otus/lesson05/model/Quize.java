package ru.otus.lesson05.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Getter
@RequiredArgsConstructor
public class Quize {
  private final List<Question> questions;
}
