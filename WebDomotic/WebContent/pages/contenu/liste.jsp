<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<script>
	//<s:if test='dataJSArray.equals("")'>
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
				//<s:elseif test='top[2].equals("periode")'>
					,formatter:"customPeriode"
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
			key:"detail_button",
			label:"",
			formatter:YAHOO.widget.DataTable.formatButton
		},
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
			{ key:'detail_button' },
			{ key:'delete_button' }
		]
	};
	webdomotic.buildYUIDataTable(myColumnDefs,responseSchema,dataJSArray,'<s:property value="selectedModule"/>');
	//init the confirm popup for delete
	webdomotic.confirmYUI('<s:property value="selectedModule"/>');
	webdomotic.myDataTable.subscribe("buttonClickEvent", 
			function(oArgs){
				var button = oArgs.target.innerHTML;
				var oRecord = this.getRecord(oArgs.target);
				if(typeof(oRecord._oData.id)!="undefined" && oRecord._oData.id != ""){
					document.getElementById('selectedId').value = oRecord._oData.id;
					if(button == "Detail"){
						webdomotic.montrer_vue('<s:property value="selectedModule"/>', 'detail');
					}else if(button == "Supprimer"){
						webdomotic.confirmYUIpopup.show();
					}else{
						document.getElementById('selectedId').value = "";
						alert('main.js-buildYUIDataTable : le nom du bouton est inconnu!!');
					}
				}else{
					alert('main.js-buildYUIDataTable : Pas d ID disponible!!');
				}
			});
</script>
<input type="hidden" id="fillobjectnameURL" value='<s:url action="fillobjectname"></s:url>'/>
<input type="button" class="button" name="nouveau_button" id="nouveau_button" value="Nouveau" onclick="document.getElementById('selectedId').value='0';webdomotic.montrer_vue('<s:property value="selectedModule"/>', 'detail');return false;"/><br><br>
<div id="ListeDiv_<s:property value="selectedModule"/>" class="yui-content"></div>