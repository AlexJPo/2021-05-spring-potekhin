package ru.otus.lesson11.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson11.model.Comment;
import ru.otus.lesson11.repositories.comment.CommentRepository;

import java.util.Optional;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public String save(final String text) {
    commentRepository.save(new Comment(0L, text));
    return "Comment for book: '" + text + "' successful save";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getById(final long id) {
    Optional<Comment> comment = commentRepository.findById(id);
    return comment
        .map(comment1 -> "Comment for book: " + comment1.getText())
        .orElseGet(() -> "Comment with id = " + id + " not present");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String update(final long id, final String text) {
    final Optional<Comment> comment = commentRepository.findById(id);
    if (comment.isPresent()) {
      final Comment updatedCooment = comment.get();
      updatedCooment.setText(text);
      commentRepository.save(updatedCooment);
      return "Comment successful update";
    }
    return "Comment with id = " + id + " not present";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String deleteById(final long id) {
    final Optional<Comment> book = commentRepository.findById(id);
    if (book.isPresent()) {
      commentRepository.delete(book.get());
      return "Comment successful remove";
    }
    return "Comment with id = " + id + " not present";
  }
}
