package org.markvarabyou.dao.sql;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.markvarabyou.dao.sql.helpers.SqlAgileToolDaoTestHelper;
import org.markvarabyou.entities.Board;
import org.markvarabyou.entities.BoardColumn;
import org.markvarabyou.entities.User;
import org.markvarabyou.entities.enums.BoardColumnType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Tests for Sql Data Access Object for entity BoardColumn.
 * User: Mark Varabyou
 * Date: 11/6/13
 * Time: 7:47 PM
 */
public class SqlBoardColumnDaoTest extends SqlAgileToolDaoTestHelper {
    private SqlBoardColumnDao sqlBoardColumnDao;

    private BoardColumn getValidBoardColumn(){
        SqlUserDao sqlUserDao = new SqlUserDao(connection);
        User user = sqlUserDao.create(new User("test", "test", "test@test.com"));
        SqlBoardDao sqlBoardDao = new SqlBoardDao(connection);
        Board board = sqlBoardDao.create(new Board("test", user.getId(), new Date()));
        return new BoardColumn("test", board.getId(), BoardColumnType.InProgress);
    }

    @Before
    public void setUp() throws Exception {
        setUpConnection();
        sqlBoardColumnDao = new SqlBoardColumnDao(connection);
    }

    @After
    public void tearDown() throws Exception {
        closeAndRollbackConnection();
    }

    @Test
    public void testCreateSuccess() {
        //Given
        BoardColumn valid = getValidBoardColumn();

        //When
        BoardColumn created = sqlBoardColumnDao.create(valid);

        //Then
        Assert.assertNotNull(created);
        BoardColumn read = sqlBoardColumnDao.read(created.getId());
        Assert.assertEquals(created.getId(), read.getId());
        Assert.assertEquals(created.getName(), read.getName());
        Assert.assertEquals(created.getBoardId(), read.getBoardId());
        Assert.assertEquals(created.getType(), read.getType());
    }

    @Test
    public void testCreateFailed() {
        //Given
        BoardColumn invalid = getValidBoardColumn();
        invalid.setBoardId(-1);

        //When
        BoardColumn created = sqlBoardColumnDao.create(invalid);

        //Then
        Assert.assertNull(created);
    }

    @Test
    public void testReadByIdSuccess(){
        //Given
        BoardColumn expected = sqlBoardColumnDao.create(getValidBoardColumn());

        //When
        BoardColumn actual = sqlBoardColumnDao.read(expected.getId());

        //Then
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBoardId(), actual.getBoardId());
        Assert.assertEquals(expected.getType(), actual.getType());
    }

    @Test
    public void testReadByIdFailed(){
        //Given
        int wrongKey = -1;

        //When
        BoardColumn read = sqlBoardColumnDao.read(wrongKey);

        //Then
        Assert.assertNull(read);
    }

    @Test
    public void testReadAll(){
        //Given
        BoardColumn created = sqlBoardColumnDao.create(getValidBoardColumn());

        //When
        ArrayList<BoardColumn> boardColumns = sqlBoardColumnDao.read();

        //Then
        Assert.assertTrue(boardColumns.size() >= 1);
        boolean flag = false;
        for (BoardColumn boardColumn : boardColumns){
            if (boardColumn.getId() == created.getId())
                flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdateSuccess(){
        //Given
        BoardColumn changed = sqlBoardColumnDao.create(getValidBoardColumn());
        changed.setName("newTestName");

        //When
        BoardColumn updated = sqlBoardColumnDao.update(changed);

        //Then
        Assert.assertNotNull(updated);
        Assert.assertEquals(changed.getId(), updated.getId());
        Assert.assertEquals(changed.getName(), updated.getName());
    }

    @Test
    public void testUpdateFailed(){
        //Given
        BoardColumn invalid = sqlBoardColumnDao.create(getValidBoardColumn());
        invalid.setId(-1); //Wrong id

        //When
        BoardColumn updated = sqlBoardColumnDao.update(invalid);

        //Then
        Assert.assertNull(updated);
    }

    @Test
    public void testDeleteSuccess(){
        //Given
        BoardColumn valid = sqlBoardColumnDao.create(getValidBoardColumn());

        //When
        boolean isDeleted = sqlBoardColumnDao.delete(valid.getId());

        //Then
        Assert.assertTrue(isDeleted);
        Assert.assertNull(sqlBoardColumnDao.read(valid.getId()));
    }

    @Test
    public void testDeleteFailed(){
        //Given
        int wrongId = -1; //Wrong id

        //When
        boolean isDeleted = sqlBoardColumnDao.delete(wrongId);

        //Then
        Assert.assertFalse(isDeleted);
    }
}
