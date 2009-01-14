<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<s:actionerror />
<jsp:include page="/pages/button/detailBtn.jsp" />
<s:form id="frm_edition" name="frm_edition" theme="ajax" action="save" method="POST" validate="true">
	<jsp:include page="/pages/button/saveBtn.jsp" />
	
	<s:hidden name="selectedModule" value="%{selectedModule}"/>
	
	<s:iterator value="fieldToDisplay">
		<s:if test='top[0].equals("id")'>
			<s:hidden name="id" value="%{top[1]}"/>		
		</s:if>
		<s:elseif test='top[3].equals("text")||top[3].equals("object_maison")||top[3].equals("object_piece")'>
			<s:textfield cssClass="textfield" name="%{top[0]}" label="%{top[2]}" value="%{top[1]}" required="true"/>
		</s:elseif>
		<s:elseif test='top[3].equals("password")'>
			<s:password cssClass="textfield" name="%{top[0]}" label="%{top[2]}"	value="%{top[1]}"
			onchange="document.getElementById('passchange').value='true'" required="true" showPassword="true" />
			<s:hidden name="passchange" id="passchange" value="false" />
		</s:elseif>
		<s:elseif test='top[3].equals("select")'>
			<s:select cssClass="textfield" name="%{top[0]}" label="%{top[2]}" headerKey="%{top[1]}" list="#{'client':'Client', 'administrateur':'Admin'}"  />
		</s:elseif>
		<s:elseif test='top[3].equals("object_compte")'>
			<s:hidden name="%{top[0]}" value="%{top[1]}"/>
		</s:elseif>
		<s:elseif test='top[3].equals("bool")'>	
			<s:checkbox cssClass="checkbox" name="%{top[0]}_chk" label="%{top[2]}" fieldValue="%{top[1]}" value="%{top[1]}"
			onchange="if(document.getElementById('frm_edition_etat_chk').checked == true){document.getElementById('frm_edition_etat').value ='1'; }
						else{document.getElementById('frm_edition_etat').value ='0';}"/>
			<s:hidden name="%{top[0]}" value="%{top[1]}"/>
		</s:elseif>
	</s:iterator>


	<jsp:include page="/pages/button/saveBtn.jsp" />
</s:form>