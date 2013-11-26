package org.markvarabyou.dao.sql;

import org.markvarabyou.entities.Board;
import org.markvarabyou.entities.exceptions.DaoException;
import org.markvarabyou.entities.interfaces.EntityDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SQL Data Access Object for Board entity.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:40 PM
 */
public class SqlBoardDao extends SqlDao implements EntityDao<Board> {
    static final String CREATE_QUERY = "INSERT INTO boards (name, created_by_user_id, creation_date) VALUES (?, ?, ?);";
    static final String READ_ALL_QUERY = "SELECT * FROM boards";
    static final String READ_QUERY = "SELECT * FROM boards WHERE id = ?";
    static final String UPDATE_QUERY = "UPDATE boards SET name = ?, created_by_user_id = ?, creation_date = ? WHERE id = ?";
    static final String DELETE_QUERY = "DELETE FROM boards WHERE id = ?";

    public SqlBoardDao(Connection connection) {
        super(connection);
    }

    private Board getBoardFromResultSet(ResultSet resultSet) throws SQLException {
        Board board = new Board();
        board.setId(resultSet.getInt("id"));
        board.setName(resultSet.getString("name"));
        board.setCreatedByUserId(resultSet.getInt("created_by_user_id"));
        board.setCreationDate(resultSet.getDate("creation_date"));
        return board;
    }

    @Override
    public Board create(Board entity) throws DaoException {
        Board board = null;
        try {
            setupStatement(CREATE_QUERY);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getCreatedByUserId());
            statement.setDate(3, new Date(entity.getCreationDate().getTime()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            int key;
            if (resultSet != null && resultSet.next()) {
                key = resultSet.getInt(1);
                board = read(key);
            }
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return board;
    }

    @Override
    public Board read(int id) throws DaoException {
        Board board = null;
        try {
            setupStatement(READ_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                board = getBoardFromResultSet(resultSet);
            }
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return board;
    }

    @Override
    public ArrayList<Board> read() throws DaoException {
        ArrayList<Board> boards = new ArrayList<Board>();
        try {
            setupStatement(READ_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                boards.add(getBoardFromResultSet(resultSet));
            }
            resultSet.close();
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return boards;
    }

    @Override
    public Board update(Board entity) throws DaoException {
        Board board = null;
        try {
            setupStatement(UPDATE_QUERY);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getCreatedByUserId());
            statement.setDate(3, new Date(entity.getCreationDate().getTime()));
            statement.setInt(4, entity.getId());
            if (statement.executeUpdate() == 1) {
                board = read(entity.getId());
            }
            closeStatement();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return board;
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
