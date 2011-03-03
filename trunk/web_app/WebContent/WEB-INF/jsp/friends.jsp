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
					<c:forEach items="{friendsList}" var="friend">
						<table border="3">
						<tr>
							<td rowspan="2" align="center"> <img src="${friend.photo}" alt="${friend.name}'s picture" /> </td>
							<td align="center"> ${friend.name} </td>
						</tr>
						<tr>
							<td align="center">${friend.description}</td>
						</tr>
					</table>
					</c:forEach>

</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>	

	<!-- wrap ends here -->
	</div>

</body>
</html>
