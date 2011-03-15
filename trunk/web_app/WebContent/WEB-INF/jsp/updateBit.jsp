<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="bits"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Update ${bit.name}</h2><br>
					<table>
						<form method="post" action="/web_app/magic/updateBitName?bitID=${bit.id}">
							<tr><td><b>Current Name:</b> ${bit.name}</td><td>New Name</td>
							<td><input type="text" size="30" name="in_name" /></td> <td> <input class="button" value="Update" type="submit"/></td></tr>
						</form>
						<form method="post" action="/web_app/magic/updateBitType?bitID=${bit.id}">
							<tr><td><b>Current Type:</b> ${bit.type}</td><td>New Type</td><td>
								<select name="in_type" size="1">
									<option value="1">Miscellaneous</option>
									<option value="2">Place</option>
									<option value="3">Table</option>
									<option value="4">Drink</option>
									<option value="5">Food</option>
									<option value="6">Display</option>
									<option value="7">Content</option>
									<option value="8">Person</option>
								</select>
							</td>
							<td><input class="button" value="Update" type="submit"/></td></tr>
						</form>
						<form method="post" action="/web_app/magic/updateBitDescription?bitID=${bit.id}">
							<tr>
							<td><b>Current Description: ${bit.description}</b></td><td> New Description </td>
							<td><input type="text" size="30" name="in_description"/></td>
							<td><input class="button" value="Update" type="submit"/></td></tr>
						</form>
					</table>
					<center><input class="button" type=button onClick="location.href='/web_app/magic/bits'" value='Done'/></center>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->

</body>
</html>