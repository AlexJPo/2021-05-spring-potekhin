package ru.otus.lesson11.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lesson11.service.comment.CommentService;

/**
 * @author Aleksey.Potekhin
 * @date 11.07.2021
 */
@ShellComponent
@RequiredArgsConstructor
public class CommentShellController {
  private final CommentService commentService;

  @ShellMethod(value = "Find comment command", key = {"fc", "findComment"})
  public String findComment(@ShellOption(defaultValue = "1") long id) {
    return commentService.getById(id);
  }

  @ShellMethod(value = "Insert comment command", key = {"ic", "insertComment"})
  public String insertComment(@ShellOption(defaultValue = "default comment title") String commentText) {
    return commentService.save(commentText);
  }

  @ShellMethod(value = "Delete comment command", key = {"dc", "deleteComment"})
  public String deleteComment(long id) {
    return commentService.deleteById(id);
  }

  @ShellMethod(value = "Update comment command", key = {"uc", "updateComment"})
  public String updateComment(long id, @ShellOption(defaultValue = "default comment title") String bookTitle) {
    return commentService.update(id, bookTitle);
  }
}
