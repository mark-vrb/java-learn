package org.markvarabyou.DAO.SQL;

import org.markvarabyou.entities.User;
import org.markvarabyou.entities.interfaces.EntityDAO;

import java.util.LinkedList;

/**
 * SQL Data Access Object for User entity.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:42 PM
 */
public class SqlUserDAO implements EntityDAO<User> {
    @Override
    public User create(User entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User read(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LinkedList<User> read() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User update(User entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(int id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
