package ru.otus.lesson05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.lesson05.service.interview.InterviewService;
import ru.otus.lesson05.shell.ApplicationShellComponent;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		/*ApplicationContext context = SpringApplication.run(Main.class, args);
		ApplicationShellComponent bean = context.getBean(ApplicationShellComponent.class);*/
	}

}
