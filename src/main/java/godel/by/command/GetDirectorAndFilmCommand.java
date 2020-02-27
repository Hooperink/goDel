package godel.by.command;

import godel.by.entity.Director;
import godel.by.entity.Movie;
import godel.by.exception.DaoException;
import godel.by.services.DirectorAndMovieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class GetDirectorAndFilmCommand implements Command {

    private DirectorAndMovieService directorAndMovieService;

    public GetDirectorAndFilmCommand(DirectorAndMovieService directorAndMovieService) {
        this.directorAndMovieService = directorAndMovieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DaoException, IOException, ServletException {
        String stringId = request.getParameter("id");
        Long id = null;
        if (!stringId.equals("")) {
            id = Long.parseLong(stringId);
        }
        String[] dates = request.getParameterValues("dates");
        LocalDate before = null;
        LocalDate after = null;
        if ((dates != null) && (!dates[0].equals("")) && (!dates[1].equals(""))) {
            before = LocalDate.parse(dates[0]);
            after = LocalDate.parse(dates[1]);
        }
        Map<Movie, Director> movieDirectorMap;
        if (id != null && ((before == null) && (after == null))) {
            movieDirectorMap = directorAndMovieService.getByDirectorId(id);
        } else if((id != null) && ((before != null) && (after != null))) {
            movieDirectorMap = directorAndMovieService.getByDirectorIdAndReleaseDate(id, before, after);
        } else {
            movieDirectorMap = directorAndMovieService.getByReleaseDate(before, after);
        }
        request.setAttribute("directorAndMovies", movieDirectorMap);
        return CommandResult.forward("/jsp/directorAndMoviesResult.jsp");
    }
}
