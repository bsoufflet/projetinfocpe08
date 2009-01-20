<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />

<s:if test='!selectedId.equals("0") && !selectedId.equals("")'>
	<jsp:include page="/pages/button/editionBtn.jsp" />
	<jsp:include page="/pages/button/supprimerBtn.jsp" />
</s:if>
<jsp:include page="/pages/button/annulerBtn.jsp" />

<s:form id="frm_detail_%{selectedModule}" cssClass="tableau" name="frm_detail">
	<s:hidden name="selectedModule" value="%{selectedModule}"/>
	
	<s:iterator value="fieldToDisplay">
		<s:if test='top[0].equals("id")'>
			<s:hidden cssClass="textfield" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />		
		</s:if>
		<s:elseif test='top[0].equals("motdepasse")'>
			<s:password cssClass="label" name="%{top[0]}" label="%{top[2]}" value="*****" showPassword="true" disabled="true"/>
		</s:elseif>
		<s:elseif test='top[0].equals("etat")'>
			<s:if test='top[1].equals("1")'>
				<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="Activé" />
			</s:if>
			<s:else>
				<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="Désactivé" />
			</s:else>
		</s:elseif>
		<s:elseif test='top[0].equals("periode")'>
			<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />
			<script>webdomotic.init_periode('frm_detail_<s:property value="selectedModule"/>', 'detail');</script>
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
<jsp:include page="/pages/contenu/edition.jsp" />

