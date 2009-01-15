<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<s:if test="!isAdmin.equals('true')">

	<div class="list" onclick="webdomotic.montrer_vue('accueil', 'detail');return false;"><img src="css/petit_logo.jpg" height="20" width="20">Accueil</div>
	<div class="list" onclick="webdomotic.montrer_vue('compte', 'detail');return false;"><img src="css/petit_logo.jpg" height="20" width="20">Mon Compte</div>
	<div class="list" onclick="webdomotic.montrer_vue('maison', 'detail');return false;"><img src="css/petit_logo.jpg" height="20" width="20">Ma Maison</div>
	<div class="list" onclick="webdomotic.montrer_vue('profil', 'liste');return false;"><img src="css/petit_logo.jpg" height="20" width="20">Mes Profils</div>
	<div class="list" onclick="webdomotic.montrer_vue('aide', 'detail');return false;"><img src="css/petit_logo.jpg" height="20" width="20">Aide</div>
	<div class="list" onclick="webdomotic.montrer_vue('outil', 'detail');return false;"><img src="css/petit_logo.jpg" height="20" width="20">Outils</div>
	
</s:if>
<s:else>
	<div class="list" onclick="webdomotic.montrer_vue('accueil', 'detail');return false;">Accueil</div>
	<div class="list" onclick="webdomotic.montrer_vue('compte', 'liste');return false;">Comptes</div>
	<div class="list" onclick="webdomotic.montrer_vue('maison', 'liste');return false;">Maisons</div>
	<div class="list" onclick="webdomotic.montrer_vue('profil', 'liste');return false;">Profils</div>
	<div class="list" onclick="webdomotic.montrer_vue('outil', 'detail');return false;">Outils</div>
</s:else>

<s:form id="frm_menu" name="frm_menu">
	<s:hidden name="selectedModule" id="selectedModule"/>
	<s:hidden name="selectedAction" id="selectedAction"/>
	<s:hidden name="selectedId" id="selectedId"/>
</s:form>