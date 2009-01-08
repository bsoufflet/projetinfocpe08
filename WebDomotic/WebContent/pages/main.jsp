<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<html>
	<head>
		<title>Login Success</title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
		<s:head theme="ajax" debug="false"/>
	</head>
	<script>  
		function show_droite(selectedModule) {
			document.getElementById('selectedModule').value = selectedModule;
			
			dojo.event.topic.publish("show_droite");
		}
	</script>
	<body>
		<s:url id="enteteurl" value="/pages/entete.jsp" />
		<s:url id="menuurl" value="/pages/menu.jsp" />
		<s:url id="droiteurl" action="droiteAction" />
		
		<s:div id="entete" theme="ajax" href="%{enteteurl}"></s:div>
		<s:div id="menu" theme="ajax" href="%{menuurl}"></s:div>
		<s:div id="droite" theme="ajax" href="%{droiteurl}" listenTopics="show_droite" formId="frm_menu"></s:div>
	</body>
</html>