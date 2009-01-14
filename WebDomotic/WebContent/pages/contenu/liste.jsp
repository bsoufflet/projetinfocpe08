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
		{	
			key:'<s:property value="top[0]"/>' 
			,label:'<s:property value="top[1]"/>'
		    ,sortable:true
			//<s:if test='top[0].equals("name")'>
				
			//</s:if>
			//<s:elseif test='top[2] != null'>
				//<s:if test='top[2].equals("number")'>
					,formatter:"number"
				//</s:if>
				//<s:elseif test='top[2].equals("select")'>
					,formatter:"customEtat"
				//</s:elseif>
				//<s:elseif test='top[2].contains("object_")'>
					,formatter:"customObjectURL"
				//</s:elseif>
				//<s:else>
					
				//</s:else>
			//</s:elseif>
			//<s:else>
					
			//</s:else>
		},
	//</s:iterator>
		{
			key:"edit_button",
			label:"Edition",
			formatter:YAHOO.widget.DataTable.formatButton
		},
		{
			key:"delete_button",
			label:"Supprimer",
			formatter:YAHOO.widget.DataTable.formatButton
		}
	];
	var responseSchema={
		resultsList : "results",
		fields : [
			//<s:iterator value="columnDefs" status="cust_stat">
				{ key:'<s:property value="top[0]"/>' }
				//<s:if test="!#cust_stat.last">
				,
				//</s:if>
			//</s:iterator>
		]
	};
	webdomotic.buildYUIDataTable(myColumnDefs,responseSchema,dataJSArray);
</script>

<div id="ListeDiv"></div>