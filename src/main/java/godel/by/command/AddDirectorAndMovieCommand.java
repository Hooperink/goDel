package godel.by.command;

import godel.by.exception.DaoException;
import godel.by.services.DirectorAndMovieService;
import godel.by.services.DirectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AddDirectorAndMovieCommand implements Command {

    private DirectorService directorService;
    private DirectorAndMovieService directorAndMovieService;

    public AddDirectorAndMovieCommand(DirectorService directorService, DirectorAndMovieService directorAndMovieService) {
        this.directorService = directorService;
        this.directorAndMovieService = directorAndMovieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String birthDateString = request.getParameter("birthDate");
        String[] names = request.getParameterValues("movie");
        String[] dates = request.getParameterValues("date");
        String[] genres = request.getParameterValues("genre");
        LocalDate birthDate = LocalDate.parse(birthDateString);
        if ((names != null) && (dates != null) && (genres != null)) {
            directorAndMovieService.saveWithMovies(firstName, secondName, birthDate, names, dates, genres);
        } else {
            directorService.save(firstName,secondName,birthDate);
        }
        return CommandResult.redirect("?command=showSuccessPage");
    }
}
