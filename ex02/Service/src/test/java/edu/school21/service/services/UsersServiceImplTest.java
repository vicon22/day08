package edu.school21.service.services;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.service.config.ApplicationConfig;
import edu.school21.service.config.TestApplicationConfig;
import edu.school21.service.repositories.UsersRepositoryJdbcImpl;
import edu.school21.service.repositories.UsersRepositoryJdbcTemplateImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceImplTest {

    UsersServiceImpl usersService;

    @BeforeEach
    public void init() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        DataSource dataSource = context.getBean("hsqldbDataSource", DataSource.class);
        usersService = new UsersServiceImpl();
        usersService.setUsersRepository(new UsersRepositoryJdbcTemplateImpl(new JdbcTemplate(dataSource)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"admin@yandex.ru", "user@mail.ru"})
    public void isReturnedPassword(String arg) throws SQLException, NoSuchAlgorithmException {
        Assertions.assertNotNull(usersService.signUp(arg));
    }
}