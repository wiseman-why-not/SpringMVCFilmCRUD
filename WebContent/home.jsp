<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC Film Site</title>
</head>
<body>
	<h3>Select a Film</h3>
    <form action="FilmSelect.do" method="GET">
        <input type="text" name="filmId" /> <input
            type="submit" value="Select a film by id" />
    </form>
    <hr>
    	<h3>Add a new Film</h3>
    <form action="CreateFilm.do" method="GET">
        Title: <input type="text" name="title" > <br> 
        Description: <input type="text" name="description" > <br> 
        Release year: <input type="number" name="releaseYear" MIN="1950" MAX="2020" step="1" > <br> 
        Rental Duration: <input type="number" name="rentalDuration" MIN="1" MAX="10" > <br>
        Rental Rate: <input type="number" name="rentalRate" step=".5" MIN="1" MAX="5" > <br>
        Length of Film (minutes): <input type="number" name="lengthOfMovie" step="1" MIN="10" MAX="999" > <br>
        Replacement Cost: <input type="number" name="replacementCost" step=".5" MIN="10" MAX="20" > <br>
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
        <input type="submit" value="Create Film" />
    </form>
    <hr>

<br>
</body>
</html>