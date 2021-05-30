package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.model.Quize;
import ru.otus.homework.service.csv.interview.InterviewService;
import ru.otus.homework.service.csv.parser.QuizeParserService;
import ru.otus.homework.service.csv.reader.CsvReaderService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 30.05.2021
 */
public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

    CsvReaderService readerService = context.getBean(CsvReaderService.class);
    List<String> data = readerService.read();

    System.out.println("\nPrepare interview questions");
    QuizeParserService parserService = context.getBean(QuizeParserService.class);
    Quize quize = parserService.parse(data);
    System.out.println("Prepare interview questions complete");

    InterviewService interviewService = context.getBean(InterviewService.class);
    interviewService.start(quize);
  }
}
