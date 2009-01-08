<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />

<s:url id="contenuurl" value="/pages/contenu/%{selectedModule}.jsp" />
<s:div id="contenu" theme="ajax" href="%{contenuurl}"></s:div>