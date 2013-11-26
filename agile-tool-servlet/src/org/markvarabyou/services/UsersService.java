package org.markvarabyou.services;

import com.google.gson.Gson;
import org.markvarabyou.entities.User;
import org.markvarabyou.entities.exceptions.DaoException;
import org.markvarabyou.entities.interfaces.EntityDao;
import org.markvarabyou.services.entities.CollectionResult;
import org.markvarabyou.services.exceptions.EntityCreationFailedException;
import org.markvarabyou.services.exceptions.EntityNotFoundException;
import org.markvarabyou.services.exceptions.EntityUpdateFailedException;
import org.markvarabyou.services.exceptions.InternalDaoException;

import java.util.ArrayList;

/**
 * Json service for users.
 * User: Mark Varabyou
 * Date: 11/21/13
 * Time: 2:03 PM
 */
public class UsersService {
    private EntityDao<User> userEntityDao;

    public UsersService(EntityDao<User> userEntityDao){
        this.userEntityDao = userEntityDao;
    }

    public String get(int id) throws EntityNotFoundException, InternalDaoException {
        Gson gson = new Gson();
        User user;
        try {
            user = userEntityDao.read(id);
        } catch (DaoException e) {
            throw new InternalDaoException(e);
        }
        if (user == null)
            throw new EntityNotFoundException();
        return gson.toJson(user);
    }

    public String get() throws InternalDaoException {
        Gson gson = new Gson();
        ArrayList<User> users;
        try {
            users = userEntityDao.read();
        } catch (DaoException e) {
            throw new InternalDaoException();
        }
        CollectionResult<User> result = new CollectionResult<User>(users);
        return gson.toJson(result);
    }

    public String post(String entity) throws EntityCreationFailedException {
        Gson gson = new Gson();
        User user = gson.fromJson(entity, User.class);
        User created;
        try {
            created = userEntityDao.create(user);
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
        User user = gson.fromJson(entity, User.class);
        user.setId(id);
        User updated;
        try {
            updated = userEntityDao.update(user);
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
            if (!userEntityDao.delete(id)) {
                throw new EntityNotFoundException();
            }
        } catch (DaoException e) {
            throw new InternalDaoException(e);
        }
    }
}
