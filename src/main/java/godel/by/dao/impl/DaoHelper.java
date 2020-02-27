package godel.by.dao.impl;


import godel.by.connection.ConnectionPool;
import godel.by.connection.ProxyConnection;
import godel.by.dao.api.DirectorDao;
import godel.by.dao.api.MovieDao;
import godel.by.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection proxyConnection;

    public DaoHelper(ConnectionPool pool) {
        this.proxyConnection = pool.getConnectionFromPool();
    }

    public DirectorDaoImpl createDirectorDao() {
        return new DirectorDaoImpl(proxyConnection);
    }

    public MovieDaoImpl createMovieDao() {
        return new MovieDaoImpl(proxyConnection);
    }

    public MovieAndDirectorDaoImpl createMovieAndDirectorDao() {return new MovieAndDirectorDaoImpl(proxyConnection); }

    @Override
    public void close() {
        proxyConnection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            proxyConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            proxyConnection.commit();
            proxyConnection.setAutoCommit(true);
        } catch (SQLException e) {
            rollbackQuery();
            throw new DaoException(e);
        }
    }

    public void rollbackQuery() throws DaoException {
        try {
            proxyConnection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
