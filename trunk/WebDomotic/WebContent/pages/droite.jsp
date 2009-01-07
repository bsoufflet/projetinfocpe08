<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url id="contenuurl" value="/pages/contenu/%{selectedModule}.jsp" />
<s:div id="contenu" theme="ajax" href="%{contenuurl}"></s:div>