<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Login</title>
<link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
	<s:form action="login" method="POST"  validate="true">
		<tr>
			<td colspan="2">
			Web Domotic
			</td>
		</tr>
			<tr>
				<td colspan="2">
				<s:actionerror />
				<s:fielderror />
			</td>
		  </tr>
		<s:textfield cssClass="textfield" name="username" label="Nom d'utilisateur"/>
		<s:password cssClass="ctextfield" name="password" label="Mot de passe"/>
		<s:submit cssClass="button" value="Login" align="center"/>
	</s:form>
</body>
</html>