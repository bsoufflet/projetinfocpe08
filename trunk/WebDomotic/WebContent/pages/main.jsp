<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<html>
	<head>
		<title>Login Success</title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/main.js"/>"></script>
		<s:head theme="ajax" debug="false"/>
	</head>
	<body>
		<s:url id="enteteurl" value="/pages/entete.jsp" />
		<s:url id="menuurl" value="/pages/menu.jsp" />
		<s:url id="vueurl" action="vue" />
		
		<s:div id="entete" theme="ajax" href="%{enteteurl}"></s:div>
		<s:div id="menu" theme="ajax" href="%{menuurl}"></s:div>
		<s:div id="vue" theme="ajax" href="%{vueurl}" listenTopics="montrer_vue" formId="frm_menu"></s:div>
	</body>
</html>