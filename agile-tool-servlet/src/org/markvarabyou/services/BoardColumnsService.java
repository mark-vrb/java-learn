package org.markvarabyou.services;

import com.google.gson.Gson;
import org.markvarabyou.entities.BoardColumn;
import org.markvarabyou.entities.exceptions.DaoException;
import org.markvarabyou.entities.interfaces.EntityDao;
import org.markvarabyou.services.entities.CollectionResult;
import org.markvarabyou.services.exceptions.EntityCreationFailedException;
import org.markvarabyou.services.exceptions.EntityNotFoundException;
import org.markvarabyou.services.exceptions.EntityUpdateFailedException;
import org.markvarabyou.services.exceptions.InternalDaoException;

import java.util.ArrayList;

/**
 * Service class for Board Columns.
 * User: Mark Varabyou
 * Date: 12/2/13
 * Time: 2:49 PM
 */
public class BoardColumnsService {
    private EntityDao<BoardColumn> boardColumnEntityDao;

    public BoardColumnsService(EntityDao<BoardColumn> boardColumnEntityDao){
        this.boardColumnEntityDao = boardColumnEntityDao;
    }

    public String get(int id) throws EntityNotFoundException, InternalDaoException {
        Gson gson = new Gson();
        BoardColumn boardColumn;
        try {
            boardColumn = boardColumnEntityDao.read(id);
        } catch (DaoException e) {
            throw new InternalDaoException(e);
        }
        if (boardColumn == null)
            throw new EntityNotFoundException();
        return gson.toJson(boardColumn);
    }

    public String get() throws InternalDaoException {
        Gson gson = new Gson();
        ArrayList<BoardColumn> boardColumns;
        try {
            boardColumns = boardColumnEntityDao.read();
        } catch (DaoException e) {
            throw new InternalDaoException();
        }
        CollectionResult<BoardColumn> result = new CollectionResult<BoardColumn>(boardColumns);
        return gson.toJson(result);
    }

    public String post(String entity) throws EntityCreationFailedException {
        Gson gson = new Gson();
        BoardColumn boardColumn = gson.fromJson(entity, BoardColumn.class);
        BoardColumn created;
        try {
            created = boardColumnEntityDao.create(boardColumn);
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
        BoardColumn boardColumn = gson.fromJson(entity, BoardColumn.class);
        boardColumn.setId(id);
        BoardColumn updated;
        try {
            updated = boardColumnEntityDao.update(boardColumn);
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
            if (!boardColumnEntityDao.delete(id)) {
                throw new EntityNotFoundException();
            }
        } catch (DaoException e) {
            throw new InternalDaoException(e);
        }
    }
}
