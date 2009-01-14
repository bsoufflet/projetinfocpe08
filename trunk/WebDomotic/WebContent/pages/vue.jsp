<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<h1><s:property value="selectedModuleLabel"/></h1>
<s:action name="%{selectedAction}" executeResult="true"/>