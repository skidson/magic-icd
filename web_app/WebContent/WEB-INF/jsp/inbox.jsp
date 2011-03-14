<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="inbox"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Inbox</h2> 
				<br />
				<table> <th>Messages</th>
					<tr><td><a href="<c:url value="/magic/viewMessage?message_id=1"/>">Hey, Magic</a></td></tr>
					<tr><td><a href="<c:url value="/magic/viewMessage?message_id=2"/>">Want to go out for a coffee?</a></td></tr>
				</table>
				<a href="composeMessage.htm?username=" " "><button> Compose Message </button></a>
				
			</div> <!-- main -->
		</div> <!-- content-wrap -->
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->
</body>
</html>