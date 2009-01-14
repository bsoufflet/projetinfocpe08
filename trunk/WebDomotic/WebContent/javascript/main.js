if (typeof(WEBDOMOTIC) == "undefined") {
	WEBDOMOTIC = function(){
	};
	WEBDOMOTIC.prototype = {
		/*
		 * Affiche la vue correspondant au bon module dans la partie droite.
		 */
		montrer_vue: function(selectedModule, selectedAction){
			if(selectedModule != ""){
				document.getElementById('selectedModule').value = selectedModule;
			}
			if(selectedAction != ""){
				document.getElementById('selectedAction').value = selectedAction;
			}
			dojo.event.topic.publish("montrer_vue");
		},
		buildYUIDataTable: function(myColumnDefs,responseSchema,dataJSArray) {
			// Add the custom formatters to the shortcuts
			YAHOO.widget.DataTable.Formatter.customEtat = this.customEtatFormatter;
	        YAHOO.widget.DataTable.Formatter.customObjectURL = this.customObjectURLFormatter;
			var myDataSource = new YAHOO.util.DataSource(dataJSArray);
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
			myDataSource.connXhrMode = "queueRequests";
			myDataSource.responseSchema = responseSchema;
			this.myDataTable = new YAHOO.widget.DataTable("ListeDiv", myColumnDefs, myDataSource);
			
			this.myDataTable.subscribe("buttonClickEvent", 
			function(oArgs){
				var button = oArgs.target.innerHTML;
				var oRecord = this.getRecord(oArgs.target);
				if(typeof(oRecord._oData.id)!="undefined" && oRecord._oData.id != ""){
					document.getElementById('selectedId').value = oRecord._oData.id;
					if(button == "Detail"){
						webdomotic.montrer_vue('', 'detail');
					}else if(button == "Edition"){
						webdomotic.montrer_vue('', 'edition');
					}else if(button == "Supprimer"){
						webdomotic.montrer_vue('', 'supprimer');
					}else{
						document.getElementById('selectedId').value = "";
						alert('main.js-buildYUIDataTable : le nom du bouton est inconnu!!');
					}
				}else{
					alert('main.js-buildYUIDataTable : Pas d ID disponible!!');
				}
			});
		},
		/*
		 * Define a custom formatter for the Column labeled "flag"
		 * draws an up icon and adds class "up" to the cell to affect
		 * a green background color if value of statut is greater than 0.
		 * Otherwise renders a down icon and assigns class "down", setting
		 * the background color to red.
		 */
		customEtatFormatter: function(elLiner, oRecord, oColumn, oData) {
			if(oRecord.getData("statut") == "1") {
				YAHOO.util.Dom.replaceClass(elLiner.parentNode, "down", "up");
				elLiner.innerHTML = 'ON';
			}
			else {
				YAHOO.util.Dom.replaceClass(elLiner.parentNode, "up","down");
				elLiner.innerHTML = 'OFF';
			}
		},
		customObjectURLFormatter: function(elLiner, oRecord, oColumn, oData){
        	var id = oData;
        	elLiner.innerHTML = "<a href=\"javascript:document.getElementById('selectedId').value = '"+id+"';webdomotic.montrer_vue('"+oColumn.extra.replace('object_','')+"', 'detail');\">" + id + "</a>";
		}
	};
}
webdomotic = new WEBDOMOTIC();