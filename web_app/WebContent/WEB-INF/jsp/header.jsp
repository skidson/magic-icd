<!--header -->
<div id="header">			
		
	<h1 id="logo-text"><a href="index.html">Coffee Shop</a></h1>		
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
		<li <c:if test="${directory == 'home'}" >id="current"</c:if>> <a href="home.html">Home</a></li>
		<li <c:if test="${directory == 'friends'}" >id="current"</c:if>> <a href="friends.html">Friends</a></li>
		<li <c:if test="${directory == 'bits'}" >id="current"</c:if>> <a href="bits.html">Bits</a></li>
		<li <c:if test="${directory == 'inbox'}" >id="current"</c:if>> <a href="inbox.html">Inbox</a></li>
		<li <c:if test="${directory == 'aaccount'}" >id="current"</c:if> class="last"> <a href="account.html">Account</a></li>
	</ul>
</div>			