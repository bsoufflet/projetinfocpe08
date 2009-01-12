<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<s:actionerror />
<jsp:include page="/pages/button/detailBtn.jsp" />
<s:form id="frm_edition" name="frm_edition" action="save" method="POST" validate="true">
	<jsp:include page="/pages/button/saveBtn.jsp" />
	<s:iterator value="#fieldToDisplay">
	    <s:property value="0" />
	    <s:property value="1" />
		<s:textfield cssClass="textfield" name="" id="" label=""/>
	</s:iterator>
	<jsp:include page="/pages/button/saveBtn.jsp" />
</s:form>