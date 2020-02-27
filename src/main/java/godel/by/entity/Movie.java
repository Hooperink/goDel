package godel.by.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class Movie implements Serializable, Identifiable {
    public static final String TABLE = "film";
    public static final String ID = "id";
    public static final String GENRE = "genre";
    public static final String RELEASE_DATE = "release_date";
    public static final String NAME = "name";
    public static final String DIRECTOR_ID = "director_id";

    private Long id;
    private String genre;
    private LocalDate releaseDate;
    private String name;
    private Long directorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(name, movie.name) &&
                Objects.equals(directorId, movie.directorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre, releaseDate, name, directorId);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", name='" + name + '\'' +
                '}';
    }
}
