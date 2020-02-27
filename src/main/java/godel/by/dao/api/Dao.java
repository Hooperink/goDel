package godel.by.dao.api;

import godel.by.entity.Identifiable;
import godel.by.exception.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> getById(long id) throws DaoException;
    void save (T item, String query) throws DaoException;
}
