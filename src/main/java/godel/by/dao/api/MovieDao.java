package godel.by.dao.api;

import godel.by.entity.Identifiable;
import godel.by.entity.Movie;
import godel.by.exception.DaoException;

public interface MovieDao<T extends Identifiable> {
    void save(Movie movie) throws DaoException;
}
