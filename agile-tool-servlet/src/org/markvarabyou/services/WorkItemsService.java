package org.markvarabyou.services;

import com.google.gson.Gson;
import org.markvarabyou.entities.WorkItem;
import org.markvarabyou.entities.exceptions.DaoException;
import org.markvarabyou.entities.interfaces.EntityDao;
import org.markvarabyou.services.entities.CollectionResult;
import org.markvarabyou.services.exceptions.EntityCreationFailedException;
import org.markvarabyou.services.exceptions.EntityNotFoundException;
import org.markvarabyou.services.exceptions.EntityUpdateFailedException;
import org.markvarabyou.services.exceptions.InternalDaoException;

import java.util.ArrayList;

/**
 * Service class for Work Items.
 * User: Mark Varabyou
 * Date: 12/2/13
 * Time: 2:49 PM
 */
public class WorkItemsService {
    private EntityDao<WorkItem> workItemEntityDao;

    public WorkItemsService(EntityDao<WorkItem> workItemEntityDao){
        this.workItemEntityDao = workItemEntityDao;
    }

    public String get(int id) throws EntityNotFoundException, InternalDaoException {
        Gson gson = new Gson();
        WorkItem workItem;
        try {
            workItem = workItemEntityDao.read(id);
        } catch (DaoException e) {
            throw new InternalDaoException(e);
        }
        if (workItem == null)
            throw new EntityNotFoundException();
        return gson.toJson(workItem);
    }

    public String get() throws InternalDaoException {
        Gson gson = new Gson();
        ArrayList<WorkItem> workItems;
        try {
            workItems = workItemEntityDao.read();
        } catch (DaoException e) {
            throw new InternalDaoException();
        }
        CollectionResult<WorkItem> result = new CollectionResult<WorkItem>(workItems);
        return gson.toJson(result);
    }

    public String post(String entity) throws EntityCreationFailedException {
        Gson gson = new Gson();
        WorkItem workItem = gson.fromJson(entity, WorkItem.class);
        WorkItem created;
        try {
            created = workItemEntityDao.create(workItem);
        } catch (DaoException e) {
            throw new EntityCreationFailedException(e);
        }
        if (created == null) {
            throw new EntityCreationFailedException();
        }
        return gson.toJson(created);
    }

    public String put(int id, String entity) throws EntityUpdateFailedException {
        Gson gson = new Gson();
        WorkItem workItem = gson.fromJson(entity, WorkItem.class);
        workItem.setId(id);
        WorkItem updated;
        try {
            updated = workItemEntityDao.update(workItem);
        } catch (DaoException e) {
            throw new EntityUpdateFailedException(e);
        }
        if (updated == null) {
            throw new EntityUpdateFailedException();
        }
        return gson.toJson(updated);
    }

    public void delete(int id) throws EntityNotFoundException, InternalDaoException {
        try {
            if (!workItemEntityDao.delete(id)) {
                throw new EntityNotFoundException();
            }
        } catch (DaoException e) {
            throw new InternalDaoException(e);
        }
    }
}
