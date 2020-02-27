package godel.by.dao.api;

import godel.by.entity.Director;
import godel.by.entity.Identifiable;
import godel.by.exception.DaoException;

public interface DirectorDao<T extends Identifiable> {
    void save(Director director) throws DaoException;
    Long saveAndGetId(Director director) throws DaoException;
}
