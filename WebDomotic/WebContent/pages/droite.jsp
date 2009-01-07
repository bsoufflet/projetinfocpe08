<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url id="errorurl" value="/pages/error.jsp" />
<s:url id="accueilurl" value="/pages/contenu/accueil.jsp" />
<s:url id="compteurl" value="/pages/contenu/compte.jsp" />
<s:url id="maisonurl" value="/pages/contenu/maison.jsp" />
<s:url id="profilurl" value="/pages/contenu/profil.jsp" />
<s:url id="aideurl" value="/pages/contenu/aide.jsp" />
<s:url id="outilurl" value="/pages/contenu/outil.jsp" />

<s:if test="selectedModule=='accueil'">
	<s:div id="contenu" theme="ajax" href="%{accueilurl}"></s:div>
</s:if>
<s:elseif test="selectedModule=='compte'">
	<s:div id="contenu" theme="ajax" href="%{compteurl}"></s:div>
</s:elseif>
<s:elseif test="selectedModule=='maison'">
	<s:div id="contenu" theme="ajax" href="%{maisonurl}"></s:div><s:property value="selectedModule"/>
</s:elseif>
<s:elseif test="selectedModule=='profil'">
	<s:div id="contenu" theme="ajax" href="%{profilurl}"></s:div>
</s:elseif>
<s:elseif test="selectedModule=='aide'">
	<s:div id="contenu" theme="ajax" href="%{aideurl}"></s:div>
</s:elseif>
<s:elseif test="selectedModule=='outil'">
	<s:div id="contenu" theme="ajax" href="%{outilurl}"></s:div>
</s:elseif>
<s:else>
	<s:div id="contenu" theme="ajax" href="%{errorurl}"></s:div>
</s:else>