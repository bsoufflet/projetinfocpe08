<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<h1><s:property id="selectedModule" value="selectedModule"/></h1>
<s:action name="%{selectedAction}" executeResult="true"/>