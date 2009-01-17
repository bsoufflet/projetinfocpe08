<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<input class="button" name="supprimer_button" id="supprimer_button" type="button" value="Supprimer" onclick="webdomotic.confirmYUIpopup.show();return false;"/>
<script>
	webdomotic.confirmYUI('<s:property value="selectedModule"/>');
</script>