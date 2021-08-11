package ru.otus.lesson11.repositories.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson11.model.Comment;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
