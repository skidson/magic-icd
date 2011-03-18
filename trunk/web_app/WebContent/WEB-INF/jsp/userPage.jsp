<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="friends"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>			
		<div id="content-wrap">
			<div id="main">
				<h2>${friend.name}'s Profile!</h2>
				
				<c:choose>
					<c:when test="${linked.magic}">
						<table>
							<tr>
								<td rowSpan="2" align="center"> <img src="${friend.imageURL}" alt="${friend.name}'s picture" /> </td>
								<td> ${friend.name} </td> <c:if test="${alreadyFriend eq 'false'}"><td align="right"><a href="<c:url value="/magic/createFriend?friendID=${friend.id}"/>">Become a friend!</a></td></c:if>
							</tr>
							<tr>
								<td>${friend.description}</td>
							</tr>
							<tr>
								<td align="center"> <b>EXP: </b>${friend.exp}</td>
								<td> <b>Points: </b>${friend.points}</td>	
							</tr>
						</table>
					</c:when>
					
					<c:otherwise>
						<form method="get" action="/web_app/magic/account">
							<table><tr>
								<td><b>Have a MAGIC account? Link it to your CoffeeShop account for this session!</b></td>
								<td><input class="button" value=" Link your MAGIC account " type="submit"/></td>
							</tr></table>
						</form>
					</c:otherwise>
				</c:choose>
				
				
				<c:choose>
					<c:when test="${not empty linkedBits}">
						<h2> ${friend.name} likes :</h2>
						<table> <tr><th>Name</th><th>Type</th><th>Description</th></tr>
							<c:forEach var="bit" items="${linkedBits}">
			        			<tr><td><a href="<c:url value ="/magic/bit?bitID=${bit.id}"/>">${bit.name}</a></td><td>${bit.type}</td><td>${bit.description}</td></tr>
			     			</c:forEach>
		     			</table>
     				</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${not empty randomFriend}">
		     			<h2> ${friend.name} has some friends! :</h2>
		     			
		     			<table> <tr>
								<td rowSpan="2" align="center"> <img src="${randomFriend.imageURL}" alt="${randomFriend.name}'s picture" /> </td>
								<td> <b><a href="<c:url value="/magic/userPage?userID=${randomFriend.id}"/>">${randomFriend.name}</a></b> </td>
							</tr>
							<tr> <td>${randomFriend.description}</td> </tr>
						</table>
					</c:when>
				</c:choose>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div>

</body>
</html>