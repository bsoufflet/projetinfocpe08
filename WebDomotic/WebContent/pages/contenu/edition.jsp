<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<s:actionerror />
<jsp:include page="/pages/button/detailBtn.jsp" />
<s:form id="frm_edition" name="frm_edition" action="save" method="POST" validate="true">
	<jsp:include page="/pages/button/saveBtn.jsp" />
	<s:iterator value="fieldToDisplay">
		<s:textfield cssClass="textfield" name="top[0]" id="top[0]" label="Test" value="top[1]" ></s:textfield>
	
	     <s:property value="top[0]" />
	     <s:property value="top[1]" />
	     
	     
	</s:iterator>
	
	
	<jsp:include page="/pages/button/saveBtn.jsp" />
</s:form>