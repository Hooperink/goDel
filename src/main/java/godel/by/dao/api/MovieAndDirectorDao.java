package godel.by.dao.api;

import godel.by.entity.Director;
import godel.by.entity.Movie;
import godel.by.exception.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MovieAndDirectorDao {
    void saveDirectorWithMovies(Director director, List<Movie> movies) throws DaoException;
    Map<Movie, Director> getDirectorAndMoviesByDirectorId(Long id) throws DaoException;
    Map<Movie, Director> getDirectorAndMoviesByDirectorIdAndDate(Long id, LocalDate dateBefore, LocalDate dateAfter) throws DaoException;
    Map<Movie, Director> getDirectorAndMoviesByDate(LocalDate dateBefore, LocalDate dateAfter) throws DaoException;
}
