package org.markvarabyou.DAO.SQL;

import org.markvarabyou.entities.interfaces.EntityDAO;
import org.markvarabyou.entities.WorkItem;

import java.util.LinkedList;

/**
 * Sql-based Data Access Object for WorkItem.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 1:18 PM
 */
public class SqlWorkItemDAO implements EntityDAO<WorkItem> {
    @Override
    public WorkItem create(WorkItem entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public WorkItem read(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LinkedList<WorkItem> read() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public WorkItem update(WorkItem entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(int id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}