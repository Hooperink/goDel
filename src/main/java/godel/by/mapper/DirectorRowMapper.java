package godel.by.mapper;

import godel.by.entity.Director;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DirectorRowMapper implements RowMapper<Director>{
    @Override
    public Director map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Director.ID);
        String firstName = resultSet.getString(Director.FIRST_NAME);
        String secondName = resultSet.getString(Director.SECOND_NAME);
        LocalDate birthDate = resultSet.getDate(Director.BIRTH_DATE).toLocalDate();

        Director director = new Director();
        director.setId(id);
        director.setFirstName(firstName);
        director.setSecondName(secondName);
        director.setBirthDate(birthDate);

        return director;
    }

    @Override
    public List<Object> getFieldsToSave(Director item) {
        List<Object> toSave = new ArrayList<>();
        toSave.add(item.getFirstName());
        toSave.add(item.getSecondName());
        toSave.add(item.getBirthDate());
        return toSave;
    }
}
