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
	<c:choose>
		<c:when test="${!empty film }">
			<table></table>
			<tr>
				<td>${film.id}</td>
				<td>${film.title}</td>
				<td>${film.description}</td>
				<td>${film.releaseYear }</td>
				<td>${film.rating }</td>
			</tr>
		</c:when>
		<c:otherwise>
			<h3>NO FILM SELECTED</h3>
		</c:otherwise>

	</c:choose>
</body>
</html>