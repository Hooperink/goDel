package godel.by.controller;

import godel.by.command.Command;
import godel.by.command.CommandFactory;
import godel.by.command.CommandResult;
import godel.by.exception.DaoException;
import godel.by.exception.UnknownCommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        requestDispatcher(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        requestDispatcher(request, response);
    }

    private void requestDispatcher(HttpServletRequest request, HttpServletResponse response) {
        String stringCommand = request.getParameter("command");
        try {
            Command command = CommandFactory.create(stringCommand);
            CommandResult result = command.execute(request, response);
            if (result.isRedirect()) {
                response.sendRedirect(request.getContextPath() + result.getPage());
            } else {
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(result.getPage());
                requestDispatcher.forward(request, response);
            }
        } catch (DaoException | ServletException | IOException | UnknownCommandException e) {
            e.printStackTrace();
        }
    }
}
