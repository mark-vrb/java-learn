package org.markvarabyou.dao.sql;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.markvarabyou.dao.sql.helpers.SqlAgileToolDaoTestHelper;
import org.markvarabyou.entities.Board;
import org.markvarabyou.entities.BoardUserRole;
import org.markvarabyou.entities.User;
import org.markvarabyou.entities.enums.BoardUserRoleType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Tests for Sql Data Access Object for entity BoardUserRole.
 * User: Mark Varabyou
 * Date: 11/6/13
 * Time: 8:27 PM
 */
public class SqlBoardUserRoleDaoTest extends SqlAgileToolDaoTestHelper {
    private SqlBoardUserRoleDao sqlBoardUserRoleDao;

    private BoardUserRole getValidBoardUserRole(){
        SqlUserDao sqlUserDao = new SqlUserDao(connection);
        User user = sqlUserDao.create(new User("test", "test", "test@test.com"));
        SqlBoardDao sqlBoardDao = new SqlBoardDao(connection);
        Board board = sqlBoardDao.create(new Board("test", user.getId(), new Date()));
        return new BoardUserRole(board.getId(), user.getId(), BoardUserRoleType.Team);
    }

    @Before
    public void setUp() throws Exception {
        setUpConnection();
        sqlBoardUserRoleDao = new SqlBoardUserRoleDao(connection);
    }

    @After
    public void tearDown() throws Exception {
        closeAndRollbackConnection();
    }

    @Test
    public void testCreateSuccess() {
        //Given
        BoardUserRole valid = getValidBoardUserRole();

        //When
        BoardUserRole created = sqlBoardUserRoleDao.create(valid);

        //Then
        Assert.assertNotNull(created);
        BoardUserRole read = sqlBoardUserRoleDao.read(created.getBoardId(), created.getUserId());
        Assert.assertEquals(created.getUserId(), read.getUserId());
        Assert.assertEquals(created.getBoardId(), read.getBoardId());
        Assert.assertEquals(created.getType(), read.getType());
    }

    @Test
    public void testCreateFailed() {
        //Given
        BoardUserRole invalid = getValidBoardUserRole();
        invalid.setBoardId(-1);

        //When
        BoardUserRole created = sqlBoardUserRoleDao.create(invalid);

        //Then
        Assert.assertNull(created);
    }

    @Test
    public void testReadByIdSuccess(){
        //Given
        BoardUserRole expected = sqlBoardUserRoleDao.create(getValidBoardUserRole());

        //When
        BoardUserRole actual = sqlBoardUserRoleDao.read(expected.getBoardId(), expected.getUserId());

        //Then
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getBoardId(), actual.getBoardId());
        Assert.assertEquals(expected.getType(), actual.getType());
    }

    @Test
    public void testReadByIdFailed(){
        //Given
        int wrongKey = -1;

        //When
        BoardUserRole read = sqlBoardUserRoleDao.read(wrongKey, wrongKey);

        //Then
        Assert.assertNull(read);
    }

    @Test
    public void testReadAll(){
        //Given
        BoardUserRole created = sqlBoardUserRoleDao.create(getValidBoardUserRole());

        //When
        ArrayList<BoardUserRole> boardUserRoles = sqlBoardUserRoleDao.read();

        //Then
        Assert.assertTrue(boardUserRoles.size() >= 1);
        boolean flag = false;
        for (BoardUserRole boardUserRole : boardUserRoles){
            if (boardUserRole.getBoardId() == created.getBoardId() && boardUserRole.getUserId() == created.getUserId())
                flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdateSuccess(){
        //Given
        BoardUserRole changed = sqlBoardUserRoleDao.create(getValidBoardUserRole());
        changed.setType(BoardUserRoleType.ProductOwner);

        //When
        BoardUserRole updated = sqlBoardUserRoleDao.update(changed);

        //Then
        Assert.assertNotNull(updated);
        Assert.assertEquals(changed.getBoardId(), updated.getBoardId());
        Assert.assertEquals(changed.getUserId(), updated.getUserId());
        Assert.assertEquals(changed.getType(), updated.getType());
    }

    @Test
    public void testUpdateFailed(){
        //Given
        BoardUserRole invalid = sqlBoardUserRoleDao.create(getValidBoardUserRole());
        invalid.setBoardId(-1); //Wrong id

        //When
        BoardUserRole updated = sqlBoardUserRoleDao.update(invalid);

        //Then
        Assert.assertNull(updated);
    }

    @Test
    public void testDeleteSuccess(){
        //Given
        BoardUserRole valid = sqlBoardUserRoleDao.create(getValidBoardUserRole());

        //When
        boolean isDeleted = sqlBoardUserRoleDao.delete(valid.getBoardId(), valid.getUserId());

        //Then
        Assert.assertTrue(isDeleted);
        Assert.assertNull(sqlBoardUserRoleDao.read(valid.getBoardId(), valid.getUserId()));
    }

    @Test
    public void testDeleteFailed(){
        //Given
        int wrongId = -1; //Wrong id

        //When
        boolean isDeleted = sqlBoardUserRoleDao.delete(wrongId, wrongId);

        //Then
        Assert.assertFalse(isDeleted);
    }
}
