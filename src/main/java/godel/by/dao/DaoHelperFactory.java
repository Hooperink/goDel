package godel.by.dao;

import godel.by.connection.ConnectionPool;
import godel.by.dao.impl.DaoHelper;

public class DaoHelperFactory {

    public DaoHelper create() {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
