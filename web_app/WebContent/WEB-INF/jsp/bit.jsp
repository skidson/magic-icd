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
				<tr colSpan="2">
					<b>Description: </b> <p>${bit.description}</p>
				</tr>
			</table>
			
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap ends here -->
</body>
</html>