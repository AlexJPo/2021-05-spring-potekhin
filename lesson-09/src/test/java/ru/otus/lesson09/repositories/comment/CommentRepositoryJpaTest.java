package ru.otus.lesson09.repositories.comment;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.lesson09.model.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ajp
 * @date 04.07.2021
 */
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {
  private static final int EXPECTED_NUMBER_OF_COMMENTS = 4;
  @Autowired
  private CommentRepositoryJpa repositoryJpa;
  @Autowired
  private TestEntityManager em;


  @DisplayName("Вернёт пустой жанр")
  @Test
  void getById_ReturnEmpty() {
    val comment = repositoryJpa.getById(10L);
    assertFalse(comment.isPresent());
  }

  @DisplayName("Вернёт существующий жанр")
  @Test
  void getById_ReturnPresentObject() {
    val comment = repositoryJpa.getById(1L);
    assertTrue(comment.isPresent());
  }

  @DisplayName("Корректное сохранение")
  @Test
  public void save() {
    val bookComment = new Comment(0, "Save book comment");

    repositoryJpa.save(bookComment);
    assertThat(bookComment.getId()).isGreaterThan(0);

    val savedBookComment = em.find(Comment.class, bookComment.getId());
    assertThat(savedBookComment)
        .isNotNull()
        .matches(comment -> !comment.getText().equals(""))
        .matches(comment -> comment.getText().equals(bookComment.getText()));
  }

  @DisplayName("Вернёт все комментарии")
  @Test
  void getAll() {
    val comments = repositoryJpa.findAll();

    assertThat(comments)
        .isNotNull()
        .hasSize(EXPECTED_NUMBER_OF_COMMENTS)
        .allMatch(s -> !s.getText().equals(""));
  }

  @DisplayName("Обновление комментария")
  @Test
  public void update() {
    val bookCommentBeforeUpdate = repositoryJpa.getById(1L);
    final long commentId = bookCommentBeforeUpdate.get().getId();
    repositoryJpa.updateTextById(commentId, "update comment");

    val bookCommentAfterUpdate  = em.find(Comment.class, commentId);
    assertThat(bookCommentBeforeUpdate)
        .isNotEqualTo(bookCommentAfterUpdate);
  }

  @DisplayName("Удаление комментария по id")
  @Test
  void delete() {
    final long firstCommentId = 1L;
    val firstStudent = em.find(Comment.class, firstCommentId);
    assertThat(firstStudent).isNotNull();
    em.detach(firstStudent);

    repositoryJpa.deleteById(firstCommentId);
    val deletedStudent = em.find(Comment.class, firstCommentId);

    assertThat(deletedStudent).isNull();
  }
}