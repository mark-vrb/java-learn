package org.markvarabyou.dao.sql;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.markvarabyou.dao.sql.helpers.SqlAgileToolDaoTestHelper;
import org.markvarabyou.entities.Board;
import org.markvarabyou.entities.BoardColumn;
import org.markvarabyou.entities.User;
import org.markvarabyou.entities.WorkItem;
import org.markvarabyou.entities.enums.BoardColumnType;
import org.markvarabyou.entities.enums.WorkItemType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Tests for Sql Data Access Object for entity WorkItem.
 * User: Mark Varabyou
 * Date: 11/7/13
 * Time: 9:44 PM
 */
public class SqlWorkItemDaoTest extends SqlAgileToolDaoTestHelper {
    private SqlWorkItemDao sqlWorkItemDao;

    private WorkItem getValidWorkItem(){
        SqlUserDao sqlUserDao = new SqlUserDao(connection);
        User user = sqlUserDao.create(new User("test", "test", "test@test.com"));
        SqlBoardDao sqlBoardDao = new SqlBoardDao(connection);
        Board board = sqlBoardDao.create(new Board("test", user.getId(), new Date()));
        SqlBoardColumnDao sqlBoardColumnDao = new SqlBoardColumnDao(connection);
        BoardColumn boardColumn = sqlBoardColumnDao.create(
                new BoardColumn("test", board.getId(), BoardColumnType.InProgress));
        return new WorkItem("test", "test", new Date(), user.getId(), user.getId(),
                (byte) 3, WorkItemType.Improvement, board.getId(), boardColumn.getId());
    }

    @Before
    public void setUp() throws Exception {
        setUpConnection();
        sqlWorkItemDao = new SqlWorkItemDao(connection);
    }

    @After
    public void tearDown() throws Exception {
        closeAndRollbackConnection();
    }

    @Test
    public void testCreateSuccess() {
        //Given
        WorkItem valid = getValidWorkItem();

        //When
        WorkItem created = sqlWorkItemDao.create(valid);

        //Then
        Assert.assertNotNull(created);
        WorkItem read = sqlWorkItemDao.read(created.getId());
        Assert.assertEquals(created.getId(), read.getId());
        Assert.assertEquals(created.getName(), read.getName());
        Assert.assertEquals(created.getDescription(), read.getDescription());
        Assert.assertEquals(created.getCreationDate(), read.getCreationDate());
        Assert.assertEquals(created.getCreatedByUserId(), read.getCreatedByUserId());
        Assert.assertEquals(created.getAssigneeUserId(), read.getAssigneeUserId());
        Assert.assertEquals(created.getSize(), read.getSize());
        Assert.assertEquals(created.getBoardId(), read.getBoardId());
        Assert.assertEquals(created.getType(), read.getType());
        Assert.assertEquals(created.getBoardColumnId(), read.getBoardColumnId());
    }

    @Test
    public void testCreateFailed() {
        //Given
        WorkItem invalid = getValidWorkItem();
        invalid.setBoardId(-1);

        //When
        WorkItem created = sqlWorkItemDao.create(invalid);

        //Then
        Assert.assertNull(created);
    }

    @Test
    public void testReadByIdSuccess(){
        //Given
        WorkItem expected = sqlWorkItemDao.create(getValidWorkItem());

        //When
        WorkItem actual = sqlWorkItemDao.read(expected.getId());

        //Then
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getCreationDate(), actual.getCreationDate());
        Assert.assertEquals(expected.getCreatedByUserId(), actual.getCreatedByUserId());
        Assert.assertEquals(expected.getAssigneeUserId(), actual.getAssigneeUserId());
        Assert.assertEquals(expected.getSize(), actual.getSize());
        Assert.assertEquals(expected.getBoardId(), actual.getBoardId());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getBoardColumnId(), actual.getBoardColumnId());
    }

    @Test
    public void testReadByIdFailed(){
        //Given
        int wrongKey = -1;

        //When
        WorkItem read = sqlWorkItemDao.read(wrongKey);

        //Then
        Assert.assertNull(read);
    }

    @Test
    public void testReadAll(){
        //Given
        WorkItem created = sqlWorkItemDao.create(getValidWorkItem());

        //When
        ArrayList<WorkItem> workItems = sqlWorkItemDao.read();

        //Then
        Assert.assertTrue(workItems.size() >= 1);
        boolean flag = false;
        for (WorkItem workItem : workItems){
            if (workItem.getId() == created.getId())
                flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdateSuccess(){
        //Given
        WorkItem changed = sqlWorkItemDao.create(getValidWorkItem());
        changed.setName("newTestName");

        //When
        WorkItem updated = sqlWorkItemDao.update(changed);

        //Then
        Assert.assertNotNull(updated);
        Assert.assertEquals(changed.getId(), updated.getId());
        Assert.assertEquals(changed.getName(), updated.getName());
    }

    @Test
    public void testUpdateFailed(){
        //Given
        WorkItem invalid = sqlWorkItemDao.create(getValidWorkItem());
        invalid.setId(-1); //Wrong id

        //When
        WorkItem updated = sqlWorkItemDao.update(invalid);

        //Then
        Assert.assertNull(updated);
    }

    @Test
    public void testDeleteSuccess(){
        //Given
        WorkItem valid = sqlWorkItemDao.create(getValidWorkItem());

        //When
        boolean isDeleted = sqlWorkItemDao.delete(valid.getId());

        //Then
        Assert.assertTrue(isDeleted);
        Assert.assertNull(sqlWorkItemDao.read(valid.getId()));
    }

    @Test
    public void testDeleteFailed(){
        //Given
        int wrongId = -1; //Wrong id

        //When
        boolean isDeleted = sqlWorkItemDao.delete(wrongId);

        //Then
        Assert.assertFalse(isDeleted);
    }
}
