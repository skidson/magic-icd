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
						<c:when test="${empty friendsList}">
							Sorry, you have no friends!
						</c:when>
						<c:otherwise>
							<table>
							<c:forEach items="${friendsList}" var="friend">
								<tr>
									<td rowSpan="2" align="center"> <img src="${friend.imageURL}" alt="${friend.name}'s picture" /> </td>
									<td> <b>${friend.name}</b> </td>
								</tr>
								<tr> <td>${friend.description}</td> </tr>
							</c:forEach>
							</table>
						</c:otherwise>
					</c:choose>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!--  wrap -->
</body>
</html>
