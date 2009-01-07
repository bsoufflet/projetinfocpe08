<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="list" onclick="javascript:show_droite('accueil');return false;">Accueil</div>
<div class="list" onclick="javascript:show_droite('compte');return false;">Mon Compte</div>
<div class="list" onclick="javascript:show_droite('maison');return false;">Ma Maison</div>
<div class="list" onclick="javascript:show_droite('profil');return false;">Mes Profils</div>
<div class="list" onclick="javascript:show_droite('aide');return false;">Aide</div>
<div class="list" onclick="javascript:show_droite('outil');return false;">Outils</div>
<s:form id="frm_menu" name="frm_menu">
	<s:hidden name="selectedModule" id="selectedModule"/>
</s:form>