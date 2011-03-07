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
						<table>
							<tr>
								<td rowSpan="2" align="center"> <img src="${profile.imageURL}" alt="${profile.name}'s picture" /> </td>
								<td> ${profile.name} </td>
							</tr>
							<tr>
								<td>${profile.description}</td>
							</tr>
							<tr>
								<td align="center"> <b>EXP: </b>${profile.exp}</td>
								<td> <b>Points: </b>${profile.points}</td>	
							</tr>
						</table>
					
						CHECKIN TEST:
						<a href="<c:url value="/magic/checkinBit?id=1"/>">Checkin to bit 1 </a>
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
				<a href="<c:url value="/basic/forgotPassword"/>">Forgot your password?</a>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div>

</body>
</html>