package godel.by.command;

import godel.by.dao.DaoHelperFactory;
import godel.by.exception.UnknownCommandException;
import godel.by.services.DirectorAndMovieService;
import godel.by.services.DirectorService;

public class CommandFactory {
    public static Command create(String command) throws UnknownCommandException {
        switch (command) {
            case("addDirector"):
                return new AddDirectorAndMovieCommand(new DirectorService(new DaoHelperFactory()), new DirectorAndMovieService(new DaoHelperFactory()));
            case("showDirectorAndMovieResults"):
                return new ShowPageCommand("/jsp/directorAndMoviesResult.jsp");
            case("showInputDirectorSearchingPage"):
                return new ShowPageCommand("/jsp/pageForInputPropertiesForSearchingDirectorAndFilms.jsp");
            case("showAddDirectorPage"):
                return new ShowPageCommand("/jsp/addDirectorAndMovie.jsp");
            case("showSuccessPage"):
                return new ShowPageCommand("/jsp/successPage.jsp");
            case("getDirectorAndFilm"):
                return new GetDirectorAndFilmCommand(new DirectorAndMovieService(new DaoHelperFactory()));
            default:
                throw new UnknownCommandException("Unknown command " + command);
        }
    }
}
