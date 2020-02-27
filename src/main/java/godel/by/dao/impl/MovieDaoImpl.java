package godel.by.dao.impl;

import godel.by.connection.ProxyConnection;
import godel.by.dao.api.MovieDao;
import godel.by.entity.Movie;
import godel.by.exception.DaoException;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {

    public MovieDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    public static final String SAVE_MOVIE = "INSERT INTO film (name, genre, director_id, release_date) VALUES(?, ?, ?, ?)";

    @Override
    public void save(Movie movie) throws DaoException {
        super.save(movie, SAVE_MOVIE);
    }

    @Override
    public String getTableName() {
        return Movie.TABLE;
    }
}
