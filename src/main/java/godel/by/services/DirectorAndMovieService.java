package godel.by.services;

import godel.by.dao.DaoHelperFactory;
import godel.by.dao.impl.DaoHelper;
import godel.by.dao.impl.MovieAndDirectorDaoImpl;
import godel.by.entity.Director;
import godel.by.entity.Movie;
import godel.by.exception.DaoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectorAndMovieService {
    private DaoHelperFactory daoHelperFactory;

    public DirectorAndMovieService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void saveWithMovies(String firstName, String secondName, LocalDate birthDate, String[] names, String[] releaseDates, String[] genres) throws DaoException {
        Director director = createDirector(firstName, secondName, birthDate);
        List<Movie> movieList = new ArrayList<>();
        LocalDate releaseDate;
        for (int i = 0; i < names.length; i++) {
            releaseDate = LocalDate.parse(releaseDates[i]);
            Movie movie = createMovie(names[i], genres[i], releaseDate);
            movieList.add(movie);
        }
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MovieAndDirectorDaoImpl movieAndDirectorDao = daoHelper.createMovieAndDirectorDao();
            movieAndDirectorDao.saveDirectorWithMovies(director, movieList);
        }
    }

    public Map<Movie, Director> getByDirectorId(Long id) throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MovieAndDirectorDaoImpl movieAndDirectorDao = daoHelper.createMovieAndDirectorDao();
            return movieAndDirectorDao.getDirectorAndMoviesByDirectorId(id);
        }
    }

    public Map<Movie, Director> getByDirectorIdAndReleaseDate(Long id, LocalDate before, LocalDate after) throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MovieAndDirectorDaoImpl movieAndDirectorDao = daoHelper.createMovieAndDirectorDao();
            return movieAndDirectorDao.getDirectorAndMoviesByDirectorIdAndDate(id, before, after);
        }
    }

    public Map<Movie, Director> getByReleaseDate(LocalDate before, LocalDate after) throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MovieAndDirectorDaoImpl movieAndDirectorDao = daoHelper.createMovieAndDirectorDao();
            return movieAndDirectorDao.getDirectorAndMoviesByDate(before, after);
        }
    }

    private Movie createMovie(String name, String genre, LocalDate releaseDate) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setGenre(genre);
        movie.setReleaseDate(releaseDate);
        return movie;
    }


    private Director createDirector(String firstName, String secondName, LocalDate birthDate) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setSecondName(secondName);
        director.setBirthDate(birthDate);
        return director;
    }
}
