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
		{	
			key:'<s:property value="top[0]"/>' 
			,label:'<s:property value="top[1]"/>'
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
	//</s:iterator>
		{
			key:"detail_button",
			label:"",
			formatter:YAHOO.widget.DataTable.formatButton
		},
		{
			key:"edit_button",
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
			{ key:'edit_button' },
			{ key:'delete_button' }
		]
	};
	webdomotic.buildYUIDataTable(myColumnDefs,responseSchema,dataJSArray);
</script>

<div id="ListeDiv"></div>