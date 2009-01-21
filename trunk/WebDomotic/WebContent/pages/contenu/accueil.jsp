<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />

<table>
  	<tr>
		<th><h2>Bonjour, bienvenue sur WebDomotic. <br />
			</h2>
			<h3>Ce site vous donne acc&egrave;s &agrave; tous les p&eacute;riph&eacute;riques de votre maison et vous permet de g&eacute;rer toute votre installation &agrave; distance tr&egrave;s facilement ! 
N&rsquo;h&eacute;sitez pas &agrave; naviguer dans les onglets pour profiter de toutes les opportunit&eacute;s que vous offre le site.</h3>
			<h2><b>Date de session: </b><%=new Date(session.getLastAccessedTime())%></h2>
		</th>
		<th class="maison"><h2><img src="<s:url value="css/maison.jpg"/>" height="330" width="500"></h2><br />
		</th>	
	</tr>
</table> 


