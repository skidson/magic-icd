<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="bits"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
			<h2>${bit.name}</h2><br>
			<table>
				<tr>
					<td><b>Type: </b> ${bit.type}</td>
					<td><c:if test="${!empty bit.imageURL}"> <img src="${bit.imageURL}" /> </c:if></td>
				</tr>
				<tr>
					<td colSpan="2"><b>Description: </b> <p>${bit.description}</p></td>
				</tr>
			</table>
			<c:if test="${empty magicUser.bits}">
				<a href="<c:url value="/magic/linkBit?bitID=${bit.id}"/>">Link to this bit!</a>
			</c:if>
			
			<c:set var="test" value="false"/>
			<c:forEach items="${magicUser.bits}" var="userBit">
				<c:if test="${bit.id eq userBit.id}">
					<c:set var="test" value="true"/>
				</c:if>
			</c:forEach>
			<input style="float:right; margin-right:100px" class="button" type=button onClick="location.href='/web_app/magic/connectBits?bitID=${bit.id}&page=1'" value='Connect this bit to another!'/>
			<c:if test="${test ne 'true'}">
				<a href="<c:url value="/magic/linkBit?bitID=${bit.id}"/>">Link to this bit!</a>
			</c:if>
			
			<c:if test="${test eq 'true'}">
				<a href="<c:url value="/magic/destroyLink?bitID=${bit.id}"/>">Destory the link to this bit!</a>
			</c:if>
			
			<c:choose>
				<c:when test="${not empty userLinks}">
					<h2>This bit is linked to the following users</h2>
					<table>
						<c:forEach items="${userLinks}" var="linkedUser">
							<c:if test="${user.username ne linkedUser.username}">
								<tr>
									<td rowSpan="2" align="center"> <img src="${linkedUser.imageURL}" alt="${linkedUser.name}'s picture" /> </td>
									<td> <b><a href="<c:url value="/magic/userPage?userID=${linkedUser.id}"/>">${linkedUser.name}</a></b> </td>
								</tr>
								<tr> <td>${linkedUser.description}</td> </tr>
							</c:if>
						</c:forEach>
					</table><br>
				</c:when>
			</c:choose>
				
			<c:choose>
				<c:when test="${not empty bitLinks}">
					<h2>This bit is linked to the following bits</h2>
						<table>
							<tr><th>Name</th><th>Type</th><th>Description</th></tr>
							<c:forEach var="linkedBit" items="${bitLinks}">
			        			<tr><td><a href="<c:url value ="/magic/bit?id=${linkedBit.id}"/>">${linkedBit.name}</a></td><td>${linkedBit.type}</td><td>${linkedBit.description}</td></tr>
			     			</c:forEach>
		     			</table>
		     	</c:when>
		     </c:choose>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap ends here -->
</body>
</html>