package ru.otus.lesson04.service.reader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 14.06.2021
 */
@Service
public class CsvReaderServiceImpl implements CsvReaderService {
  final Resource resource;

  public CsvReaderServiceImpl(@Value("classpath:quize.csv") Resource resource) {
    this.resource = resource;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> read() {
    final List<String> result = new ArrayList<>();
    try {
      System.out.println("Read file: " + resource.getFilename());
      final InputStream csvInputStream = resource.getInputStream();
      try (BufferedReader br = new BufferedReader(new InputStreamReader(csvInputStream))) {
        String line;
        while ((line = br.readLine()) != null) {
          result.add(line);
        }
      }
      System.out.println("Reading file complete.");
    } catch (IOException e) {
      System.out.println("Csv file reading error: " + e);
    }
    return result;
  }
}
