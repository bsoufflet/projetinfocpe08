<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<b>Web Domotic</b><br>
Bonjour, <%= session.getAttribute( "name" ) %>! <a href="<%= request.getContextPath() %>/webdomotic/logout.action">Logout</a>

