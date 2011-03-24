<%@ include file="/WEB-INF/jsp/include.jsp" %>

<c:choose>
	<c:when test="${android}">
		<jsp:forward page = "/mobile/android"></jsp:forward>
	</c:when>
	<c:otherwise>
		<jsp:forward page = "/basic/home"></jsp:forward>
	</c:otherwise>
</c:choose>