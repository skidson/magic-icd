<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>CoffeShop</title>
</head>

<body>
	<div id="wrap">
		
		<div id="header">			
				
			<h1 id="logo-text"><a href="home.html">Coffee Shop</a></h1>		
			<p id="slogan">Blurring the lines of reality</p>		
			
			<div id="header-links">
				<p>
					<a href="home.html">Home</a> | 
					<a href="index.html">Contact</a> | 
					<a href="index.html">Site Map</a>			
				</p>		
			</div>		
						
		</div>
		
		<div  id="menu">
			<ul>
				<li><a href="home.html">Home</a></li>
				<li><a href="friends.html">Friends</a></li>
				<li><a href="bits.html">Bits</a></li>
				<li id="current"><a href="inbox.html">Inbox</a></li>
				<li><a href="account.html">Account</a></li>
			</ul>
		</div>		
		
		<div id="content-wrap">
			<div id="main">
				<h4>Mailbox</h4> <br />
				<a href="composeMessage.htm?username=" " "><button> Compose Message </button></a>
				<table>
					SHOW MESSAGES HERE LULZ
				</table>
			</div> <!-- main -->
		
		</div> <!-- content-wrap -->
					
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>	<!-- footer -->

	</div> <!-- wrap -->

</body>
</html>