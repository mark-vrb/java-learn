package org.markvarabyou.dao.sql;

import org.markvarabyou.entities.Board;
import org.markvarabyou.entities.interfaces.EntityDao;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * SQL Data Access Object for Board entity.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:40 PM
 */
public class SqlBoardDao implements EntityDao<Board> {
    @Override
    public Board create(Board entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Board read(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList<Board> read() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Board update(Board entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(int id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
