package ru.otus.lesson17.dto;

import lombok.Getter;
import lombok.Setter;
import ru.otus.lesson17.model.Comment;

import java.util.List;

/**
 * @author ajp
 * @date 21.08.2021
 */
@Getter
@Setter
public class BookDTO {
  private String title;
  private List<Long> bookAuthors;
  private List<Long> bookGenres;
  private List<Comment> bookComment;
}
