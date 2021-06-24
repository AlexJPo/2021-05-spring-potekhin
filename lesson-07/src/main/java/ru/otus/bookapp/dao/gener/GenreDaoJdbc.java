package ru.otus.bookapp.dao.gener;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.bookapp.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author ajp
 * @date 24.06.2021
 */
@Repository
public class GenreDaoJdbc implements GenreDao {
  private static final int DEFAULT_ROW_ID = 1;
  private final NamedParameterJdbcTemplate jdbc;

  public GenreDaoJdbc(NamedParameterJdbcTemplate jdbc)
  {
    this.jdbc = jdbc;
  }

  private static class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
      final long id = resultSet.getLong("id");
      final String name = resultSet.getString("title");
      return new Genre(id, name);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long nextId() {
    final Long result = jdbc.getJdbcOperations().queryForObject("select MAX(ID) from genres", Long.class);
    return result == null ? DEFAULT_ROW_ID : result + 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Genre findById(long id) {
    return jdbc.queryForObject(
        "select id, title from genres where id = :id", Map.of("id", id), new GenreMapper()
    );
  }
}
