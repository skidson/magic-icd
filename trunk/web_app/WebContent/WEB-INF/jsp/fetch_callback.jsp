<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>OAuth Redirect</title>
</head>
<body>
	oauth_token=${callback.token}&oauth_token_secret=${callback.tokenSecret}&oauth_callback_confirmed=${callback.callbackConfirmed}
</body>
</html>