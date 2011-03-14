<jsp:forward page = "/basic/home"></jsp:forward>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="home"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>		
		<div id="content-wrap">
			<div id="main">
				<h2>Home</h2>
				<br>
				<table>
					<tr>
						<form method="post" action="userSearch.html">
						<center><input type="text" size="40" name="searchQuery" />
						<input class="button" value=" Search " type="submit" /></center>
						</form>
					</tr>
				</table>
				
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>	

	<!-- wrap ends here -->
	</div>

</body>
</html>
