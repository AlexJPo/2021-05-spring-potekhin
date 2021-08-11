package ru.otus.lesson16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ajp
 * @date 11.08.2021
 */
@Controller
public class HomeController {
  @GetMapping("/")
  public String index() {
    return "home";
  }
}
