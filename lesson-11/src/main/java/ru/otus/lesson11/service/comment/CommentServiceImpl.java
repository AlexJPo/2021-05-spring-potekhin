package ru.otus.lesson11.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson11.model.Comment;
import ru.otus.lesson11.repositories.comment.CommentRepository;

import java.util.List;

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
  public Comment save(final Comment comment) {
    return commentRepository.save(comment);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Comment getById(final long id) {
    return commentRepository.getById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(final Comment comment) {
    commentRepository.update(comment);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteById(final long id) {
    commentRepository.deleteById(id);
  }
}
