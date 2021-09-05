package ru.otus.lesson17.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.lesson17.model.Genre;
import ru.otus.lesson17.service.genre.GenreService;

import java.util.List;

/**
 * @author Aleksey.Potekhin
 * @date 11.08.2021
 */
@Component
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {
  private final GenreService genreService;

  @GetMapping(value = "")
  public ResponseEntity<List<Genre>> all() {
    return new ResponseEntity<>(genreService.findAll(), HttpStatus.OK);
  }
}
