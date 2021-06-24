package ru.otus.bookapp.service;

import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.dto.BookDTO;

/**
 * @author ajp
 * @date 24.06.2021
 */
public interface BookService {
  BookDTO getById(long id);

  void deleteById(long id);

  void insert(String bookTitle, long authorId, long genreId);

  int getTotalRows();

  void updateAuthor(long bookId, long authorId);

  void updateGenre(long bookId, long genreId);
}
