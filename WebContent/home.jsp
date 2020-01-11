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
        <input type="text" name="filmId" size="2" /> <input
            type="submit" value="Select a film by id" />
    </form>
</body>
</html>