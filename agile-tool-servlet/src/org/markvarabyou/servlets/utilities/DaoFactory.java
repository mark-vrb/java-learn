package org.markvarabyou.servlets.utilities;

import org.markvarabyou.dao.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Dao factory for servlets.
 * User: Mark_Varabyou
 * Date: 11/18/13
 * Time: 10:53 AM
 */
public class DaoFactory {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/agile-tool-db";

    private static final String USER = "root";
    private static final String PASS = "1234";

    private Connection connection;

    private SqlUserDao sqlUserDao;
    private SqlBoardDao sqlBoardDao;
    private SqlBoardColumnDao sqlBoardColumnDao;
    private SqlBoardUserRoleDao sqlBoardUserRoleDao;
    private SqlWorkItemDao sqlWorkItemDao;

    public DaoFactory() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(DB_URL, USER, PASS);

        sqlUserDao = new SqlUserDao(connection);
        sqlBoardDao = new SqlBoardDao(connection);
        sqlBoardColumnDao = new SqlBoardColumnDao(connection);
        sqlBoardUserRoleDao = new SqlBoardUserRoleDao(connection);
        sqlWorkItemDao = new SqlWorkItemDao(connection);
    }

    public SqlUserDao getSqlUserDao() {
        return sqlUserDao;
    }

    public SqlBoardDao getSqlBoardDao() {
        return sqlBoardDao;
    }

    public SqlBoardColumnDao getSqlBoardColumnDao() {
        return sqlBoardColumnDao;
    }

    public SqlBoardUserRoleDao getSqlBoardUserRoleDao() {
        return sqlBoardUserRoleDao;
    }

    public SqlWorkItemDao getSqlWorkItemDao() {
        return sqlWorkItemDao;
    }
}
