<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" /> 
		
		<title>WebDomotic</title>
		
		<link rel="stylesheet" type="text/css" href="<s:url value="/javascript/yui/fonts/fonts-min.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<s:url value="/javascript/yui/paginator/assets/skins/sam/paginator.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<s:url value="/javascript/yui/button/assets/skins/sam/button.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/javascript/yui/container/assets/skins/sam/container.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/javascript/yui/tabview/assets/skins/sam/tabview.css"/>" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/javascript/yui/datatable/assets/skins/sam/datatable.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<s:url value="/css/main.css"/>"/>
		<link rel="shortcut icon" type="image/png" href="css/icon.png"> 

		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/yui/utilities/utilities.js"/>"></script>
		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/yui/yahoo-dom-event/yahoo-dom-event.js"/>"></script>
		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/yui/connection/connection-min.js"/>"></script>
		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/yui/element/element-beta-min.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/javascript/yui/button/button-min.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/javascript/yui/container/container-min.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/javascript/yui/paginator/paginator-min.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/javascript/yui/tabview/tabview-min.js"/>"></script>
		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/yui/datasource/datasource-min.js"/>"></script>
		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/yui/datatable/datatable-min.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/javascript/yui/tabview/dispatcher-min.js"/>"></script> 
		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/jsvalidate.js"/>"></script>
		<script type="text/javascript" language="javascript" src="<s:url value="/javascript/main.js"/>"></script>

		<s:head theme="ajax" debug="false"/>
	</head>
	
	<body class=" yui-skin-sam">
		<s:url id="enteteurl" value="/pages/entete.jsp" />
		<s:url id="menuurl" action="menu" />
		<s:url id="vueurl" action="vue" />
		
		<s:div id="entete" theme="ajax" href="%{enteteurl}"></s:div>
		<s:div id="menu" theme="ajax" href="%{menuurl}"></s:div>
		<s:div id="vue" cssClass="vuecss yui-navset" theme="ajax" href="%{vueurl}" listenTopics="montrer_vue" formId="frm_menu" executeScripts="true"></s:div>
		<div id="container_yui_confirm"></div>
	</body>
</html>