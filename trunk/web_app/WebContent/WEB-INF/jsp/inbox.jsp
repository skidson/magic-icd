<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div  id="menu">
			<ul>
				<li><a href="home.html">Home</a></li>
				<li><a href="friends.html">Friends</a></li>
				<li><a href="bits.html">Bits</a></li>
				<li id="current"><a href="inbox.html">Inbox</a></li>
				<li class="last" ><a href="account.html">Account</a></li>
			</ul>
		</div>		
		
		<div id="content-wrap">
			<div id="main">
				<h2>Inbox</h2> 
				<br />
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