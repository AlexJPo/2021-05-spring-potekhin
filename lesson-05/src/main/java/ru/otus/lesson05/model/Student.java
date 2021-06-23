package ru.otus.lesson05.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@Getter
@RequiredArgsConstructor
public class Student {
  private final String name;
  private final String surname;
}
