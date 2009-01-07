<%@ taglib prefix="s" uri="/struts-tags" %>
<ul>
<li><s:a href="#" onclick="javascript:show_droite('accueil');return false;">Accueil</s:a></li>
<li><s:a href="#" onclick="javascript:show_droite('compte');return false;">Mon Compte</s:a></li>
<li><s:a href="#" onclick="javascript:show_droite('maison');return false;">Ma Maison</s:a></li>
<li><s:a href="#" onclick="javascript:show_droite('profil');return false;">Mes Profils</s:a></li>
<li><s:a href="#" onclick="javascript:show_droite('aide');return false;">Aide</s:a></li>
<li><s:a href="#" onclick="javascript:show_droite('outil');return false;">Outils</s:a></li>
</ul>
<s:form id="frm_menu" name="frm_menu">
	<s:hidden name="selectedModule" id="selectedModule"/>
</s:form>