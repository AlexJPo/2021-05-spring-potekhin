package ru.otus.lesson09;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Lesson09Application {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Lesson09Application.class, args);
		//Console.main(args);
	}

}
