<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<div id="logo">
<img src="<s:url value="/pages/logo.jpg"/>" height="112" width="664"><br></div>
Bonjour, <%= session.getAttribute( "username" ) %>! <a class="logout" href="<s:url action="logout" />">Logout</a>
<div id="coin"></div>

