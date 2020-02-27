package godel.by.services;

import godel.by.dao.DaoHelperFactory;
import godel.by.dao.api.DirectorDao;
import godel.by.dao.api.MovieDao;
import godel.by.dao.impl.DaoHelper;
import godel.by.entity.Director;
import godel.by.entity.Movie;
import godel.by.exception.DaoException;

import java.time.LocalDate;

public class DirectorService {

    private DaoHelperFactory daoHelperFactory;

    public DirectorService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void save(String firstName, String secondName, LocalDate birthDate) throws DaoException {
        Director director = createDirector(firstName, secondName, birthDate);
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            DirectorDao directorDao = daoHelper.createDirectorDao();
            directorDao.save(director);
        }
    }


    private Director createDirector(String firstName, String secondName, LocalDate birthDate) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setSecondName(secondName);
        director.setBirthDate(birthDate);
        return director;
    }

}