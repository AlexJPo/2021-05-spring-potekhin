package ru.otus.lesson17.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.lesson17.model.Author;
import ru.otus.lesson17.service.author.AuthorService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 11.08.2021
 */
@Component
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorService authorService;

  @GetMapping("")
  public ResponseEntity<List<Author>> all() {
    return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
  }

}