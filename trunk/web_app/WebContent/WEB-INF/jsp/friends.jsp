<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div  id="menu">
			<ul>
				<li><a href="home.html">Home</a></li>
				<li id="current"><a href="friends.html">Friends</a></li>
				<li><a href="bits.html">Bits</a></li>
				<li><a href="inbox.html">Inbox</a></li>
				<li class="last"><a href="account.html">Account</a></li>		
			</ul>
		</div>					
			
		<div id="content-wrap">
			<div id="main">
				<h2>Friends</h2>
				
					<c:forEach var="friend" items="${friends_list}">
						${friend}
						<!-- <li><img src="<c:url value="${friend}"/>"/></li> -->
					</c:forEach>
				
					<table border="3">
						<tr>
							<td rowspan="2" align="center"> PICTUREEE </td>
							<td align="center"> Friend name here? </td>
						</tr>
						<tr>
							
							<td align="center">Description or w/e</td>
						</tr>
					</table>
					
					<table border="3">
						<tr>
							<td rowspan="2" align="center"> PICTUREEE </td>
							<td align="center"> Friend name here? </td>
						</tr>
						<tr>
							
							<td align="center">Description or w/e</td>
						</tr>
					</table>
					
					<table border="3">
						<tr>
							<td rowspan="2" align="center"> PICTUREEE </td>
							<td align="center"> Friend name here? </td>
						</tr>
						<tr>
							
							<td align="center">Description or w/e</td>
						</tr>
					</table>
</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>	

	<!-- wrap ends here -->
	</div>

</body>
</html>