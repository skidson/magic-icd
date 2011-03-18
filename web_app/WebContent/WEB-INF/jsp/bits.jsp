<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="bits"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Your Bits</h2>
				<c:choose>
					<c:when test="${linked.magic}">
						<c:choose>
							<c:when test="${empty bitsList}">
								Sorry you have no bits!
							</c:when>
							<c:otherwise>
								<table>
								<tr><th>Name</th><th>Type</th><th>Description</th><th>Update?</th></tr>
								<c:forEach var="bit" items="${bitsList}">
				        			<tr><td><a href="<c:url value ="/magic/bit?bitID=${bit.id}"/>">${bit.name}</a></td><td>${bit.type}</td><td>${bit.description}</td><td><a href="<c:url value="/magic/updateBit?bitID=${bit.id}"/>">Update this bit!</a></td></tr>
				     			</c:forEach>
				     			</table> <br>
				     		</c:otherwise>
				     	</c:choose>
		     			<h2>Search</h2><br>
		     			<table><tr><form method="post" action="bitSearch">
							<center><b>Keyword: </b><input type="text" size="40" name="searchQuery" />
							<input class="button" value=" Search " type="submit" /></center>
						</form></tr></table>
						
						<form method="get" action="/web_app/magic/createBit">
							<table align=right><tr>
								<td><b>Can't find a bit? </b></td>
								<td><input class="button" value=" Create Bit " type="submit"/></td>
							</tr></table>
						</form>
					</c:when>
					
					<c:otherwise>
						<br><center>We can't see your bits! Have you linked up with your <a href="<c:url value="/basic/account" />">MAGIC account?</a></center><br>
					</c:otherwise>
					
				</c:choose>
				
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->

</body>
</html>