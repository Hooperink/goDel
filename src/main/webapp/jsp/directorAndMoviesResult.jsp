<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    </head>
    <body>
        <header>
            <link rel="stylesheet" type="text/css" href="/css/result.css"/>
            <link rel="icon" href="data:;base64,iVBORw0KGgo=">
        </header>
        <div class="a-container">
            <a href="movie?command=showAddDirectorPage">Add Director And Movies</a>
            <a href="movie?command=showInputDirectorSearchingPage">Search director and movies</a>
        </div>
        <div class="all-container">
            <c:choose>
                <c:when test = "${not empty directorAndMovies}">
                    <c:forEach items="${directorAndMovies}" var="entry">
                        <div class="all-solo-container">
                            <c:choose>
                                <c:when test = "${entry.value != null}">
                                    <div class="director">
                                        Director first name: "${entry.value.firstName}",&nbsp
                                        Director second name: "${entry.value.secondName}",&nbsp
                                        Director birth date: "${entry.value.birthDate}"&nbsp
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="director">
                                        No director found!
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test = "${entry.key != null}">
                                    <div class="movie">
                                        Movie: "${entry.key.name}",&nbsp
                                        Genre: "${entry.key.genre}",&nbsp
                                        Release date: "${entry.key.releaseDate}"&nbsp
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="movie">
                                        No movie found!
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="all-solo-container">
                        <div class="director">
                            No director found!
                        </div>
                        <div class="movie">
                            No movie found!
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>