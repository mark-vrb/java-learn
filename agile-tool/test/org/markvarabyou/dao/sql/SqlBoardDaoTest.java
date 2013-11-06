package org.markvarabyou.dao.sql;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.markvarabyou.dao.sql.helpers.SqlAgileToolDaoTestHelper;
import org.markvarabyou.entities.Board;
import org.markvarabyou.entities.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Tests for Sql Data Access Object for entity Board.
 * User: Mark
 * Date: 11/6/13
 * Time: 6:49 PM
 */
public class SqlBoardDaoTest extends SqlAgileToolDaoTestHelper {
    private SqlBoardDao sqlBoardDao;

    private Board getValidBoard(){
        SqlUserDao sqlUserDao = new SqlUserDao(connection);
        User user = sqlUserDao.create(new User("test", "test", "test@test.com"));
        return new Board("test", user.getId(), new Date());
    }

    @Before
    public void setUp() throws Exception {
        setUpConnection();
        sqlBoardDao = new SqlBoardDao(connection);
    }

    @After
    public void tearDown() throws Exception {
        closeAndRollbackConnection();
    }

    @Test
    public void testCreateSuccess() {
        //Given
        Board valid = getValidBoard();

        //When
        Board created = sqlBoardDao.create(valid);

        //Then
        Assert.assertNotNull(created);
        Board read = sqlBoardDao.read(created.getId());
        Assert.assertEquals(created.getId(), read.getId());
        Assert.assertEquals(created.getName(), read.getName());
        Assert.assertEquals(created.getCreatedByUserId(), read.getCreatedByUserId());
        Assert.assertEquals(created.getCreationDate(), read.getCreationDate());
    }

    @Test
    public void testCreateFailed() {
        //Given
        Board invalid = getValidBoard();
        invalid.setCreatedByUserId(-1);

        //When
        Board created = sqlBoardDao.create(invalid);

        //Then
        Assert.assertNull(created);
    }

    @Test
    public void testReadByIdSuccess(){
        //Given
        Board expected = sqlBoardDao.create(getValidBoard());

        //When
        Board actual = sqlBoardDao.read(expected.getId());

        //Then
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getCreatedByUserId(), actual.getCreatedByUserId());
        Assert.assertEquals(expected.getCreationDate(), actual.getCreationDate());
    }

    @Test
    public void testReadByIdFailed(){
        //Given
        int wrongKey = -1;

        //When
        Board read = sqlBoardDao.read(wrongKey);

        //Then
        Assert.assertNull(read);
    }

    @Test
    public void testReadAll(){
        //Given
        Board created = sqlBoardDao.create(getValidBoard());

        //When
        ArrayList<Board> boards = sqlBoardDao.read();

        //Then
        Assert.assertTrue(boards.size() >= 1);
        boolean flag = false;
        for (Board board : boards){
            if (board.getId() == created.getId())
                flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdateSuccess(){
        //Given
        Board changed = sqlBoardDao.create(getValidBoard());
        changed.setName("newTestName");

        //When
        Board updated = sqlBoardDao.update(changed);

        //Then
        Assert.assertNotNull(updated);
        Assert.assertEquals(changed.getId(), updated.getId());
        Assert.assertEquals(changed.getName(), updated.getName());
    }

    @Test
    public void testUpdateFailed(){
        //Given
        Board invalid = sqlBoardDao.create(getValidBoard());
        invalid.setId(-1); //Wrong id

        //When
        Board updated = sqlBoardDao.update(invalid);

        //Then
        Assert.assertNull(updated);
    }

    @Test
    public void testDeleteSuccess(){
        //Given
        Board valid = sqlBoardDao.create(getValidBoard());

        //When
        boolean isDeleted = sqlBoardDao.delete(valid.getId());

        //Then
        Assert.assertTrue(isDeleted);
        Assert.assertNull(sqlBoardDao.read(valid.getId()));
    }

    @Test
    public void testDeleteFailed(){
        //Given
        int wrongId = -1; //Wrong id

        //When
        boolean isDeleted = sqlBoardDao.delete(wrongId);

        //Then
        Assert.assertFalse(isDeleted);
    }
}
