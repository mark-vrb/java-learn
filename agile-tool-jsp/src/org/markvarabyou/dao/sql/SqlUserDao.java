package org.markvarabyou.dao.sql;

import org.markvarabyou.entities.User;
import org.markvarabyou.entities.exceptions.DaoException;
import org.markvarabyou.entities.interfaces.EntityDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SQL Data Access Object for User entity.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:42 PM
 */
public class SqlUserDao extends SqlDao implements EntityDao<User> {
    static final String CREATE_QUERY = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?);";
    static final String READ_ALL_QUERY = "SELECT * FROM users";
    static final String READ_QUERY = "SELECT * FROM users WHERE id = ?";
    static final String UPDATE_QUERY = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
    static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";

    public SqlUserDao(Connection connection) {
        super(connection);
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        return user;
    }

    @Override
    public User create(User entity) throws DaoException {
        User user = null;
        try {
            setupStatement(CREATE_QUERY);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            int key;
            if (resultSet != null && resultSet.next()) {
                key = resultSet.getInt(1);
                user = read(key);
            }
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public User read(int id) throws DaoException {
        User user = null;
        try {
            setupStatement(READ_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public ArrayList<User> read() throws DaoException {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            setupStatement(READ_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
            resultSet.close();
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public User update(User entity) throws DaoException {
        User user = null;
        try {
            setupStatement(UPDATE_QUERY);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setInt(4, entity.getId());
            if (statement.executeUpdate() == 1) {
                user = read(entity.getId());
            }
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        int affectedRows;
        try {
            setupStatement(DELETE_QUERY);
            statement.setInt(1, id);
            affectedRows = statement.executeUpdate();
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return affectedRows != 0;
    }
}
