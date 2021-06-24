package ru.otus.bookapp;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.dto.BookDTO;
import ru.otus.bookapp.service.BookService;

@SpringBootApplication
public class BookAppApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BookAppApplication.class, args);
		/*Console.main(args);*/


		/*ApplicationContext context = SpringApplication.run(BookAppApplication.class, args);
		BookService bookService = context.getBean(BookService.class);

		// select
		BookDTO book = bookService.getById(1L);
		System.out.println("Find book by id: " + 1L);
		System.out.println("Result is: " + book);
		System.out.println("\n");

		// insert
		bookService.insert("insert row", 1L, 1L);
		System.out.println("Total rows after insert: " + bookService.getTotalRows());

		book = bookService.getById(2L);
		System.out.println("Find book by id: " + 2L);
		System.out.println("Result is: " + book);
		System.out.println("\n");


		// update - author
		bookService.updateAuthor(book.getId(), 2L);
		System.out.println("Change author of book id = " + 2L);

		book = bookService.getById(2L);
		System.out.println("Find book by id: " + 2L);
		System.out.println("Result is: " + book);
		System.out.println("\n");

		// update - genre
		bookService.updateGenre(book.getId(), 2L);
		System.out.println("Change author of book id = " + 2L);

		book = bookService.getById(2L);
		System.out.println("Find book by id: " + 2L);
		System.out.println("Result is: " + book);
		System.out.println("\n");


		// delete
		bookService.deleteById(2L);
		System.out.println("Delete book with id = " + 2L);

		book = bookService.getById(2L);
		System.out.println("Find book by id: " + 2L);
		System.out.println("Result is: " + book);

		System.out.println("\n");
		System.out.println("Total rows after insert: " + bookService.getTotalRows());*/
	}

}
