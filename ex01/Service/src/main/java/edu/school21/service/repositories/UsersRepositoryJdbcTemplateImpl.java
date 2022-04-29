package edu.school21.service.repositories;

import edu.school21.service.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static java.lang.String.format;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    DriverManagerDataSource dataSource;
    private String tableName;
    private JdbcTemplate jdbcTemplate;

    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(resultSet.getLong("id"), resultSet.getString("email"));
    };

    public UsersRepositoryJdbcTemplateImpl(DriverManagerDataSource driverManagerDataSource, String tableName) {
        this.dataSource = driverManagerDataSource;
        this.jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
        this.tableName = tableName;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = jdbcTemplate.queryForObject(format("SELECT * FROM %s WHERE id = %d", tableName, id), ROW_MAPPER);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(format("SELECT * FROM %s", tableName), ROW_MAPPER);
    }

    @Override
    public void save(User entity) {

        jdbcTemplate.update(format("INSERT INTO %s (email) VALUES ('%s')", tableName, entity.getEmail()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(format("UPDATE %s  SET email = '%s' WHERE id = %d", tableName, entity.getEmail(), entity.getIdentifier()));
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(format("DELETE FROM %s WHERE id = %d", tableName, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = jdbcTemplate.queryForObject(format("SELECT * FROM %s WHERE email = '%s'", tableName, email), ROW_MAPPER);
        return Optional.ofNullable(user);
    }
}
