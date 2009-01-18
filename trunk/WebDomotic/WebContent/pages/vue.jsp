<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<h1 class="h1"><s:property value="selectedModuleLabel"/></h1>
<s:if test='selectedAction.equals("supprimer")'>
	<s:action name="vue!supprimer"/>
	<s:set name="selectedAction" value="%{'liste'}"/>
</s:if>
<s:else>
	<s:set name="selectedAction" value="selectedAction"/>
</s:else>
<s:if test='selectedModule.equals("maison") || selectedModule.equals("piece") || selectedModule.equals("peripherique")'>
	<script>
		webdomotic.createMaisonTabs('<s:url action="%{#selectedAction}"></s:url>','<s:property value="selectedId"/>');
	</script>
	<div id="TabContainer"></div>
</s:if>
<s:elseif test='selectedModule.equals("profil") || selectedModule.equals("regle")'>
	<script>
		webdomotic.createProfilTabs('<s:url action="%{#selectedAction}"></s:url>','<s:property value="selectedId"/>');
	</script>
	<div id="TabContainer"></div>
</s:elseif>
<s:else>
	<s:action name="%{#selectedAction}" executeResult="true"/>
</s:else>
