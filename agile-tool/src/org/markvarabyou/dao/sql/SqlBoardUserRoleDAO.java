package org.markvarabyou.dao.sql;

import org.markvarabyou.entities.BoardUserRole;
import org.markvarabyou.entities.enums.BoardUserRoleType;
import org.markvarabyou.entities.interfaces.RelationEntityDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SQL Data Access Object for BoardUserRole entity.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:48 PM
 */
public class SqlBoardUserRoleDao extends SqlDao implements RelationEntityDao<BoardUserRole> {
    static final String CREATE_QUERY = "INSERT INTO board_user_roles (board_id, user_id, type) VALUES (?, ?, ?);";
    static final String READ_ALL_QUERY = "SELECT * FROM board_user_roles";
    static final String READ_QUERY = "SELECT * FROM board_user_roles WHERE board_id = ? AND user_id = ?";
    static final String UPDATE_QUERY = "UPDATE board_user_roles SET type = ? WHERE board_id = ? AND user_id = ?";
    static final String DELETE_QUERY = "DELETE FROM board_user_roles WHERE board_id = ? AND user_id = ?";

    public SqlBoardUserRoleDao(Connection connection) {
        super(connection);
    }

    private BoardUserRole getBoardUserRoleFromResultSet(ResultSet resultSet) throws SQLException {
        BoardUserRole boardUserRole = new BoardUserRole();
        boardUserRole.setBoardId(resultSet.getInt("board_id"));
        boardUserRole.setUserId(resultSet.getInt("user_id"));
        boardUserRole.setType(BoardUserRoleType.values()[resultSet.getInt("type")]);
        return boardUserRole;
    }

    @Override
    public BoardUserRole create(BoardUserRole entity) {
        setupStatement(CREATE_QUERY);
        BoardUserRole boardUserRole = null;
        try {
            statement.setInt(1, entity.getBoardId());
            statement.setInt(2, entity.getUserId());
            statement.setInt(3, entity.getType().ordinal());
            if (statement.executeUpdate() == 1) {
                boardUserRole = read(entity.getBoardId(), entity.getUserId());
            }
        } catch (SQLException ignored) {}
        closeStatement();
        return boardUserRole;
    }

    @Override
    public BoardUserRole read(int firstId, int secondId) {
        BoardUserRole boardUserRole = null;
        setupStatement(READ_QUERY);
        try {
            statement.setInt(1, firstId);
            statement.setInt(2, secondId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                boardUserRole = getBoardUserRoleFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return boardUserRole;
    }

    @Override
    public ArrayList<BoardUserRole> read() {
        ArrayList<BoardUserRole> boardUserRoles = new ArrayList<BoardUserRole>();
        setupStatement(READ_ALL_QUERY);
        try{
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                boardUserRoles.add(getBoardUserRoleFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return boardUserRoles;
    }

    @Override
    public BoardUserRole update(BoardUserRole entity) {
        setupStatement(UPDATE_QUERY);
        BoardUserRole boardUserRole = null;
        try {
            statement.setInt(1, entity.getType().ordinal());
            statement.setInt(2, entity.getBoardId());
            statement.setInt(3, entity.getUserId());
            if (statement.executeUpdate() == 1){
                boardUserRole = read(entity.getBoardId(), entity.getUserId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return boardUserRole;
    }

    @Override
    public boolean delete(int firstId, int secondId) {
        setupStatement(DELETE_QUERY);
        int affectedRows = 0;
        try{
            statement.setInt(1, firstId);
            statement.setInt(2, secondId);
            affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return affectedRows != 0;
    }
}
