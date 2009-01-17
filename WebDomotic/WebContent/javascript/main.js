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
		buildYUIDataTable: function(myColumnDefs,responseSchema,dataJSArray,selectedModule) {
			// Add the custom formatters to the shortcuts
			YAHOO.widget.DataTable.Formatter.customEtat = this.customEtatFormatter;
	        YAHOO.widget.DataTable.Formatter.customObjectURL = this.customObjectURLFormatter;
			var myDataSource = new YAHOO.util.DataSource(dataJSArray);
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
			myDataSource.connXhrMode = "queueRequests";
			myDataSource.responseSchema = responseSchema;
			var oConfigs = {
					paginator: new YAHOO.widget.Paginator({
					rowsPerPage: 10,
					template: YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
					rowsPerPageOptions: [5,10,20,50]
					})
	        };
			this.myDataTable = new YAHOO.widget.DataTable("ListeDiv_"+selectedModule, myColumnDefs, myDataSource, oConfigs);
			
			//init the confirm popup for delete
			this.confirmYUI(selectedModule);
			

		},
		/*
		 * Define a custom formatter for the Column labeled "flag"
		 * draws an up icon and adds class "up" to the cell to affect
		 * a green background color if value of statut is greater than 0.
		 * Otherwise renders a down icon and assigns class "down", setting
		 * the background color to red.
		 */
		customEtatFormatter: function(elLiner, oRecord, oColumn, oData) {
			if(oData == "1") {
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
		},
		confirmYUI: function(module){
			var handleYes = function() {
				webdomotic.montrer_vue(module, 'supprimer');
				this.hide();
			};
			var handleNo = function() {
				this.hide();
			};
			this.confirmYUIpopup = new YAHOO.widget.SimpleDialog("confirmYUI", 
				{
					width: "300px",
					fixedcenter: true,
					visible: false,
					draggable: false,
					close: true,
					text: "Etes vous sur de vouloir supprimer cet element?",
					icon: YAHOO.widget.SimpleDialog.ICON_HELP,
					constraintoviewport: true,
					buttons: [ { text:"Oui", handler:handleYes, isDefault:true },{ text:"Non",  handler:handleNo } ]
				});
			this.confirmYUIpopup.setHeader("Etes vous sur?");
			this.confirmYUIpopup.render('container_yui_confirm');
		},
		init_edition: function(){
			if(document.getElementById('frm_edition_etat_chk').checked == true)
				document.getElementById('frm_edition_etat').value ='1';
			else
				document.getElementById('frm_edition_etat').value ='0';
		}
	};
}
webdomotic = new WEBDOMOTIC();
