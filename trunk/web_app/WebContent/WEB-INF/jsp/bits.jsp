<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<body>
	<div id="wrap">
		<c:set var="directory" value="bits"/>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<div id="content-wrap">
			<div id="main">
				<h2>Your Bits</h2>
				<table>
				<tr><th>Name</th><th>Type</th><th>Description</th></tr>
				<c:forEach var="bit" items="${bitsList}">
        			<tr><td><a href="<c:url value ="/magic/bit?id=${bit.id}"/>">${bit.name}</a></td><td>${bit.type}</td><td>${bit.description}</td></tr>
     			</c:forEach>
     			</table> <br>
     			<h2>Search</h2><br>
     			<table><tr><form method="post" action="/web_app/magic/bitSearch">
					<center><b>Keyword: </b><input type="text" size="40" name="searchQuery" />
					<select name="searchFilter" size="1">
						<option value="all">All</option>
						<option value="place">Place</option>
						<option value="display">Display</option>
						<option value="content">Content</option>
						<option value="person">Person</option>
						<option value="drink">Drink</option>
						<option value="food">Food</option>
						<option value="table">Table</option>
					</select>
					<input class="button" value=" Search " type="submit" /></center>
				</form></tr></table>
				
				<c:if test="${linked.magic}">
					<form method="get" action="createBit">
						<table align=right><tr>
							<td><b>Can't find a bit? </b></td>
							<td><input class="button" value=" Create Bit " type="submit"/></td>
						</tr></table>
					</form>
				</c:if>
				
			</div> <!--  main --> 
		</div> <!-- content-wrap -->	
		<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div> <!-- wrap -->

</body>
</html>