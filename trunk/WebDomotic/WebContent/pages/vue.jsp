<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<h1 class="h1"><s:property value="selectedModuleLabel"/></h1>
<s:if test='selectedModule.equals("maison") || selectedModule.equals("piece") || selectedModule.equals("peripherique")'>
	<script>
		(function(){webdomotic.tabView = new YAHOO.widget.TabView({id: 'MaisonTab'});
		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Maisons',
	        dataSrc: '<s:url action="%{selectedAction}"/>?selectedModule=maison&selectedId=<s:property value="selectedId"/>',
	        active: (document.getElementById('selectedModule').value == 'maison')
	        
	    }), webdomotic.tabView);

		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Pieces',
	        dataSrc: '<s:url action="%{selectedAction}"/>?selectedModule=piece&selectedId=<s:property value="selectedId"/>',
	        active: (document.getElementById('selectedModule').value == 'piece')
	    }), webdomotic.tabView);
		//delegate permet d'executer le javascript de la reponse ajax
		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Peripheriques',
	        dataSrc: '<s:url action="%{selectedAction}"/>?selectedModule=peripherique&selectedId=<s:property value="selectedId"/>',
		    active: (document.getElementById('selectedModule').value == 'peripherique')
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
			webdomotic.tabView.get('tabs')[2].set('disabled', true);
		}
		})();
	</script>
	<div id="TabContainer"></div>
</s:if>
<s:elseif test='selectedModule.equals("profil") || selectedModule.equals("regle")'>
	<script>
		(function(){webdomotic.tabView = new YAHOO.widget.TabView({id: 'ProfilTab'});
		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Profil',
	        dataSrc: '<s:url action="%{selectedAction}"></s:url>?selectedModule=profil&selectedId=<s:property value="selectedId"/>',
	        active: (document.getElementById('selectedModule').value == 'profil')
	    }), webdomotic.tabView);

		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Regle',
	        dataSrc: '<s:url action="%{selectedAction}"></s:url>?selectedModule=regle&selectedId=<s:property value="selectedId"/>',
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
		})();
	</script>
	<div id="TabContainer"></div>
</s:elseif>
<s:else>
	<s:action name="%{selectedAction}" executeResult="true"/>
</s:else>
