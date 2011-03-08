<!--header -->
<div id="header">
	<jsp:useBean id="linked" class="ca.ubc.magic.icd.web.model.Linked" scope="session" />		
		
	<h1 id="logo-text"><a href="<c:url value="/basic/home"/>">Coffee Shop</a></h1>		
	<p id="slogan">Blurring the lines of reality</p>		
	
	<c:if test="${!empty user}" >
		<div id="header-links">
			<p>Logged in as: ${user.username} | <a href="<c:url value="/j_spring_security_logout" />">Logout</a></p>		
		</div>
	</c:if>
				
</div>
<!-- menu -->	
<div  id="menu">
	<ul>
		<c:choose>
			<c:when test="${linked.magic}">
				<li <c:if test="${directory == 'home'}" >id="current"</c:if>><a href="<c:url value="/magic/home"/>">Home</a></li>
				<li <c:if test="${directory == 'friends'}" >id="current"</c:if>> <a href="<c:url value="/magic/friends"/>">Friends</a></li>
				<li <c:if test="${directory == 'bits'}" >id="current"</c:if>> <a href="<c:url value="/magic/bits"/>">Bits</a></li>
				<li <c:if test="${directory == 'inbox'}" >id="current"</c:if>> <a href="<c:url value="/magic/inbox"/>">Inbox</a></li>
				<li <c:if test="${directory == 'account'}" >id="current"</c:if> class="last"> <a href="<c:url value="/magic/account"/>">Account</a></li>
			</c:when>
				
			<c:otherwise>
				<li <c:if test="${directory == 'home'}" >id="current"</c:if>><a href="<c:url value="/basic/home"/>">Home</a></li>
				<li <c:if test="${directory == 'friends'}" >id="current"</c:if>> <a href="<c:url value="/basic/friends"/>">Friends</a></li>
				<li <c:if test="${directory == 'bits'}" >id="current"</c:if>> <a href="<c:url value="/basic/bits"/>">Bits</a></li>
				<li <c:if test="${directory == 'inbox'}" >id="current"</c:if>> <a href="<c:url value="/basic/inbox"/>">Inbox</a></li>
				<li <c:if test="${directory == 'account'}" >id="current"</c:if> class="last"> <a href="<c:url value="/basic/account"/>">Account</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>			