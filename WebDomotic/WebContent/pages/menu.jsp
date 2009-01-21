<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<s:if test="!isAdmin.equals('true')">

	<div class="list" onclick="webdomotic.montrer_vue('accueil', 'accueil');return false;">Accueil</div>
	<div class="list" onclick="webdomotic.montrer_vue('compte', 'detail');return false;">Mon Compte</div>
	<div class="list" onclick="webdomotic.montrer_vue('maison', 'liste');return false;">Ma Maison</div>
	<div class="list" onclick="webdomotic.montrer_vue('profil', 'liste');return false;">Mes Profils</div>
	<div class="list" onclick="webdomotic.montrer_vue('console', 'liste');return false;">Ma Console</div>
	<div class="list" onclick="webdomotic.montrer_vue('aide', 'detail');return false;">Aide</div>
	<div class="list" onclick="webdomotic.montrer_vue('outil', 'detail');return false;">Outils</div>
	
</s:if>
<s:else>
	<div class="list" onclick="webdomotic.montrer_vue('accueil', 'accueil');return false;">Accueil</div>
	<div class="list" onclick="webdomotic.montrer_vue('compte', 'liste');return false;">Comptes</div>
	<div class="list" onclick="webdomotic.montrer_vue('maison', 'liste');return false;">Maisons</div>
	<div class="list" onclick="webdomotic.montrer_vue('profil', 'liste');return false;">Profils</div>
	<div class="list" onclick="webdomotic.montrer_vue('console', 'liste');return false;">Consoles</div>
	<div class="list" onclick="webdomotic.montrer_vue('outil', 'detail');return false;">Outils</div>
</s:else>

<s:form id="frm_menu" name="frm_menu">
	<s:hidden name="selectedModule" id="selectedModule"/>
	<s:hidden name="selectedAction" id="selectedAction"/>
	<s:hidden name="selectedId" id="selectedId"/>
	<s:hidden name="lastId" id="lastId"/>
	<s:hidden name="actionType" id="actionType"/>
</s:form>