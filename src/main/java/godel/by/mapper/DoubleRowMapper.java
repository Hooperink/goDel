package godel.by.mapper;

import godel.by.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface DoubleRowMapper<T extends Identifiable, V extends Identifiable> {
    Map <T,V> map(ResultSet resultSet) throws SQLException;
}
