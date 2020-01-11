<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Search</title>
</head>
<body>
	<h1>Film App!</h1>
	<p></p>
	<c:choose>
		<c:when test="${!empty film }">
			<table>
				<tr>
					<td>${film.id}</td>
					<td>${film.title}</td>
					<td>${film.description}</td>
					<td>${film.releaseYear }</td>
					<td>${film.rating }</td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<h3>NO FILM SELECTED</h3>
		</c:otherwise>
	</c:choose>
	<c:if test="${film.id > 1000}">
		<form action="Delete.do" method="POST">
			 <button type="submit" value="${film.id}" name="Delete"> Delete</button>
		</form>
	</c:if>
	<a href= "home.jsp" > Return Home </a>
</body>
</html>