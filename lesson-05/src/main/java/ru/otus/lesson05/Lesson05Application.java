package ru.otus.lesson05;

import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.lesson05.service.interview.InterviewService;

@SpringBootApplication
public class Lesson05Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Main.class, args);
		InterviewService interviewService = context.getBean(InterviewService.class);
		interviewService.start();
	}

}
