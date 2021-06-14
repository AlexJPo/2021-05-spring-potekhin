package ru.otus.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.service.interview.InterviewService;

/**
 * @author Aleksey.Potekhin
 * @date 09.06.2021
 */
@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

    InterviewService interviewService = context.getBean(InterviewService.class);
    interviewService.start();
  }
}
