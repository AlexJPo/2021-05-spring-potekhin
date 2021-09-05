package ru.otus.lesson23.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.lesson23.model.Book;
import ru.otus.lesson23.service.author.AuthorService;
import ru.otus.lesson23.service.book.BookService;
import ru.otus.lesson23.service.genre.GenreService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 11.08.2021
 */
@Component
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  @GetMapping(value = "")
  public String showUsersPage(Model model) {
    model.addAttribute("books", bookService.getAllBooks());
    return "books/index";
  }

  @GetMapping(value = "/add")
  public ModelAndView showCreatePage() {
    final ModelAndView model = new ModelAndView();
    model.setViewName("books/add");
    model.addObject("backUrl", "/books");
    model.addObject("redirect", "books");
    model.addObject("authors", authorService.findAll());
    model.addObject("genres", genreService.findAll());
    return model;
  }

  @PutMapping(value="/add")
  public ModelAndView addBook(@RequestParam("bookTitle") String bookTitle,
                              @RequestParam("bookAuthors") List<Long> bookAuthors,
                              @RequestParam("bookGenres") List<Long> bookGenres,
                              @RequestParam("bookComment") String bookComment) {
    bookService.save(bookTitle, bookAuthors, bookGenres, bookComment);
    return new ModelAndView("redirect:/books");
  }

  @GetMapping(value = "/edit/{id}")
  public ModelAndView showEditUserPage(@PathVariable("id") long id) {
    final Book book = bookService.getById(id);

    final ModelAndView model = new ModelAndView();
    model.setViewName("books/edit");
    model.addObject("backUrl", "/books");
    model.addObject("redirect", "books");
    model.addObject("book", book);
    model.addObject("authors", authorService.findAll());
    model.addObject("genres", genreService.findAll());
    model.addObject("comments", book.getComments());
    return model;
  }
  @PostMapping(value = "/edit/{id}")
  public ModelAndView editUser(@PathVariable("id") long id,
                               @RequestParam("bookTitle") String bookTitle,
                               @RequestParam("bookAuthors") List<Long> bookAuthors,
                               @RequestParam("bookGenres") List<Long> bookGenres,
                               @RequestParam("bookComments") String[] bookComments) {
    bookService.update(id, bookTitle, bookAuthors, bookGenres, bookComments);
    return new ModelAndView("redirect:/books");
  }

  @GetMapping(value = "/delete/{id}")
  public ModelAndView showDeletePage(@PathVariable("id") long id) {
    final ModelAndView model = new ModelAndView();
    model.setViewName("books/delete");
    model.addObject("book", bookService.getById(id));
    model.addObject("backUrl", "/books");
    return model;
  }

  @DeleteMapping(value="/delete/{id}")
  public ModelAndView addBook(@PathVariable("id") long id) {
    bookService.deleteById(id);
    return new ModelAndView("redirect:/books");
  }
}
