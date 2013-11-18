package org.markvarabyou.dao.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Base class for SQL Data Access Objects.
 * User: Mark Varabyou
 * Date: 11/5/13
 * Time: 11:35 AM
 */
public abstract class SqlDao {
    protected Connection connection = null;
    protected PreparedStatement statement = null;

    private static Logger logger = LogManager.getLogger(SqlDao.class.getName());

    public SqlDao(Connection connection){
        this.connection = connection;
    }

    protected void setupStatement(String sql) {
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    protected void closeStatement(){
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
