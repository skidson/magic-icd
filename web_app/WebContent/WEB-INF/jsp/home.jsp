<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>I R SUCCESS</title>
</head>
<body>
	Steve's friends are : 
	<c:forEach items="${relationships['friends']}" var="friend">
					<tr>
						<td>${friend}, </td>
					</tr>
	</c:forEach>
	<br>
	and he likes 
	<c:forEach items="${relationships['bits']}" var="bit">
					<tr>
						<td>${bit.name}, </td>
					</tr>
	</c:forEach>
		
</body>
</html>