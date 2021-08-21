package ru.otus.lesson17.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Aleksey.Potekhin
 * @date 11.08.2021
 */
@Controller
public class HomeController {
  @GetMapping("/")
  public String index() {
    return "home";
  }
}
