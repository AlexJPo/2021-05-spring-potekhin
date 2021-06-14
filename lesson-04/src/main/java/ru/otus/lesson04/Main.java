package ru.otus.lesson04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.lesson04.service.interview.InterviewService;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Main.class, args);
		InterviewService interviewService = context.getBean(InterviewService.class);
		interviewService.start();
	}
}
