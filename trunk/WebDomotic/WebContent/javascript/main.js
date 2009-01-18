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
					width: "450px",
					fixedcenter: true,
					visible: false,
					draggable: false,
					close: true,
					modal:true,
					text: "Etes vous sur de vouloir supprimer cet element?<BR> ATTENTION : Tous les elements associes seront egalement supprimes?",
					icon: YAHOO.widget.SimpleDialog.ICON_HELP,
					constraintoviewport: true,
					buttons: [ { text:"Oui", handler:handleYes, isDefault:true },{ text:"Non",  handler:handleNo } ]
				});
			this.confirmYUIpopup.setHeader("Suppression?");
			this.confirmYUIpopup.render('container_yui_confirm');
		},
		createYUIEditionForm: function(container,nouveau){
			if(typeof(this.editionDialog)!="undefined" && this.editionDialog.body != null){
				this.editionDialog.destroy();
			}
			//Define various event handlers for Dialog
			var handleSubmit = function() {
				this.submit();
			};
			var handleCancel = function() {
				this.cancel();
			};
			//Instantiate the Dialog
			this.editionDialog = new YAHOO.widget.Dialog(container, 
						{ width : "30em",
						  fixedcenter : true,
						  modal:true,
						  visible : false, 
						  constraintoviewport : true,
						  buttons : [ { text:"Submit", handler:handleSubmit, isDefault:true },
									  { text:"Cancel", handler:handleCancel } ]
						 } );
			var handleSuccess = function(o) {
				var response = o.responseText;
				webdomotic.editionDialog.hide();
				YAHOO.plugin.Dispatcher.process( 'vue', response );
			};
			
			var handleFailure = function(o) {
				alert("Submission failed: " + o.status);
			};
			
			this.editionDialog.callback = { success: handleSuccess,
														 failure: handleFailure };
			this.editionDialog.render(document.body);
			
			// Si c'est une creation on cache le formulaire de detail car il est vide 
			// et on montre directement le formulaire d'edition/creation
			if(nouveau){
				document.getElementById('frm_detail').style.display="none";
				this.editionDialog.show();
			}
		},
		createMaisonTabs: function(actionURL, selectedId){
			webdomotic.tabView = new YAHOO.widget.TabView({id: 'YUITab'});
			YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
		        label: 'Maisons',
		        dataSrc: actionURL+'?selectedModule=maison&selectedId='+selectedId,
		        active: (document.getElementById('selectedModule').value == 'maison')
		        
		    }), webdomotic.tabView);

			YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
		        label: 'Pieces',
		        dataSrc: actionURL+'?selectedModule=piece&selectedId='+selectedId,
		        active: (document.getElementById('selectedModule').value == 'piece')
		    }), webdomotic.tabView);
			//delegate permet d'executer le javascript de la reponse ajax
			YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
		        label: 'Peripheriques',
		        dataSrc: actionURL+'?selectedModule=peripherique&selectedId='+selectedId,
			    active: (document.getElementById('selectedModule').value == 'peripherique')
		    }), webdomotic.tabView);
			YAHOO.util.Event.onContentReady('TabContainer', function() {
				webdomotic.tabView.appendTo('TabContainer');
			});
			if(document.getElementById('selectedAction').value == "supprimer"){
				document.getElementById('selectedAction').value = "liste";
			}
			// Desactivation des Tabs si la vue n'est pas une liste
			if(document.getElementById('selectedAction').value != "liste"){
				webdomotic.tabView.get('tabs')[0].set('disabled', true);
				webdomotic.tabView.get('tabs')[1].set('disabled', true);
				webdomotic.tabView.get('tabs')[2].set('disabled', true);
			}
		},
		createProfilTabs: function(actionURL, selectedId){
			webdomotic.tabView = new YAHOO.widget.TabView({id: 'ProfilTab'});
			YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
		        label: 'Profil',
		        dataSrc: actionURL+'?selectedModule=profil&selectedId='+selectedId,
		        active: (document.getElementById('selectedModule').value == 'profil')
		    }), webdomotic.tabView);

			YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
		        label: 'Regle',
		        dataSrc: actionURL+'?selectedModule=regle&selectedId='+selectedId,
		        active: (document.getElementById('selectedModule').value == 'regle')
		    }), webdomotic.tabView);
		    
			YAHOO.util.Event.onContentReady('TabContainer', function() {
				webdomotic.tabView.appendTo('TabContainer');
			});
			if(document.getElementById('selectedAction').value == "supprimer"){
				document.getElementById('selectedAction').value = "liste";
			}
			if(document.getElementById('selectedAction').value != "liste"){
				webdomotic.tabView.get('tabs')[0].set('disabled', true);
				webdomotic.tabView.get('tabs')[1].set('disabled', true);
			}
		},
		init_edition: function(){
			if(document.getElementById('etat_chk').checked == true)
				document.getElementById('etat').value ='1';
			else
				document.getElementById('etat').value ='0';
		}
	};
}
webdomotic = new WEBDOMOTIC();
