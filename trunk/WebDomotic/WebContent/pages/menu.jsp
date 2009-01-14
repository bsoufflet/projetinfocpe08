<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<div class="list" onclick="webdomotic.montrer_vue('accueil', 'detail');return false;">Accueil</div>
<div class="list" onclick="webdomotic.montrer_vue('compte', 'detail');return false;">Mon Compte</div>
<div class="list" onclick="webdomotic.montrer_vue('maison', 'liste');return false;">Ma Maison</div>
<div class="list" onclick="webdomotic.montrer_vue('profil', 'liste');return false;">Mes Profils</div>
<div class="list" onclick="webdomotic.montrer_vue('aide', 'detail');return false;">Aide</div>
<div class="list" onclick="webdomotic.montrer_vue('outil', 'detail');return false;">Outils</div>
<s:form id="frm_menu" name="frm_menu">
	<s:hidden name="selectedModule" id="selectedModule"/>
	<s:hidden name="selectedAction" id="selectedAction"/>
	<s:hidden name="selectedId" id="selectedId"/>
</s:form>