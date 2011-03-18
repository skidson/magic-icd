<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="bits"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Connect ${origBit.name} to :</h2>
					<c:choose>
						<c:when test="${empty bits}">
							Sorry, no matches were found. Search another? <br>
							<table><tr><form method="post" action="/web_app/magic/userSearch">
								<center><b>Keyword: </b><input type="text" size="40" name="searchQuery" />
								<input class="button" value=" Search " type="submit" /></center>
							</form></tr></table>
						</c:when>
						<c:otherwise>
							<table width = 95%>
								<tr><th>Name</th><th>Type</th><th>Description</th><th>Connect!</th></tr>
								<c:forEach var="bit" items="${bits}">
									<c:if test="${bit.name ne origBit.name}">
				        				<tr><td>${bit.name}</td><td>${bit.type}</td><td>${bit.description}</td><td><a href="<c:url value ="/magic/connectTo?bitID=${origBit.id}&tieID=${bit.id}"/>">Connect!</a></td></tr>
				     				</c:if>
				     			</c:forEach>
     						</table> <br>
     						
     						<center> Page: <c:forEach var="i" begin="1" end="${numPages}" step="1"><c:if test="${i ne page}"><a href="<c:url value="/magic/connectBits?bitID=${origBit.id}&page=${i}"/>"></c:if>${i}  </a></c:forEach></center>

							<h2>Know what you are looking for? </h2>
							<table><tr><form method="post" action="connectBits?bitID=${origBit.id}&page=1">
									<center><b>Keyword: </b><input type="text" size="40" name="searchQuery" />
							<input class="button" value=" Search " type="submit" /></center>
							</form></tr></table>
						</c:otherwise>
					</c:choose>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!--  wrap -->
</body>
</html>
