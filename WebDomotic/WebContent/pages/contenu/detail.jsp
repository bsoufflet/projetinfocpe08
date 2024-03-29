<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<s:elseif test='top[3].equals("object_compte")'>
			<s:if test='isAdmin.equals("true")'>
				<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />
			<script>
			var initObj=new Object();
			initObj.action='<s:url action="fillobjectname"></s:url>';
			initObj.data='module=<s:property value="top[0]"/>&id=<s:property value="top[1]"/>&selectedAction=detail';
			initObj.container='frm_detail_<s:property value="selectedModule"/>_<s:property value="top[0]"/>';
			YAHOO.util.Event.onContentReady('frm_detail_<s:property value="selectedModule"/>',webdomotic.fill_object_names, initObj);
			</script>
			</s:if>
			<s:else>
				<s:hidden cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />
			</s:else>
		</s:elseif>
		<s:elseif test='top[3].equals("password")'>
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
			<script>
			var initObj=new Object();
			initObj.formId='frm_detail_<s:property value="selectedModule"/>';
			initObj.context='detail';
			YAHOO.util.Event.onContentReady('frm_detail_<s:property value="selectedModule"/>',webdomotic.init_periode, initObj);
			</script>
		</s:elseif>
		<s:elseif test='top[3].equals("object_maison")||top[3].equals("object_piece")'>
			<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />
			<script>
			var initObj=new Object();
			initObj.action='<s:url action="fillobjectname"></s:url>';
			initObj.data='module=<s:property value="top[0]"/>&id=<s:property value="top[1]"/>&selectedAction=detail';
			initObj.container='frm_detail_<s:property value="selectedModule"/>_<s:property value="top[0]"/>';
			YAHOO.util.Event.onContentReady('frm_detail_<s:property value="selectedModule"/>',webdomotic.fill_object_names, initObj);
			</script>
		</s:elseif>
		<s:else>
			<s:label cssClass="label" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" />
		</s:else>
		
	</s:iterator>
	
</s:form>
<s:if test='selectedModule.equals("regle")'>
	<jsp:include page="/pages/contenu/panelDetail.jsp" />
</s:if>
<jsp:include page="/pages/contenu/edition.jsp" />

