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
				<form method="post" action="createBit"><table>
					<tr><td><b>Name: </b></td><td><input type="text" size="30" name="in_name" /></td></tr>
					<tr><td><b>Type: </b></td>
					<td>
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
					</td></tr>
					<tr><td><b>Location: </b></td>
					<td>
						<select name="in_place" size="1">
							<option value="1">None</option>
							<option value="2">Vancouver</option>
						</select>
					</td></tr>
					<tr>
						<td valign="top"><b>Description: </b></td>
						<td><textarea name="in_description" cols=40 rows=6></textarea></td>
					</tr>
				</table>
				<center><input class="button" value=" Submit " type="submit"/></center><br>
				</form>
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->

</body>
</html>