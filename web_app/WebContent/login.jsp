<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<table>
	<tr>
		<c:if test="${not empty param.error}">
			<font color="red">Login error:
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</font>
		</c:if>
		<form method="POST"
			action="<c:url value="/j_spring_security_check" />"><label>Username:</label><input
			type="text" size="30" name="j_username" /> <label>Password:</label><input
			type="password" size="30" name="j_password" /> <br />
		<a href="register.htm?error=false">Register</a> <br />
		<label><input type="checkbox"
			name="_spring_security_remember_me" /> Remember me</label>
		<center><input class="button"
			value="          Login          " type="submit" /></center>
		</form>
	</tr>
</table>
</body>
</html>