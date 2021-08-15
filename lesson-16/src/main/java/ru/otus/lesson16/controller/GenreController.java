package ru.otus.lesson16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.lesson16.service.author.AuthorService;
import ru.otus.lesson16.service.genre.GenreService;

/**
 * @author Aleksey.Potekhin
 * @date 11.08.2021
 */
@Component
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
  private final GenreService genreService;

  @GetMapping(value = "")
  public String showUsersPage(Model model) {
    model.addAttribute("genres", genreService.findAll());
    return "genres/index";
  }
}
