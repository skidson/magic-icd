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
				
     			<table border="3">
						<tr>
							<td rowspan="2" align="center"> <img src="${magicuser.imageURL}" alt="${magicuser.name}'s picture" /> </td>
							<td align="center"> ${magicuser.name} </td>
						</tr>
						<tr>
							<td align="center">${magicuser.description}</td>
						</tr>
					</table>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<!-- footer -->
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>
	</div>

</body>
</html>
