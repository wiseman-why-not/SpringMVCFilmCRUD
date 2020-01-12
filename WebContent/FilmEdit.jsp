<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Edit</title>
</head>
<body>
    <form action="CreateFilm.do" method="POST">
        Title: <input type="text" name="title" value ="${film.title }" required  maxlength="255"> <br> 
        Description: <input type="text" name="description" value="${film.description }" required > <br> 
        Release year: <input type="number" name="releaseYear" MIN="1950" MAX="2020" step="1" value="${film.releaseYear }" required > <br> 
        Rental Duration: <input type="number" name="rentalDuration" MIN="0" MAX="10" value="${film.rentalDuration }" required > <br>
        Rental Rate: <input type="number" name="rentalRate" step=".5" MIN="0" MAX="15" value= "${film.rentalRate }" required > <br>
        Length of Film (minutes): <input type="number" name="lengthOfMovie" step="1" MIN="0" MAX="185" value = "${film.length }" required> <br>
        Replacement Cost: <input type="number" name="replacementCost" step=".5" MIN="0" MAX="40"  value = "${film.replacementCost }" required> <br>
        Movie Rating: <br>
        <input type="radio" name="rating" value="PG" > PG <br>
        <input type="radio" name="rating" value="G" > G <br>
        <input type="radio" name="rating" value="NC17" > NC17 <br>
        <input type="radio" name="rating" value="PG13" > PG13 <br>
        <input type="radio" name="rating" value="R" > R <br>
        Special Features: <br>
        <input type="checkbox" name="specialFeatures" value="Commentaries" > Commentaries <br>
        <input type="checkbox" name="specialFeatures" value="Trailers" > Trailers <br>
        <input type="checkbox" name="specialFeatures" value="Deleted Scenes" > Deleted Scenes <br>
        <input type="checkbox" name="specialFeatures" value="Behind the Scenes" > Behind the Scenes <br>
        <input type="hidden" value="${film.filmId }" name="filmId">
        <input type="submit" value="Submit" />
    </form>
    <br>
    <a href= "home.jsp" > Return Home </a>
</body>
</html>