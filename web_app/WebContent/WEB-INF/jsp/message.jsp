<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="inbox"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Message</h2> 
				<br />
				<form method="post" action="sendMessage.htm">
					<table>

						<tr><td>To: </td><td><input type="text" size="60" name="in_recipient" value=${replyName}></td></tr>
						<tr><td>Subject: </td><td><input type="text" size="60" name="in_subject" value=${reply.subject}></td></tr>
						<tr><td colspan="2"><center><textarea name="in_text"></textarea></center></td></tr>
						<tr><td colspan="2"><center><input class="button" value="       Send       " type="submit" /></center></td></tr>
					</table>
				</form>	
			</div> <!-- main -->
		</div> <!-- content-wrap -->
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->
</body>
</html>