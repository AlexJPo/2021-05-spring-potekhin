package ru.otus.lesson11.repositories.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson11.model.Genre;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
