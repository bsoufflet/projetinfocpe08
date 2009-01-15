<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<h1><s:property value="selectedModuleLabel"/></h1>

<s:if test='selectedModule.equals("maison")'>
	<script>
		webdomotic.createTabMaison();
	</script>
	<s:url id="listeurl" action="liste" />
	<div id="TabContainer" class="yui-navset">
	    <ul class="yui-nav">
	        <li class="selected"><a href=""><em>Maison</em></a></li>
	        <li><a href=""><em>Piece</em></a></li>
	        <li><a href=""><em>Peripherique</em></a></li>
	    </ul>            
		<s:div cssClass="yui-content" id="montrer_tab_div" theme="ajax" href="%{listeurl}" listenTopics="montrer_tab" formId="frm_menu" executeScripts="true"></s:div>
	</div>
</s:if>
<s:elseif test='selectedModule.equals("profil")'>
	<script>
		webdomotic.createTabProfil();
	</script>
	<s:url id="listeurl" action="liste" />
	<div id="TabContainer" class="yui-navset">
	    <ul class="yui-nav">
	        <li class="selected"><a href=""><em>Profil</em></a></li>
	        <li><a href=""><em>Regle</em></a></li>
	    </ul>
		<s:div cssClass="yui-content" id="montrer_tab_div" theme="ajax" href="%{listeurl}" listenTopics="montrer_tab" formId="frm_menu" executeScripts="true"></s:div>
	</div>
</s:elseif>
<s:else>
	<s:action name="%{selectedAction}" executeResult="true"/>
</s:else>
