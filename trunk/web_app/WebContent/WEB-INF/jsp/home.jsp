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
					<a href="home.html">Home</a> | 
					<a href="index.html">Contact</a> | 
					<a href="index.html">Site Map</a>			
				</p>		
			</div>		
						
		</div>
		
		<!-- menu -->	
		<div  id="menu">
			<ul>
				<li id="current"><a href="home.html">Home</a></li>
				<li><a href="friends.html">Friends</a></li>
				<li><a href="bits.html">Bits</a></li>
				<li><a href="inbox.html">Inbox</a></li>
				<li class="last"><a href="account.html">Account</a></li>		
			</ul>
		</div>					
			
		<!-- content-wrap starts here -->
		<div id="content-wrap">

			<div id="main">
				<h2>Home</h2>
				${randomString}
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
