package edu.school21.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Component
public class UsersRepositoryJdbcImpl implements UsersRepository {

    private HikariDataSource dataSource;
    private String tableName = "users";

    private String QUERY_TEMPLATE;
    private String FIND_BY_EMAIL_QUERY_TEMPLATE;
    private String SAVE_QUERY;
    private String UPDATE_QUERY;
    private String DELETE_QUERY;
    private String FIND_ALL_QUERY;


    @Autowired
    public UsersRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
        QUERY_TEMPLATE = "SELECT * FROM " + tableName + " WHERE id=?";
        FIND_BY_EMAIL_QUERY_TEMPLATE = "SELECT * FROM " + tableName + " WHERE email = ?";
        SAVE_QUERY = "INSERT INTO " + tableName + " (email, password) VALUES (?, ?)";
        UPDATE_QUERY = "UPDATE " + tableName + " SET email = ?, password = ? WHERE id = ?";
        DELETE_QUERY = "DELETE FROM " + tableName + " WHERE id = ?";
        FIND_ALL_QUERY = "SELECT * FROM " + tableName;
    }


    @Override
    public Optional<User> findById(Long id) {
        User ret = null;
        ResultSet resultSet = null;
        Connection connection;

        try {
            connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(QUERY_TEMPLATE);
            query.setLong(1, id);
            resultSet = query.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (resultSet.next()){
                ret = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(ret);
    }

    @Override
    public List<User> findAll() {
        List<User> productList = new LinkedList<>();
        ResultSet resultSet = null;

        try {
            Connection connection = dataSource.getConnection();
            resultSet = connection.createStatement().executeQuery(FIND_ALL_QUERY);

            while (resultSet.next()){
                productList.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productList;
    }

    @Override
    public void save(User entity) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3, entity.getIdentifier());
            preparedStatement.executeUpdate();
//            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User ret = null;
        ResultSet resultSet = null;
        Connection connection;

        try {
            connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(FIND_BY_EMAIL_QUERY_TEMPLATE);
            query.setString(1, email);
            resultSet = query.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (resultSet.next()){
                ret = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(ret);
    }
}
