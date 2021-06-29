package ru.otus.bookapp.dao.genre;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.bookapp.domain.Genre;
import ru.otus.bookapp.exception.NotFoundRowException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Aleksey.Potekhin
 * @date 24.06.2021
 */
@Repository
public class GenreDaoJdbc implements GenreDao {
  private final NamedParameterJdbcTemplate jdbc;

  public GenreDaoJdbc(NamedParameterJdbcTemplate jdbc)
  {
    this.jdbc = jdbc;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Genre getById(final long id) throws NotFoundRowException {
    try {
      return jdbc.queryForObject(
          "select id, title from genres where id = :id", Map.of("id", id), new GenreMapper()
      );
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundRowException(e);
    }
  }

  private static class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
      final long id = resultSet.getLong("id");
      final String name = resultSet.getString("title");
      return new Genre(id, name);
    }
  }
}
