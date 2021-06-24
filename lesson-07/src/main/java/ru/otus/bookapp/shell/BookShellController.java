package ru.otus.bookapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.dto.BookDTO;
import ru.otus.bookapp.service.BookService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
@ShellComponent
@RequiredArgsConstructor
public class BookShellController {
  private final BookService bookService;

  @ShellMethod(value = "Find book command", key = {"fb", "findBook"})
  public String findBook(@ShellOption(defaultValue = "1") long id) {
    BookDTO book = bookService.getById(id);
    if (book.getId() == null) {
      return "Book with id = " + id + " not present";
    }
    return book.toString();
  }

  @ShellMethod(value = "Insert book command", key = {"ib", "insertBook"})
  public String insertBook(@ShellOption(defaultValue = "default book title") String bookTitle,
                           @ShellOption(defaultValue = "1") long authorId,
                           @ShellOption(defaultValue = "1") long genreId) {
    return bookService.insert(bookTitle, authorId, genreId);
  }

  @ShellMethod(value = "Delete book command", key = {"db", "deleteBook"})
  public String deleteBook(long id) {
    return bookService.deleteById(id);
  }

  @ShellMethod(value = "Delete book command", key = {"uba", "ubAuthor", "updateBookAuthor"})
  public String updateBookAuthor(long id, @ShellOption(defaultValue = "1") long authorId) {
    return bookService.updateAuthor(id, authorId);
  }

  @ShellMethod(value = "Delete book command", key = {"ubg", "ubGenre", "updateBookGenre"})
  public String updateBookGenre(long id, @ShellOption(defaultValue = "1") long genreId) {
    return bookService.updateAuthor(id, genreId);
  }

  @ShellMethod(value = "Delete book command", key = {"btr", "bRows", "bookTotalRows"})
  public long totalRows() {
    return bookService.getTotalRows();
  }

  @ShellMethod(value = "All books command", key = {"ab", "aBooks", "allBooks"})
  public List<Book> allBooks() {
    return bookService.getAllBooks();
  }
}
