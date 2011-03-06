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
					<c:if test="${empty friendsList}">
						Sorry, you have no friends!
					<c:choose>
						<c:when test="${!empty friendsList}">
							<c:forEach items="{friendsList}" var="friend">
								<table border="3">
									<tr>
										<td rowspan="2" align="center"> <img src="${friend.imageURL}" alt="${friend.name}'s picture" /> </td>
										<td align="center"> ${friend.name} </td>
									</tr>
									<tr>
										<td align="center">${friend.description}</td>
									</tr>
								</table>
							</c:forEach>
						</c:when>
					</c:choose>
					</c:if>
</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>	

	<!-- wrap ends here -->
	</div>

</body>
</html>
