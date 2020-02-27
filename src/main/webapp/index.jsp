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
        <div class="all">
            <div class="text">
                Hello, this is simple web app! Please, choose what you want to do.
                There is no server validation, so pls enter valid values.
            </div>
            <div class="a-container">
                <a href="movie?command=showAddDirectorPage">Add Director And Movies</a>
                <a href="movie?command=showInputDirectorSearchingPage">Search director and movies</a>
            </div>
        </div>
    </body>
</html>