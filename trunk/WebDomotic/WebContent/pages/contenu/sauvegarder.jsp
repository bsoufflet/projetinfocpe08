<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<s:actionerror />
<s:actionmessage />
<s:action name="%{selectedAction}" executeResult="true"/>