package ru.otus.lesson09.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lesson09.service.author.AuthorService;
import ru.otus.lesson09.service.book.BookService;
import ru.otus.lesson09.service.genre.GenreService;

import java.util.List;

/**
 * @author ajp
 * @date 04.07.2021
 */
@ShellComponent
@RequiredArgsConstructor
public class BookShellController {
  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  @ShellMethod(value = "Find book command", key = {"fb", "findBook"})
  public String findBook(@ShellOption(defaultValue = "1") long id) {
    return bookService.getById(id);
  }

  @ShellMethod(value = "Delete book command", key = {"db", "deleteBook"})
  public String deleteBook(long id) {
    return bookService.deleteById(id);
  }

  @ShellMethod(value = "Update book command", key = {"ub", "updateBook"})
  public String updateBook(long id,
                           @ShellOption(defaultValue = "default book title") String bookTitle) {
    return bookService.update(id, bookTitle);
  }

  @ShellMethod(value = "Total rows command", key = {"btr", "bRows", "bookTotalRows"})
  public long totalRows() {
    return bookService.getTotalRows();
  }

  @ShellMethod(value = "All books command", key = {"ab", "aBooks", "allBooks"})
  public List<String> allBooks() {
    return bookService.getAllBooks();
  }
}

