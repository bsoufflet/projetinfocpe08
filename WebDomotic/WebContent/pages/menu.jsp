<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<div class="list" onclick="javascript:montrer_vue('accueil', 'liste');return false;">Accueil</div>
<div class="list" onclick="javascript:montrer_vue('compte', 'liste');return false;">Mon Compte</div>
<div class="list" onclick="javascript:montrer_vue('maison', 'liste');return false;">Ma Maison</div>
<div class="list" onclick="javascript:montrer_vue('profil', 'liste');return false;">Mes Profils</div>
<div class="list" onclick="javascript:montrer_vue('aide', 'liste');return false;">Aide</div>
<div class="list" onclick="javascript:montrer_vue('outil', 'liste');return false;">Outils</div>
<s:form id="frm_menu" name="frm_menu">
	<s:hidden name="selectedModule" id="selectedModule"/>
	<s:hidden name="selectedAction" id="selectedAction"/>
</s:form>