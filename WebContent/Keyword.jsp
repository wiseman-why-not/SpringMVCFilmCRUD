<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Film App!</h1>
	<p></p>
	<c:choose>
		<c:when test="${!empty films }">
		<c:forEach var="film" items="${films }">
			<table>
				<tr>
					<td>${film.filmId}</td>
					<td>${film.title}</td>
					<td>${film.description}</td>
					<td>${film.releaseYear }</td>
					<td>${film.rating }</td>

				</tr>
			</table>
			
			<h3>List of Actors</h3>
			<h5>${film.actorList }</h5>
			
			<h3>Category List</h3>
			<h5>${film.categoryList }</h5>
			<c:if test="${film.filmId > 1000}">
		<form action="Delete.do" method="POST">
			 <button type="submit" value="${film.filmId}" name="Delete"> Delete</button>
		</form>
	</c:if>
	<form action="FilmEdit.do" method="POST" >
			 <button type="submit" value="${film.filmId}" name="filmId"> Update</button>
		</form>
		<br>
		<hr>
			</c:forEach>
		</c:when>
		
		<c:otherwise>
			<h3>NO FILM SELECTED</h3>
		</c:otherwise>
	</c:choose>

	<a href= "home.jsp" > Return Home </a>
</body>
</html>