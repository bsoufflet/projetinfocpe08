<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<jsp:include page="/pages/button/editionBtn.jsp" />

<s:form id="frm_detail" name="frm_detail">
	<s:hidden name="selectedModule" value="%{selectedModule}"/>
	
	<s:iterator value="fieldToDisplay">
		<s:if test='top[0].equals("id")'>
			<s:hidden cssClass="textfield" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />		
		</s:if>
		<s:else>
			<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}"></s:label>
		</s:else>
		
	</s:iterator>
	
</s:form>