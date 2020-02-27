package godel.by.mapper;

import godel.by.entity.Director;
import godel.by.entity.Movie;
import godel.by.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public interface RowMapper<T extends Identifiable> {
    T map (ResultSet resultSet) throws SQLException;
    List<Object> getFieldsToSave(T item);

    static RowMapper<?> create(String table) {
        switch (table) {
            case Director.TABLE:
                return new DirectorRowMapper();
            case Movie.TABLE:
                return new MovieRowMapper();
            default:
                throw new IllegalArgumentException();
        }
    }
}
