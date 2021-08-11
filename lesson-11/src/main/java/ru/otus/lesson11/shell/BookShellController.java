package ru.otus.lesson11.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lesson11.service.book.BookService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@ShellComponent
@RequiredArgsConstructor
public class BookShellController {
  private final BookService bookService;

  @ShellMethod(value = "Find book command", key = {"fb", "findBook"})
  public String findBook(@ShellOption(defaultValue = "1") long id) {
    return bookService.getById(id);
  }

  @ShellMethod(value = "Insert book command", key = {"ib", "insertBook"})
  public String insertBook(@ShellOption(value = "-t", defaultValue = "default book title") String bookTitle,
                           @ShellOption(value = "-g", defaultValue = "default book author") String[] genres,
                           @ShellOption(value = "-a", defaultValue = "default book genre") String[] authors) {
    return bookService.save(bookTitle, genres, authors);
  }

  @ShellMethod(value = "Delete book command", key = {"db", "deleteBook"})
  public String deleteBook(long id) {
    return bookService.deleteById(id);
  }

  @ShellMethod(value = "Update book command", key = {"ub", "updateBook"})
  public String updateBook(long id,
                           @ShellOption(value = "-t", defaultValue = "default book title") String bookTitle,
                           @ShellOption(value = "-g", defaultValue = "default book author") String[] genres,
                           @ShellOption(value = "-a", defaultValue = "default book genre") String[] authors) {
    return bookService.update(id, bookTitle, genres, authors);
  }

  @ShellMethod(value = "All books command", key = {"ab", "aBooks", "allBooks"})
  public List<String> allBooks() {
    return bookService.getAllBooks();
  }

  @ShellMethod(value = "All comment for book command", key = {"bc", "bookComments"})
  public String allCommentsForBook(long id) {
    return bookService.getComments(id);
  }
}

