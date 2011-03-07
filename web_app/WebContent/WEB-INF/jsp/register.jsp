<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="account"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Register</h2>
				<center><font color="red">${error}</font></center>
				<table><form method="POST" action="register.html">
					<tr><td>First name: <font color="red">*</font></td><td><input type="text" size="30" name="in_firstName" /></td></tr>
					<tr><td>Last name: <font color="red">*</font></td><td><input type="text" size="30" name="in_lastName" /></td></tr>
					<tr><td>E-mail address: <font color="red">*</font></td><td><input type="text" size="30" name="in_email" /></td></tr>
					<tr><td>Username: <font color="red">*</font></td><td><input type="text" size="30" name="in_username" /></td></tr>
					<tr><td>Password: <font color="red">*</font></td><td><input type="password" size="30" name="in_password" /></td></tr>
					<tr><td>Confirm password: <font color="red">*</font></td><td><input type="password" size="30" name="in_confirmPassword" /></td></tr>
					<tr><td>Country: <font color="red">*</font></td><td><input type="text" size="30" name="in_country" /></td></tr>
					<tr><td><input class="button" value="          Create Account          " type="submit" /></td></tr>
				</form></table>
			</div> <!-- main -->
		</div> <!-- content-wrap -->
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->
</body>
</html>