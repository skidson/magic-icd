<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty user}" >
${user.username}
</c:if>