package edu.school21.service.repositories;

import edu.school21.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    DriverManagerDataSource dataSource;
    private final String tableName = "users";
    private final JdbcTemplate jdbcTemplate;

    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"));
    };

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(DriverManagerDataSource driverManagerDataSource) {
        this.dataSource = driverManagerDataSource;
        this.jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
    }

    public UsersRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        jdbcTemplate.update(format("INSERT INTO %s (email, password) VALUES ('%s', '%s')", tableName, entity.getEmail(), entity.getPassword()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(format("UPDATE %s  SET email = %s, password = %s WHERE id = %d", tableName, entity.getEmail(), entity.getPassword(), entity.getIdentifier()));
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(format("DELETE FROM %s WHERE id = %d", tableName, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = jdbcTemplate.query(format("SELECT * FROM %s WHERE email = '%s'", tableName, email), ROW_MAPPER).get(0);
        return Optional.ofNullable(user);
    }
}
