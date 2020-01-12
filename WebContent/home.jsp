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
		<input type="text" name="filmId" /> <input type="submit"
			value="Find Film!" />
	</form>

	<hr>
	<h3>Add a new Film</h3>
	<form action="FilmEdit.do" method="POST">
		<button type="submit" value="0" name="filmId">Add a Film</button>
	</form>
	<hr>

</body>
</html>