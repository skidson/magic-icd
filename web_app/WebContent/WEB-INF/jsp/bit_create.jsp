<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="bits"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
			<h2>Create Bit</h2><br>
			<table><form method="post" action="bitSearch.html">
				<tr>
					<td><b>Name: </b></td><td><input type="text" size="30" name="in_name" /></td>
					<td><b>Type: </b></td><td><input type="text" size="30" name="in_type" /></td>
					<td><c:if test="${!empty bit.imageURL}"> <img src="${bit.imageURL}" /> </c:if></td>
				</tr>
				<tr colSpan="2">
					<b>Description: </b> <p>${bit.description}</p>
				</tr>
			</form></table>
			
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>	

	<!-- wrap ends here -->
	</div>

</body>
</html>