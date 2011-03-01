<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>User Page</title>
</head>
<body>
Hi im ${testuser.name}
<br>
${testuser.description}

I currently have ${testuser.exp}<c:if test="${testuser.exp} = 0"> cuz im a noob </c:if>
 experience and ${testuser.points} points
 
 <br>
 friends include <c:forEach items="${testuser.friends}" var="friends">
 					<td> ${friends}, </td> </c:forEach>
</body>
</html>