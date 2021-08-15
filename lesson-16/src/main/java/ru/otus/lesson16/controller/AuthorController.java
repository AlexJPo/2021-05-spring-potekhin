package ru.otus.lesson16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.lesson16.service.author.AuthorService;

/**
 * @author Aleksey.Potekhin
 * @date 11.08.2021
 */
@Component
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorService authorService;

  @GetMapping(value = "")
  public String showUsersPage(Model model) {
    model.addAttribute("authors", authorService.findAll());
    return "authors/index";
  }
}
