package org.markvarabyou.DAO.SQL;

import org.markvarabyou.entities.BoardColumn;
import org.markvarabyou.entities.interfaces.EntityDAO;

import java.util.LinkedList;

/**
 * SQL Data Access Object for BoardColumn entity.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:44 PM
 */
public class SqlBoardColumnDAO implements EntityDAO<BoardColumn> {
    @Override
    public BoardColumn create(BoardColumn entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BoardColumn read(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LinkedList<BoardColumn> read() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BoardColumn update(BoardColumn entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(int id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
