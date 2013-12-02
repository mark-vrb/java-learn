package org.markvarabyou.services;

import com.google.gson.Gson;
import org.markvarabyou.entities.Board;
import org.markvarabyou.entities.exceptions.DaoException;
import org.markvarabyou.entities.interfaces.EntityDao;
import org.markvarabyou.services.entities.CollectionResult;
import org.markvarabyou.services.exceptions.EntityCreationFailedException;
import org.markvarabyou.services.exceptions.EntityNotFoundException;
import org.markvarabyou.services.exceptions.EntityUpdateFailedException;
import org.markvarabyou.services.exceptions.InternalDaoException;

import java.util.ArrayList;

/**
 * Boards service class.
 * User: Mark Varabyou
 * Date: 11/29/13
 * Time: 8:08 PM
 */
public class BoardsService {
    private EntityDao<Board> boardEntityDao;

    public BoardsService(EntityDao<Board> boardEntityDao){
        this.boardEntityDao = boardEntityDao;
    }

    public String get(int id) throws EntityNotFoundException, InternalDaoException {
        Gson gson = new Gson();
        Board board;
        try {
            board = boardEntityDao.read(id);
        } catch (DaoException e) {
            throw new InternalDaoException(e);
        }
        if (board == null)
            throw new EntityNotFoundException();
        return gson.toJson(board);
    }

    public String get() throws InternalDaoException {
        Gson gson = new Gson();
        ArrayList<Board> boards;
        try {
            boards = boardEntityDao.read();
        } catch (DaoException e) {
            throw new InternalDaoException();
        }
        CollectionResult<Board> result = new CollectionResult<Board>(boards);
        return gson.toJson(result);
    }

    public String post(String entity) throws EntityCreationFailedException {
        Gson gson = new Gson();
        Board board = gson.fromJson(entity, Board.class);
        Board created;
        try {
            created = boardEntityDao.create(board);
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
        Board board = gson.fromJson(entity, Board.class);
        board.setId(id);
        Board updated;
        try {
            updated = boardEntityDao.update(board);
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
            if (!boardEntityDao.delete(id)) {
                throw new EntityNotFoundException();
            }
        } catch (DaoException e) {
            throw new InternalDaoException(e);
        }
    }
}
