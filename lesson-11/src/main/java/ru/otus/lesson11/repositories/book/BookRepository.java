package ru.otus.lesson11.repositories.book;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson11.model.Book;

/**
 * @author Aleksey.Potekhin
 * @date 04.07.2021
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
