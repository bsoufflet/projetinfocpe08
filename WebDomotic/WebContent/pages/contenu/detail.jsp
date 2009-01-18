<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />

<s:if test='!selectedId.equals("0") && !selectedId.equals("")'>
	<jsp:include page="/pages/button/editionBtn.jsp" />
	<jsp:include page="/pages/button/supprimerBtn.jsp" />
</s:if>
<jsp:include page="/pages/button/annulerBtn.jsp" />

<s:form id="frm_detail" name="frm_detail">
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


<!-- Formulaire pour le popup de l'edition -->
<div id='EditFrmContainer_<s:property value="selectedModule"/>'> 
<div class="hd"></div> 
<div class="bd"> 
<form action="save.action" method="POST">
	<input type="hidden" name="selectedModule" value="<s:property value="selectedModule"/>"/>
	<input type="hidden" name="selectedId" value="<s:property value="selectedId"/>"/>
	<s:iterator value="fieldToDisplay">
		<s:if test='top[0].equals("id")'>
			<s:if test='top[1].equals("")'>
				<input type="hidden" name="id" value="0"/>
			</s:if>
			<s:else>
				<input type="hidden" name="id" value='<s:property value="top[1]"/>'/>
			</s:else>		
		</s:if>
		<s:elseif test='top[3].equals("text")||top[3].equals("object_maison")||top[3].equals("object_piece")'>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<input type="textbox" name='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>'/>
		</s:elseif>
		<s:elseif test='top[3].equals("password")'>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<input type="password" name='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' onchange="document.getElementById('passchange').value='true'"/>
			<input type="hidden" name="passchange" id="passchange" value="false" />
		</s:elseif>
		<s:elseif test='top[3].equals("select")'>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<select name='<s:property value="top[0]"/>'>
				<option value="client">Client</option>
				<option value="administrateur">Admin</option>
			</select>
		</s:elseif>
		<s:elseif test='top[3].equals("object_compte")'>
			<s:if test='isAdmin.equals("true")'>
				<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
				<input type="textbox" name='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' />
			</s:if>
			<s:else>
				<input type="hidden" name='<s:property value="top[0]"/>' value='<s:property value="userId"/>' />
			</s:else>
		</s:elseif>
		
		<s:elseif test='top[3].equals("bool")'>
			<div class="clear"></div>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<input type="checkbox" name='<s:property value="top[0]"/>_chk' id='<s:property value="top[0]"/>_chk' value='<s:property value="top[1]"/>'
			onchange="webdomotic.init_edition()" <s:if test='top[1].equals("1")'>CHECKED</s:if>/>
			<input type="hidden" name='<s:property value="top[0]"/>' id='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' />
		</s:elseif>
	</s:iterator>
</form>
</div>
</div>
<script>
//<s:if test='selectedId.equals("0")'>
	YAHOO.util.Event.onDOMReady(webdomotic.createYUIEditionForm("EditFrmContainer_<s:property value="selectedModule"/>",true));
//</s:if>
//<s:else>
	YAHOO.util.Event.onDOMReady(webdomotic.createYUIEditionForm("EditFrmContainer_<s:property value="selectedModule"/>",false));
//</s:else>
</script>