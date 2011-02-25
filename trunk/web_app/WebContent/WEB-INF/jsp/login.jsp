<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<title>Coffee Shop</title>
	
</head>

<body>
<!-- wrap starts here -->
<div id="wrap">
		
		<!--header -->
		<div id="header">			
				
			<h1 id="logo-text"><a href="index.html">Coffee Shop</a></h1>		
			<p id="slogan">Blurring the lines of reality</p>		
			
			<div id="header-links">
				<p>
					<a href="index.html">Home</a> | 
					<a href="index.html">Contact</a> | 
					<a href="index.html">Site Map</a>			
				</p>		
			</div>		
						
		</div>
		
		<!-- menu -->	
		<div  id="menu">
			<ul>
				<li id="current"><a href="index.html">Home</a></li>
				<li><a href="index.html">Friends</a></li>
				<li><a href="index.html">Bits</a></li>
				<li><a href="index.html">Inbox</a></li>
				<li class="last"><a href="index.html">Account</a></li>		
			</ul>
		</div>					
			
		<!-- content-wrap starts here -->
		<div id="content-wrap">

			<div id="main">
				<h2>Login</h2>
				<center>
				<table><tr>
					<c:if test="${not empty param.error}">
						<font color="red">Login error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</font>
					</c:if>
					<form method="post" action="<c:url value="/j_spring_security_check" />">			
						<label>Username:</label><input type="text" size = "30" name="j_username" />
						<label>Password:</label><input type="password" size="30" name="j_password" /> <br />
						<a href="register.htm?error=false">Register</a>
						<br />
						<label><input type="checkbox" name="_spring_security_remember_me" /> Remember me</label>
						<center><input class="button" value="          Login          " type="submit" /></center>
					</form>		
				</tr></table>
				</center>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<!--footer starts here-->
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>	

<!-- wrap ends here -->
</div>

</body>
</html>
