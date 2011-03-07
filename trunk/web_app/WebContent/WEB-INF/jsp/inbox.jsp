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
				<a href="composeMessage.htm?username=" " "><button> Compose Message </button></a>
				<table>
					SHOW MESSAGES HERE LULZ
				</table>
			</div> <!-- main -->
		</div> <!-- content-wrap -->
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->
</body>
</html>