<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    </head>
    <body>
        <header>
            <link rel="stylesheet" type="text/css" href="/css/inputDirector.css"/>
            <link rel="icon" href="data:;base64,iVBORw0KGgo=">
        </header>
        <div class="a-container">
            <a href="movie?command=showAddDirectorPage">Add Director And Movies</a>
        </div>
        <div class="all">
            <form method="post" action="movie">
                <input type="hidden" name="command" value="getDirectorAndFilm">
                <div class="input-info">
                    id:
                    <input type="text" name="id">
                    Date before:
                    <input type="date" name="dates">
                    Date after:
                    <input type="date" name="dates">
                </div>
                <input type="submit">
            </form>
        </div>
    </body>
</html>