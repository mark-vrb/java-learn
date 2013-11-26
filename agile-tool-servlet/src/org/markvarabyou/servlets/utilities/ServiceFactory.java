package org.markvarabyou.servlets.utilities;

import org.markvarabyou.dao.sql.*;
import org.markvarabyou.services.UsersService;
import org.markvarabyou.servlets.utilities.exceptions.ServiceSetupException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Dao factory for servlets.
 * User: Mark_Varabyou
 * Date: 11/18/13
 * Time: 10:53 AM
 */
public class ServiceFactory {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/agile-tool-db";

    private static final String USER = "root";
    private static final String PASS = "1234";

    public UsersService getUsersService() throws ServiceSetupException {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            SqlUserDao sqlUserDao = new SqlUserDao(connection);
            return new UsersService(sqlUserDao);
        } catch (ClassNotFoundException e) {
            throw new ServiceSetupException();
        } catch (SQLException e) {
            throw new ServiceSetupException();
        }

    }
}
