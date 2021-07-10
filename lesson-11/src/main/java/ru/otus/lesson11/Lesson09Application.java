package ru.otus.lesson11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

@SpringBootApplication
@EnableJpaRepositories
public class Lesson09Application {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Lesson09Application.class, args);
		//Console.main(args);
	}

}
