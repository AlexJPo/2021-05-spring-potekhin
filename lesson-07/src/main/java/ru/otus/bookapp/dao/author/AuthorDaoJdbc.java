package ru.otus.bookapp.dao.author;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.bookapp.domain.Author;
import ru.otus.bookapp.exception.NotFoundRowException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
@Repository
public class AuthorDaoJdbc implements AuthorDao {
  private final NamedParameterJdbcTemplate jdbc;

  public AuthorDaoJdbc(NamedParameterJdbcTemplate jdbc)
  {
    this.jdbc = jdbc;
  }

  private static class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
      long id = resultSet.getLong("id");
      String name = resultSet.getString("name");
      return new Author(id, name);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Author findById(final long id) throws NotFoundRowException {
    try {
      return jdbc.queryForObject(
          "select id, name from authors where id = :id", Map.of("id", id), new AuthorMapper()
      );
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundRowException(e);
    }
  }
}
