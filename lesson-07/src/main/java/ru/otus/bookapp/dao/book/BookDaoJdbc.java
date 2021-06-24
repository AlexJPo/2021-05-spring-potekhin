package ru.otus.bookapp.dao.book;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.bookapp.domain.Book;
import ru.otus.bookapp.exception.NotFoundRowException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 23.06.2021
 */
@Repository
public class BookDaoJdbc implements BookDao {
  private final NamedParameterJdbcTemplate jdbc;

  public BookDaoJdbc(NamedParameterJdbcTemplate jdbc)
  {
    this.jdbc = jdbc;
  }

  private static class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
      final long id = resultSet.getLong("id");
      final String name = resultSet.getString("title");
      final long authorId = resultSet.getLong("author_id");
      final long genreId = resultSet.getLong("genre_id");

      return new Book(id, name, authorId, genreId);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long count() {
    return jdbc.getJdbcTemplate().queryForObject("select count(*) from books", Long.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Book findById(final Long id) throws NotFoundRowException {
    try {
      return jdbc.queryForObject(
          "select * from books where id = :id", Map.of("id", id), new BookMapper()
      );
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundRowException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insert(final Book book) {
    final Map<String, Object> params = prepareQueryParameters(book);
    jdbc.update(
        "insert into books (id, title, author_id, genre_id) values(:bookId, :title, :authorId, :genreId)",
        params
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(final Book book) {
    final Map<String, Object> params = prepareQueryParameters(book);
    jdbc.update(
        "update books set title = :title, author_id = :authorId, genre_id = :genreId where id = :bookId",
        params
    );
  }

  /**
   * Формирование параметров для запроса
   */
  private Map<String, Object> prepareQueryParameters(final Book book) {
    final Map<String, Object> params = new HashMap<>();
    params.put("bookId", book.getId());
    params.put("title", book.getTitle());
    params.put("authorId", book.getAuthorId());
    params.put("genreId", book.getGenreId());
    return params;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteById(final Long id) {
    jdbc.update("delete from books where id = :id", Map.of("id", id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long nextId() {
    return jdbc.getJdbcOperations().queryForObject("select MAX(ID) from genres", Long.class);
  }
}
