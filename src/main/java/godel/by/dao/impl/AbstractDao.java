package godel.by.dao.impl;

import godel.by.connection.ProxyConnection;
import godel.by.dao.api.Dao;
import godel.by.entity.Identifiable;
import godel.by.exception.DaoException;
import godel.by.mapper.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <T extends Identifiable> implements Dao<T> {

    protected ProxyConnection proxyConnection;

    protected AbstractDao(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    @Override
    public Optional<T> getById(long id) throws DaoException {
        String table = getTableName();
        RowMapper<T> rowMapper = (RowMapper<T>) RowMapper.create(table);
        return executeGetForSingleResultQuery("SELECT * FROM " + table + "WHERE id = ?", rowMapper, id);
    }

    private Optional<T> executeGetForSingleResultQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        List<T> entities = executeGetQuery(query, mapper, params);
        if (entities.size() == 1) {
            return Optional.of(entities.get(0));
        } else if (entities.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }
    private List<T> executeGetQuery (String query, RowMapper<T> mapper, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(query, params)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()){
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = proxyConnection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        return preparedStatement;
    }

    protected void executeSaveEntity(String query, T item) throws DaoException {
        String table = getTableName();
        RowMapper<T> rowMapper = (RowMapper<T>) RowMapper.create(table);
        List<Object> toSave = rowMapper.getFieldsToSave(item);
        try (PreparedStatement preparedStatement = createStatement(query, toSave.toArray())){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(T item, String query) throws DaoException {
        executeSaveEntity(query, item);
    }

    public abstract String getTableName();
}
