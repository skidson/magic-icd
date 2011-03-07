<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="account"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>			
		<div id="content-wrap">
			<div id="main">
				<h2>Change Password</h2>
				<table><form method="post" action="changePassword">
					<tr><td>Old Password </td><td><input type="text" size="30" name="in_oldpassword" /></td></tr>
					<tr><td>New Password <font color="red">*</font></td><td><input type="text" size="30" name="in_newPassword" /></td></tr>
					<tr><td>Re-type new Password <font color="red">*</font></td><td><input type="text" size="30" name="in_confirmPassword" /></td></tr>
					<tr><td><input class="button" value="          Change Password         " type="submit" /></td></tr>
				</form></table>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<!-- footer -->
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>
	</div>

</body>
</html>