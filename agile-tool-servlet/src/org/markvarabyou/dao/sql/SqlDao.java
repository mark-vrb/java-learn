package org.markvarabyou.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Base class for SQL Data Access Objects.
 * User: Mark Varabyou
 * Date: 11/5/13
 * Time: 11:35 AM
 */
public abstract class SqlDao {
    protected Connection connection = null;
    protected PreparedStatement statement = null;

    public SqlDao(Connection connection) {
        this.connection = connection;
    }

    protected void setupStatement(String sql) throws SQLException {
        statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    protected void closeStatement() throws SQLException {
        statement.close();
    }
}
