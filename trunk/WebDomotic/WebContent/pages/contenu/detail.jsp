<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<jsp:include page="/pages/button/editionBtn.jsp" />

<s:form id="frm_detail" name="frm_detail">
	<s:hidden name="selectedModule" value="%{selectedModule}"/>
	
	<s:iterator value="fieldToDisplay">
		<s:if test='top[0].equals("id")'>
			<s:hidden cssClass="textfield" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />		
		</s:if>
		<s:elseif test='top[0].equals("motdepasse")'>
			<s:password cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" showPassword="true" readonly="true"/>
		</s:elseif>
		<s:elseif test='top[0].equals("etat")'>
			<s:if test='top[1].equals("1")'>
				<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="Activé" />
			</s:if>
			<s:else>
				<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="Désactivé" />
			</s:else>
		</s:elseif>
		<s:elseif test='top[3].equals("object_compte")'>
			<s:if test='isAdmin.equals("true")'>
				<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />
			</s:if>
			<s:else>
				<s:hidden cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />
			</s:else>
		</s:elseif>
		<s:else>
			<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />
		</s:else>
		
	</s:iterator>
	
</s:form>