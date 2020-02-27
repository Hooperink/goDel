package godel.by.mapper;

import godel.by.entity.Movie;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Movie.ID);
        String name = resultSet.getString(Movie.NAME);
        String genre = resultSet.getString(Movie.GENRE);
        long directorId = resultSet.getLong(Movie.DIRECTOR_ID);
        LocalDate releaseDate = resultSet.getDate(Movie.RELEASE_DATE).toLocalDate();

        Movie movie = new Movie();
        movie.setId(id);
        movie.setName(name);
        movie.setGenre(genre);
        movie.setDirectorId(directorId);
        movie.setReleaseDate(releaseDate);

        return movie;
    }

    @Override
    public List<Object> getFieldsToSave(Movie item) {
        List<Object> toSave = new ArrayList<>();
        toSave.add(item.getName());
        toSave.add(item.getGenre());
        if (item.getDirectorId() != null) {
            toSave.add(item.getDirectorId());
        }
        toSave.add(item.getReleaseDate());
        return toSave;
    }
}
