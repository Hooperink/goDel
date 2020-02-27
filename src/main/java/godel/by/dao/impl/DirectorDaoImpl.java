package godel.by.dao.impl;

import godel.by.connection.ProxyConnection;
import godel.by.dao.api.DirectorDao;
import godel.by.entity.Director;
import godel.by.exception.DaoException;
import godel.by.mapper.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DirectorDaoImpl extends AbstractDao<Director> implements DirectorDao {

    public static final String SAVE_DIRECTOR = "INSERT INTO director (first_name, second_name, birth_date) VALUES (?, ?, ?)";
    public static final String SAVE_DIRECTOR_AND_RETURN_ID = "INSERT INTO director (first_name, second_name, birth_date) VALUES (?, ?, ?) RETURNING id";

    public DirectorDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public String getTableName() {
        return Director.TABLE;
    }

    @Override
    public void save(Director director) throws DaoException {
        super.save(director, SAVE_DIRECTOR);
    }

    @Override
    public Long saveAndGetId(Director director) throws DaoException {
        return executeSaveEntityWithReturningId(director);
    }

    private Long executeSaveEntityWithReturningId(Director item) throws DaoException {
        String table = getTableName();
        RowMapper<Director> rowMapper = (RowMapper<Director>) RowMapper.create(table);
        List<Object> toSave = rowMapper.getFieldsToSave(item);
        try (PreparedStatement preparedStatement = createStatement(DirectorDaoImpl.SAVE_DIRECTOR_AND_RETURN_ID, toSave.toArray())){
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}