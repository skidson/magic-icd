<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="account"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>			
		<div id="content-wrap">
			<div id="main">
				<h2>Account</h2>
				
				<c:choose>
					<c:when test="${user.linked}">
						<table border="3">
							<tr>
								<td rowspan="2" align="center"> <img src="${magicUser.imageURL}" alt="${magicUser.name}'s picture" /> </td>
								<td align="center"> ${magicUser.name} </td>
							</tr>
							<tr>
								<td align="center">${magicUser.description}</td>
							</tr>
							<tr>
								<td align="center"> EXP: ${magicUser.exp}</td>
								<td align="center"> Points: ${magicUser.points}</td>	
							</tr>
						</table>
					
						CHECKIN TEST:
						<a href="<c:url value="/magic/checkinBit?id=1"/>">Checkin to bit 1 </a>
					</c:when>
					
					<c:otherwise>
						<b>Have a MAGIC account? Link it to your CoffeeShop account for this session to!</b>
						<form method="get" action="/magic/account">
							<input class="button" value=" Link your MAGIC account " type="submit"/>
						</form>
					</c:otherwise>
				</c:choose>
				Linked to magic: ${user.linked} <br>
				
				
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<!-- footer -->
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>
	</div>

</body>
</html>