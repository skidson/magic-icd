<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="home"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>		
		<div id="content-wrap">
			<div id="main">
				<h2>Home</h2>
				<br>
				<c:choose>
					<c:when test="${linked.magic}">
						<c:choose>
							<c:when test="${not empty friends}">
								<table>
									<c:set var="bitCheck" value="fail"/>
									<c:forEach var="friend" items="${friends}">
										<c:if test="${not empty friend.bits}">
											<c:set var="bitCheck" value="pass"/>
											<c:forEach var="friendLink" items="${friend.bits}">
												<tr><td><a href="<c:url value="/magic/userPage?userID=${friend.id}"/>">${friend.name}</a> is linked to <a href="<c:url value ="/magic/bit?bitID=${friendLink.id}"/>">${friendLink.name}</a> </td></tr>
											</c:forEach>
										</c:if>
									</c:forEach>
									<c:if test="${bitCheck eq 'fail'}">
										Sorry, all your friends are rather boring and are not connected to anything!
									</c:if>
								</table>
							</c:when>
							<c:otherwise>
								Sorry you have no friends, go to the friends page to search for some people you may know!
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<center><img src="http://www.magic.ubc.ca/496/pmwiki/uploads/New/tablenow.jpg"></img></center><br>
							<table><tr>
								<td><b>Have a MAGIC account? Link it to your CoffeeShop account for this session!</b></td>
								<td align="right"><input class="button" type=button onClick="location.href='/web_app/magic/account'" value='Link your MAGIC account!'/></td>
							</tr></table>
					</c:otherwise>
				</c:choose>
								
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->

</body>
</html>
