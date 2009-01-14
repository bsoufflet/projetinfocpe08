<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<input type="button" class="button" name="nouveau_button" id="nouveau_button" value="Nouveau" onclick="document.getElementById('selectedId').value='';webdomotic.montrer_vue('', 'edition');return false;"/>