<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<h1 class="h1"><s:property value="selectedModuleLabel"/></h1>
<s:if test='selectedAction.equals("supprimer") && actionType.equals("relationship")'>
	<s:action name="vue!supprimer"/>
	<s:set name="selectedAction" value="%{'detail'}"/>
</s:if>
<s:elseif test='selectedAction.equals("supprimer")'>
	<s:action name="vue!supprimer"/>
	<s:set name="selectedAction" value="%{'liste'}"/>
</s:elseif>
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
