package ru.otus.bookapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.dto.BookDTO;
import ru.otus.bookapp.service.BookService;
import ru.otus.bookapp.service.author.AuthorService;
import ru.otus.bookapp.service.genre.GenreService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
@ShellComponent
@RequiredArgsConstructor
public class BookShellController {
  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

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

  @ShellMethod(value = "Update book command", key = {"ub", "updateBook"})
  public String updateBook(long id,
                           @ShellOption(defaultValue = "default book title") String bookTitle,
                           @ShellOption(defaultValue = "1") long authorId,
                           @ShellOption(defaultValue = "1") long genreId) {
    return bookService.update(id, bookTitle, authorId, genreId);
  }

  @ShellMethod(value = "Total rows command", key = {"btr", "bRows", "bookTotalRows"})
  public long totalRows() {
    return bookService.getTotalRows();
  }

  @ShellMethod(value = "All books command", key = {"ab", "aBooks", "allBooks"})
  public List<Book> allBooks() {
    return bookService.getAllBooks();
  }
}
