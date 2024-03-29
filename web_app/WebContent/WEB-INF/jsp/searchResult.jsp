<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="bits"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Search Results</h2>
					<c:choose>
						<c:when test="${empty bitsFound && empty usersFound}">
							Sorry, no matches were found. Search another? <br>
							<table><tr><form method="post" action="/web_app/magic/userSearch">
								<center><b>Keyword: </b><input type="text" size="40" name="searchQuery" />
								<input class="button" value=" Search " type="submit" /></center>
							</form></tr></table>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${not empty usersFound}">
									<table width = 95%>
									<c:forEach items="${usersFound}" var="result">
										<tr>
											<td rowSpan="2" align="center"> <img src="${result.imageURL}" alt="${result.name}'s picture" /> </td>
											<td> <b><a href="<c:url value="/magic/userPage?userID=${result.id}"/>">${result.name}</a></b> </td>
											<td align="right">
											<c:forEach items="${alreadyFriends}" var="friend"> 
												<c:if test="${friend.username eq result.username}">
													<c:set var="flag" value="matched"/>
												</c:if>
											</c:forEach>
											<c:if test="${flag ne 'matched'}">
												<a href="<c:url value="/magic/createFriend?friendID=${result.id}"/>">Become a friend!</a>
											</c:if>
											</td>
										</tr>
										<tr> <td>${result.description}</td> </tr>
										<c:set var="flag" value="reset"/>
									</c:forEach>
									</table>
								</c:when>
								<c:when test="${!empty bitsFound}">
									<table width = 95%>
										<tr><th>Name</th><th>Type</th><th>Description</th></tr>
										<c:forEach var="bit" items="${bitsFound}">
						        			<tr><td><a href="<c:url value ="/magic/bit?bitID=${bit.id}"/>">${bit.name}</a></td><td>${bit.type}</td><td>${bit.description}</td></tr>
						     			</c:forEach>
		     						</table> <br>
		     						<center> Page: <c:forEach var="i" begin="1" end="${numPages}" step="1"><c:if test="${i ne page}"><a href="<c:url value="/magic/bitSearch?searchQuery=${search}&page=${i}"/>"></c:if>${i}  </a></c:forEach></center>
		     						
		     					</c:when>
		     				</c:choose>
						</c:otherwise>
					</c:choose>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!--  wrap -->
</body>
</html>
