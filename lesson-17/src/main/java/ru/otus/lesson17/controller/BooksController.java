package ru.otus.lesson17.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lesson17.dto.BookDTO;
import ru.otus.lesson17.model.Book;
import ru.otus.lesson17.service.book.BookService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 11.08.2021
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
  private final BookService bookService;

  @GetMapping(value = "/all")
  public ResponseEntity<List<Book>> allBooks() {
    return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
  }

  @PutMapping(value="/book/add")
  public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
    bookService.save(bookDTO);
    return new ResponseEntity("OK", HttpStatus.CREATED);
  }

  @GetMapping(value = "/book/{id}")
  public ResponseEntity<Book> bookById(@PathVariable("id") long id) {
    return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
  }

  @PostMapping(value = "/book/{id}")
  public ResponseEntity<Void> editUser(@PathVariable("id") long id,
                                       @RequestBody BookDTO bookDTO) {
    bookService.update(id, bookDTO);
    return new ResponseEntity("OK", HttpStatus.OK);
  }

  @DeleteMapping(value="/delete/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
    bookService.deleteById(id);
    return new ResponseEntity("OK", HttpStatus.NO_CONTENT);
  }
}
