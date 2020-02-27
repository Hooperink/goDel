package godel.by.dao.impl;

import godel.by.connection.ProxyConnection;
import godel.by.dao.api.MovieAndDirectorDao;
import godel.by.entity.Director;
import godel.by.entity.Movie;
import godel.by.exception.DaoException;
import godel.by.mapper.DirectorAndMovieMapper;
import godel.by.mapper.DoubleRowMapper;
import godel.by.mapper.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieAndDirectorDaoImpl implements MovieAndDirectorDao {

    public static final String VALUES_FOR_SAVE_DIRECTOR_WITH_MOVIES = ", (?, ?, (SELECT qwe.id from qwe), ?)";
    public static final String SAVE_DIRECTOR_WITH_MOVIES = "WITH qwe AS (INSERT INTO director (first_name, second_name, birth_date)" +
            " VALUES (?, ?, ?) RETURNING id) " +
            "INSERT INTO film (name, genre, director_id, release_date) " +
            "VALUES (?, ?, (SELECT qwe.id from qwe), ?)";
    public static final String GET_VALUES_BY_DIRECTOR_ID = "SELECT director.first_name, director.second_name, director.birth_date, film.name, film.genre, film.release_date from director " +
            "LEFT OUTER JOIN film ON film.director_id = director.id " +
            "WHERE director.id = ?";
    public static final String GET_VALUES_BY_DIRECTOR_ID_AND_RELEASE_DATE = "SELECT director.first_name, director.second_name, director.birth_date, film.name, film.genre, film.release_date from director " +
            "LEFT OUTER JOIN film ON film.director_id = director.id " +
            "WHERE director.id = ? AND film.release_date BETWEEN ? AND ?";
    public static final String GET_VALUES_BY_RELEASE_DATE = "SELECT film.name, film.genre, film.release_date, director.first_name, director.second_name, director.birth_date FROM film " +
            "LEFT JOIN director ON film.director_id = director.id WHERE film.release_date BETWEEN ? AND ?";

    private ProxyConnection proxyConnection;

    public MovieAndDirectorDaoImpl(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    @Override
    public void saveDirectorWithMovies(Director director, List<Movie> movies) throws DaoException {
        executeSaveDirectorWithMovies(director, movies, SAVE_DIRECTOR_WITH_MOVIES);
    }

    @Override
    public Map<Movie, Director> getDirectorAndMoviesByDirectorId(Long id) throws DaoException {
        return executeGetForDirectorAndMovie(GET_VALUES_BY_DIRECTOR_ID, new DirectorAndMovieMapper(), id);
    }

    @Override
    public Map<Movie, Director> getDirectorAndMoviesByDirectorIdAndDate(Long id, LocalDate dateBefore, LocalDate dateAfter) throws DaoException {
        return executeGetForDirectorAndMovie(GET_VALUES_BY_DIRECTOR_ID_AND_RELEASE_DATE, new DirectorAndMovieMapper(), id, dateBefore, dateAfter);
    }

    @Override
    public Map<Movie, Director> getDirectorAndMoviesByDate(LocalDate dateBefore, LocalDate dateAfter) throws DaoException {
        return executeGetForDirectorAndMovie(GET_VALUES_BY_RELEASE_DATE, new DirectorAndMovieMapper(), dateBefore, dateAfter);
    }

    private Map<Movie, Director> executeGetForDirectorAndMovie(String query, DirectorAndMovieMapper directorAndMovieMapper, Object... params) throws DaoException {
        try(PreparedStatement preparedStatement = createStatement(query, params)){
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Movie, Director> films = new HashMap<>();
            while (resultSet.next()) {
               films.putAll(directorAndMovieMapper.map(resultSet));
            }
            return films;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    private void executeSaveDirectorWithMovies(Director director, List<Movie> movies, String query) throws DaoException {
        RowMapper<Director> rowMapperDirector = (RowMapper<Director>) RowMapper.create(Director.TABLE);
        RowMapper<Movie> rowMapperMovie = (RowMapper<Movie>) RowMapper.create(Movie.TABLE);
        List<Object> toSave = rowMapperDirector.getFieldsToSave(director);
        StringBuilder queryBuilder = new StringBuilder(query);
        for (int i = 0; i < movies.size(); i++) {
            if (i > 0) {
                queryBuilder.append(VALUES_FOR_SAVE_DIRECTOR_WITH_MOVIES);
            }
            toSave.addAll(rowMapperMovie.getFieldsToSave(movies.get(i)));
        }
        query = queryBuilder.toString();
        try (PreparedStatement preparedStatement = createStatement(query, toSave.toArray())){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = proxyConnection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        return preparedStatement;
    }
}
