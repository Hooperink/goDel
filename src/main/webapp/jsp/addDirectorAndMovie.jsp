<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="icon" href="data:;base64,iVBORw0KGgo=">
        <script type="text/javascript" src="/js/addField.js"></script>
        <link rel="stylesheet" type="text/css" href="/css/inputDirector.css"/>
    </head>
    <body>
        <header>
            <link rel="icon" href="data:;base64,iVBORw0KGgo=">
        </header>
        <div class="a-container">
            <a href="movie?command=showInputDirectorSearchingPage">Search director and movies</a>
        </div>
        <div class="all">
            <form method="post" id="myForm" action="movie">
                <input type="hidden" name="command" value="addDirector">
                <div class="input-info">
                    <div class="first-name">
                        Name: <input type="text" name="firstName">
                    </div>
                    <div class="second-name">
                        Second Name: <input type="text" name="secondName">
                    </div>
                    <div class="date">
                        Birth date: <input type="date" name="birthDate" required>
                    </div>
                </div>
                <div id="container"></div>
                <div class="button-container">
                    <input type="submit" value="Submit">
                    <input type="button" id="addMovie" value="Add movie" onclick="createMovieField()">
                </div>
            </form>
        </div>
    </body>
</html>