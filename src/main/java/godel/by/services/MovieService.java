package godel.by.services;

import godel.by.dao.DaoHelperFactory;

public class MovieService {
    private DaoHelperFactory daoHelperFactory;

    public MovieService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


}
