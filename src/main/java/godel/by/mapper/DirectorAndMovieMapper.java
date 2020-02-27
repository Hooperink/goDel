package godel.by.mapper;

import godel.by.entity.Director;
import godel.by.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DirectorAndMovieMapper implements DoubleRowMapper<Movie, Director> {
    @Override
    public Map<Movie, Director> map(ResultSet resultSet) throws SQLException {
        Map<Movie, Director> films = new HashMap<>();

        String firstName = resultSet.getString(Director.FIRST_NAME);
        String secondName = resultSet.getString(Director.SECOND_NAME);
        LocalDate birthDate = resultSet.getDate(Director.BIRTH_DATE).toLocalDate();
        Director director = buildDirector(firstName, secondName, birthDate);

        String name = resultSet.getString(Movie.NAME);
        String genre = resultSet.getString(Movie.GENRE);
        LocalDate releaseDate = resultSet.getDate(Movie.RELEASE_DATE) != null ? resultSet.getDate(Movie.RELEASE_DATE).toLocalDate() : null;
        Movie movie = buildMovie(name, genre, releaseDate);
        films.put(movie, director);
        return films;
    }

    private Director buildDirector(String firstName, String secondName, LocalDate birthDate) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setSecondName(secondName);
        director.setBirthDate(birthDate);
        return director;
    }

    private Movie buildMovie(String name, String genre, LocalDate releaseDate) {
        if (name == null && genre == null & releaseDate == null) {
            return null;
        }
        Movie movie = new Movie();
        movie.setName(name);
        movie.setGenre(genre);
        movie.setReleaseDate(releaseDate);
        return movie;
    }
}
