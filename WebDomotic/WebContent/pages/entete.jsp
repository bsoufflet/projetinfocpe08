<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<b>Web Domotic</b><br>
Bonjour, <%= session.getAttribute( "username" ) %>! <a href="<s:url action="logout" />">Logout</a>

