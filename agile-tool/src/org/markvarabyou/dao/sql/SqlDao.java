package org.markvarabyou.dao.sql;

import java.sql.*;

/**
 * Base class for SQL Data Access Objects.
 * User: Mark Varabyou
 * Date: 11/5/13
 * Time: 11:35 AM
 */
public abstract class SqlDao {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    protected Connection connection = null;
    protected PreparedStatement statement = null;

    public SqlDao(Connection connection){
        this.connection = connection;
    }

    protected void setupStatement(String sql) {
        try {
            Class.forName(JDBC_DRIVER);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void closeStatement(){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
