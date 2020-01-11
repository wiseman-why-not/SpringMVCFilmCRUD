<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Search</title>
</head>
<body>
<h1>Here is the film you requested</h1>
<c:choose>
<c:when test="${!empty film }"> ${film}
</c:when>
<c:otherwise><h3> NO FILM FOUND</h3></c:otherwise>

</c:choose>
</body>
</html>