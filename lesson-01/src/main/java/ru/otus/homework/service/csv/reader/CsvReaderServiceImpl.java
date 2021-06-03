package ru.otus.homework.service.csv.reader;

import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 *
 * Сервис чтения вопросов из файла
 */
public class CsvReaderServiceImpl implements CsvReaderService {
  private Resource resource;

  public void setCsvFileLocation(Resource csvFile) {
    this.resource = csvFile;
  }

  /**
   * Чтение вопросов из файла
   *
   * @return список вопросов
   * @throws IOException ошибка чтения файла
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
