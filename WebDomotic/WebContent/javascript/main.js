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
		montrer_tab: function(selectedModule, selectedAction){
			if(selectedModule != ""){
				document.getElementById('selectedModule').value = selectedModule;
			}
			if(selectedAction != ""){
				document.getElementById('selectedAction').value = selectedAction;
			}
			dojo.event.topic.publish("montrer_tab");
		},
		buildYUIDataTable: function(myColumnDefs,responseSchema,dataJSArray) {
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
			this.myDataTable = new YAHOO.widget.DataTable("ListeDiv", myColumnDefs, myDataSource, oConfigs);
			
			//init the confirm popup for delete
			this.confirmYUI();
			
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
						webdomotic.confirmYUIpopup.show();
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
		},
		confirmYUI: function(){
			var handleYes = function() {
				webdomotic.montrer_vue('', 'supprimer');
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
		createTabMaison: function(){
			var tabView = new YAHOO.widget.TabView("TabContainer");
			var handleClickProfilFct=function handleClick(e) {
				if(e.target.innerHTML == "Maison"){
					webdomotic.montrer_tab('maison','liste');
				}else if(e.target.innerHTML == "Piece"){
					webdomotic.montrer_tab('piece','liste');
				}else{
					webdomotic.montrer_tab('peripherique','liste');
				}
			}
			var tab0 = tabView.getTab(0);
			tab0.addListener('click', handleClickProfilFct);
			var tab1 = tabView.getTab(1);
			tab1.addListener('click', handleClickProfilFct);
			var tab2 = tabView.getTab(2);
			tab2.addListener('click', handleClickProfilFct);	
		},
		createTabProfil: function(){
			var tabView = new YAHOO.widget.TabView("TabContainer");
			var handleClickProfilFct=function handleClickProfil(e) {
				if(e.target.innerHTML == "Profil"){
					webdomotic.montrer_tab('profil','liste');
				}else{
					webdomotic.montrer_tab('regle','liste');
				}
			}
			var tab0 = tabView.getTab(0);
			tab0.addListener('click', handleClickProfilFct);
			var tab1 = tabView.getTab(1);
			tab1.addListener('click', handleClickProfilFct);
		}
	};
}
webdomotic = new WEBDOMOTIC();
