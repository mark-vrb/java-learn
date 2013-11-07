package org.markvarabyou.dao.sql.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helper for test classes of dao layer.
 * User: Mark Varabyou
 * Date: 11/6/13
 * Time: 7:14 PM
 */
public abstract class SqlAgileToolDaoTestHelper {
    protected static final String DB_URL = "jdbc:mysql://localhost:3306/agile-tool-db";

    protected static final String USER = "root";
    protected static final String PASS = "1234";

    protected Connection connection;

    protected void setUpConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        connection.setAutoCommit(false);
    }

    protected void closeAndRollbackConnection() throws SQLException {
        connection.rollback();
        connection.close();
    }
}
