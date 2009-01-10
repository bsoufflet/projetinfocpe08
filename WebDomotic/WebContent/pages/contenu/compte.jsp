<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<jsp:include page="%{selectedModule}.jsp"/>
<s:action name="%{selectedModule}"/>
