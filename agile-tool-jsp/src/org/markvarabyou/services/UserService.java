package org.markvarabyou.services;

import org.markvarabyou.dao.sql.SqlUserDao;
import org.markvarabyou.entities.exceptions.DaoException;
import org.markvarabyou.services.transfer.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service class for User entity.
 * User: Mark Varabyou
 * Date: 12/3/13
 * Time: 2:02 PM
 */
public class UserService {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/agile-tool-db";

    private static final String USER = "root";
    private static final String PASS = "1234";

    private SqlUserDao getSqlUserDao() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return new SqlUserDao(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        SqlUserDao sqlUserDao = getSqlUserDao();
        try {
            ArrayList<User> result = new ArrayList<User>();
            for (org.markvarabyou.entities.User u : sqlUserDao.read()) {
                User user = new User();
                user.setFirstName(u.getFirstName());
                user.setLastName(u.getLastName());
                user.setEmail(u.getEmail());
                user.setId(u.getId());
                result.add(user);
            }
            return result;
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
