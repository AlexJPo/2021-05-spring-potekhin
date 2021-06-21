package ru.otus.lesson04.service.reader;

import org.springframework.stereotype.Service;
import java.util.Scanner;

/**
 * @author Aleksey.Potekhin
 * @date 15.06.2021
 */
@Service
public class InputReaderImpl implements InputReader {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getInput() {
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }
}
