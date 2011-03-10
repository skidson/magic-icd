<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="friends"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Friends</h2>
				
					<c:choose>
						<c:when test="${!linked.magic}">
							<br><center>We can't see your friends! Have you linked up with your <a href="<c:url value="/basic/account" />">MAGIC account?</a></center><br>
						</c:when>
						<c:when test="${empty friendsList}">
							Sorry, you have no friends!
						</c:when>
						<c:otherwise>
							<table>
							<c:forEach items="${friendsList}" var="friend">
								<tr>
									<td rowSpan="2" align="center"> <img src="${friend.imageURL}" alt="${friend.name}'s picture" /> </td> 
									<td> <b><a href="<c:url value="/magic/userPage?userID=${friend.id}"/>">${friend.name}</b> </td>
									<td align="right"><a href="<c:url value="/magic/destroyFriend?friendID=${friend.id}"/>">Unfriend this person</a></td>
									
								</tr>
								<tr> <td>${friend.description}</td> </tr>
							</c:forEach>
							</table>
						</c:otherwise>
					</c:choose>
					
				<h2>Looking for someone?</h2><br>
				<table>
					<tr>
						<form method="post" action="/web_app/magic/userSearch">
						<center><b>Username or name to search for: </b><input type="text" size="40" name="searchQuery" />
						<input class="button" value=" Search " type="submit" /></center>
						</form>
					</tr>
				</table>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!--  wrap -->
</body>
</html>
