package org.markvarabyou.dao.sql;

import org.markvarabyou.entities.BoardColumn;
import org.markvarabyou.entities.WorkItem;
import org.markvarabyou.entities.enums.BoardColumnType;
import org.markvarabyou.entities.enums.WorkItemType;
import org.markvarabyou.entities.interfaces.EntityDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Sql-based Data Access Object for WorkItem.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 1:18 PM
 */
public class SqlWorkItemDao extends SqlDao implements EntityDao<WorkItem> {
    static final String CREATE_QUERY = "INSERT INTO work_items (name, description, created_by_user_id," +
            " creation_date, assignee_user_id, size, type, board_id, board_column_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    static final String READ_ALL_QUERY = "SELECT * FROM work_items";
    static final String READ_QUERY = "SELECT * FROM work_items WHERE id = ?";
    static final String UPDATE_QUERY = "UPDATE work_items SET name = ?, description = ?, assignee_user_id = ?, size = ?, type = ?, board_column_id = ? WHERE id = ?"; //TODO: here
    static final String DELETE_QUERY = "DELETE FROM work_items WHERE id = ?";

    public SqlWorkItemDao(Connection connection) {
        super(connection);
    }

    private WorkItem getWorkItemFromResultSet(ResultSet resultSet) throws SQLException {
        WorkItem workItem = new WorkItem();
        workItem.setId(resultSet.getInt("id"));
        workItem.setName(resultSet.getString("name"));
        workItem.setDescription(resultSet.getString("description"));
        workItem.setCreatedByUserId(resultSet.getInt("created_by_user_id"));
        workItem.setCreationDate(resultSet.getDate("creation_date"));
        workItem.setAssigneeUserId(resultSet.getInt("assignee_user_id"));
        workItem.setSize(resultSet.getByte("size"));
        workItem.setType(WorkItemType.values()[resultSet.getInt("type")]);
        workItem.setBoardId(resultSet.getInt("board_id"));
        workItem.setBoardColumnId(resultSet.getInt("board_column_id"));
        return workItem;
    }

    @Override
    public WorkItem create(WorkItem entity) {
        setupStatement(CREATE_QUERY);
        WorkItem workItem = null;
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getCreatedByUserId());
            statement.setDate(4, new Date(entity.getCreationDate().getTime()));
            statement.setInt(5, entity.getAssigneeUserId());
            statement.setByte(6, entity.getSize());
            statement.setInt(7, entity.getType().ordinal());
            statement.setInt(8, entity.getBoardId());
            statement.setInt(9, entity.getBoardColumnId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            int key;
            if (resultSet != null && resultSet.next()) {
                key = resultSet.getInt(1);
                workItem = read(key);
            }
        } catch (SQLException ignored) {}
        closeStatement();
        return workItem;
    }

    @Override
    public WorkItem read(int id) {
        WorkItem workItem = null;
        setupStatement(READ_QUERY);
        try {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                workItem = getWorkItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return workItem;
    }

    @Override
    public ArrayList<WorkItem> read() {
        ArrayList<WorkItem> workItems = new ArrayList<WorkItem>();
        setupStatement(READ_ALL_QUERY);
        try{
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                workItems.add(getWorkItemFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return workItems;
    }

    @Override
    public WorkItem update(WorkItem entity) {
        setupStatement(UPDATE_QUERY);
        WorkItem workItem = null;
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getAssigneeUserId());
            statement.setByte(4, entity.getSize());
            statement.setInt(5, entity.getType().ordinal());
            statement.setInt(6, entity.getBoardColumnId());
            statement.setInt(7, entity.getId());
            if (statement.executeUpdate() == 1){
                workItem = read(entity.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return workItem;
    }

    @Override
    public boolean delete(int id) {
        setupStatement(DELETE_QUERY);
        int affectedRows = 0;
        try{
            statement.setInt(1, id);
            affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return affectedRows != 0;
    }
}
