package org.markvarabyou.dao.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.markvarabyou.entities.BoardColumn;
import org.markvarabyou.entities.enums.BoardColumnType;
import org.markvarabyou.entities.interfaces.EntityDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SQL Data Access Object for BoardColumn entity.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:44 PM
 */
public class SqlBoardColumnDao extends SqlDao implements EntityDao<BoardColumn> {
    static final String CREATE_QUERY = "INSERT INTO board_columns (name, type, board_id) VALUES (?, ?, ?);";
    static final String READ_ALL_QUERY = "SELECT * FROM board_columns";
    static final String READ_QUERY = "SELECT * FROM board_columns WHERE id = ?";
    static final String UPDATE_QUERY = "UPDATE board_columns SET name = ?, type = ?, board_id = ? WHERE id = ?";
    static final String DELETE_QUERY = "DELETE FROM board_columns WHERE id = ?";

    private static Logger logger = LogManager.getLogger(SqlBoardColumnDao.class.getName());

    public SqlBoardColumnDao(Connection connection) {
        super(connection);
    }

    private BoardColumn getBoardColumnFromResultSet(ResultSet resultSet) throws SQLException {
        BoardColumn boardColumn = new BoardColumn();
        boardColumn.setId(resultSet.getInt("id"));
        boardColumn.setName(resultSet.getString("name"));
        boardColumn.setType(BoardColumnType.values()[resultSet.getInt("type")]);
        boardColumn.setBoardId(resultSet.getInt("board_id"));
        return boardColumn;
    }

    @Override
    public BoardColumn create(BoardColumn entity) {
        setupStatement(CREATE_QUERY);
        BoardColumn boardColumn = null;
        try {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getType().ordinal());
            statement.setInt(3, entity.getBoardId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            int key;
            if (resultSet != null && resultSet.next()) {
                key = resultSet.getInt(1);
                boardColumn = read(key);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        closeStatement();
        return boardColumn;
    }

    @Override
    public BoardColumn read(int id) {
        BoardColumn boardColumn = null;
        setupStatement(READ_QUERY);
        try {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                boardColumn = getBoardColumnFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        closeStatement();
        return boardColumn;
    }

    @Override
    public ArrayList<BoardColumn> read() {
        ArrayList<BoardColumn> boardColumns = new ArrayList<BoardColumn>();
        setupStatement(READ_ALL_QUERY);
        try{
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                boardColumns.add(getBoardColumnFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        closeStatement();
        return boardColumns;
    }

    @Override
    public BoardColumn update(BoardColumn entity) {
        setupStatement(UPDATE_QUERY);
        BoardColumn boardColumn = null;
        try {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getType().ordinal());
            statement.setInt(3, entity.getBoardId());
            statement.setInt(4, entity.getId());
            if (statement.executeUpdate() == 1){
                boardColumn = read(entity.getId());
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        closeStatement();
        return boardColumn;
    }

    @Override
    public boolean delete(int id) {
        setupStatement(DELETE_QUERY);
        int affectedRows = 0;
        try{
            statement.setInt(1, id);
            affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        closeStatement();
        return affectedRows != 0;
    }
}
