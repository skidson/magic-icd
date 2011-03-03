<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
<!-- wrap starts here -->
<div id="wrap">
		<c:set var="directory" value="account"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>			
		<!-- content-wrap -->
		<div id="content-wrap">

			<div id="main">
				<h2>Account</h2>
				<c:forEach var="sparklrPhotoId" items="${photoIds}">
        			<img src="sparklr/photo?photo_id=${sparklrPhotoId}"/>
     			</c:forEach>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
					
		<!-- footer -->
		<div id="footer">
			<%@ include file="/WEB-INF/jsp/footer.jsp" %>
		</div>

<!-- wrap ends here -->
</div>

</body>
</html>
