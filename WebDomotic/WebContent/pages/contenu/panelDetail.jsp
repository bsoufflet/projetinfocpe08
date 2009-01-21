<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<script>
	//<s:if test='dataJSArray == null || dataJSArray.equals("")'>
		var dataJSArray = new Array();
	//</s:if>
	//<s:else>
		var dataJSArray = <s:property value="dataJSArray"/>;
	//</s:else>
	var myColumnDefs = [
	//<s:iterator value="columnDefs" status="cust_stat">
		//<s:if test='(top[3] != null && !top[3].equals("false")) || top[3] == null'>
		//<s:if test='!top[0].equals("motdepasse")'>
		{	
			key:'<s:property value="top[0]"/>' 
			,label:"<s:property value="top[1]"/>"
		    ,sortable:true
			//<s:if test='top[2] != null'>
				//<s:if test='top[2].equals("number")'>
					,formatter:"number"
				//</s:if>
				//<s:elseif test='top[2].equals("bool")'>
					,formatter:"customEtat"
				//</s:elseif>
				//<s:elseif test='top[2].contains("object_")'>
					,formatter:"customObjectURL"
					,extra:'<s:property value="top[2]"/>'
				//</s:elseif>
				//<s:else>
				//</s:else>
			//</s:if>
			//<s:else>
			//</s:else>
		},
		//</s:if>
		//</s:if>
	//</s:iterator>
		{
			key:"delete_button",
			label:"",
			formatter:YAHOO.widget.DataTable.formatButton
		}
	];
	var responseSchema={
		resultsList : "results",
		fields : [
			//<s:iterator value="columnDefs" status="cust_stat">
				{ key:'<s:property value="top[0]"/>' },
			//</s:iterator>
			{ key:'delete_button' }
		]
	};
	webdomotic.buildYUIDataTable(myColumnDefs,responseSchema,dataJSArray,'<s:property value="selectedModule"/>');
	//init the confirm popup for delete
	webdomotic.confirmYUIPanel('<s:property value="selectedModule"/>');
	
	webdomotic.myDataTable.subscribe("buttonClickEvent", 
			function(oArgs){
				var button = oArgs.target.innerHTML;
				var oRecord = this.getRecord(oArgs.target);
				if(typeof(oRecord._oData.id)!="undefined" && oRecord._oData.id != ""){
					document.getElementById('lastId').value = oRecord._oData.id;
					if(button == "Supprimer"){
						webdomotic.confirmYUIPanelpopup.show();
					}else{
						document.getElementById('selectedId').value = "";
						alert('main.js-buildYUIDataTable : le nom du bouton est inconnu!!');
					}
				}else{
					alert('main.js-buildYUIDataTable : Pas d ID disponible!!');
				}
			});
</script>

<br>
<input type="button" class="button" name="ajouter_button" id="ajouter_button" value="Ajouter" onclick="webdomotic.panelDialog.show();"/>
<div id="ListeDiv_<s:property value="selectedModule"/>" class="yui-content"></div>
<div id="container_yui_confirm_panel"></div>

<!-- Formulaire pour le popup de l'ajout d'un lien n-n -->
<div id='PanelFrmContainer_<s:property value="selectedModule"/>'> 
<div class="hd"></div> 
<div class="bd"> 
<form action="addRelationship.action" method="POST" id='frm_panel_<s:property value="selectedModule"/>'>
	<input type="hidden" name="selectedModule" value="<s:property value="selectedModule"/>"/>
	<input type="hidden" name="selectedId" value="<s:property value="selectedId"/>"/>
	<input type="hidden" name="relationship_name" value="regles_peripheriques"/>
	<input type="hidden" name="regle_id" value="<s:property value="selectedId"/>"/>
	<label for='peripherique_id'>Peripherique:</label>
	<select name='peripherique_id'>
	<s:iterator value="resultPeriph">
		<option value="<s:property value="top[0]"/>"><s:property value="top[1]"/> - <s:property value="top[2]"/></option>
	</s:iterator>
	</select>
</form>
</div>
</div>
<script>
	YAHOO.util.Event.onDOMReady(webdomotic.createYUIPanelForm('PanelFrmContainer_<s:property value="selectedModule"/>','<s:property value="selectedModule"/>'));
</script>