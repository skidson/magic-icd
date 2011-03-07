<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="friends"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Search Results</h2>
					<c:choose>
						<c:when test="${empty searchResults}">
							Sorry, no users matched your search. Search another? <br>
							<table><tr><form method="post" action="/web_app/magic/userSearch">
								<center><b>Username or name to search for: </b><input type="text" size="40" name="searchQuery" />
								<input class="button" value=" Search " type="submit" /></center>
							</form></tr></table>
						</c:when>
						<c:otherwise>
							<table>
							<c:forEach items="${searchResults}" var="user">
								<tr>
									<td rowSpan="2" align="center"> <img src="${user.imageURL}" alt="${user.name}'s picture" /> </td>
									<td> <b><a href="<c:url value="/magic/userPage"/>">${user.name}</a></b> </td>
								</tr>
								<tr> <td>${user.description}</td> </tr>
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
