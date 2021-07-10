package ru.otus.lesson11.repositories.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson11.model.Author;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
