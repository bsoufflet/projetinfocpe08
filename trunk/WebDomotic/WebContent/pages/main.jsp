<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<html>
	<head>
		<title>Login Success</title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
		<s:head theme="ajax" debug="false"/>
	</head>
	<script>  
		function montrer_vue(selectedModule, selectedAction) {
			if(selectedModule != ""){
				document.getElementById('selectedModule').value = selectedModule;
			}
			if(selectedAction != ""){
				document.getElementById('selectedAction').value = selectedAction;
			}
			dojo.event.topic.publish("montrer_vue");
		}
	</script>
	<body>
		<s:url id="enteteurl" value="/pages/entete.jsp" />
		<s:url id="menuurl" value="/pages/menu.jsp" />
		<s:url id="vueurl" action="vue" />
		
		<s:div id="entete" theme="ajax" href="%{enteteurl}"></s:div>
		<s:div id="menu" theme="ajax" href="%{menuurl}"></s:div>
		<s:div id="vue" theme="ajax" href="%{vueurl}" listenTopics="montrer_vue" formId="frm_menu"></s:div>
	</body>
</html>