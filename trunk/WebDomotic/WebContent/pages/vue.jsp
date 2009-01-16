<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<h1><s:property value="selectedModuleLabel"/></h1>

<s:if test='selectedModule.equals("maison")'>
	<script>
		(function(){var tabView = new YAHOO.widget.TabView({id: 'MaisonTab'});
		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Maison',
	        dataSrc: '<s:url action="%{selectedAction}"></s:url>?selectedModule=maison&selectedId=<s:property value="selectedId"/>',
	        active: true
	    }), tabView);

		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Piece',
	        dataSrc: '<s:url action="%{selectedAction}"></s:url>?selectedModule=piece&selectedId=<s:property value="selectedId"/>'
	    }), tabView);

		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Peripherique',
	        dataSrc: '<s:url action="%{selectedAction}"></s:url>?selectedModule=peripherique&selectedId=<s:property value="selectedId"/>'
	    }), tabView);
		YAHOO.util.Event.onContentReady('TabContainer', function() {
			tabView.appendTo('TabContainer');
		});
		/*
		var handleClickFct=function handleClick(e) {
			document.getElementById("selectedAction").value="liste";
		};
		var tab0 = tabView.getTab(0);
		tab0.addListener('click', handleClickFct);
		var tab1 = tabView.getTab(1);
		tab1.addListener('click', handleClickFct);
		var tab2 = tabView.getTab(2);
		tab2.addListener('click', handleClickFct);*/
		})();
	</script>
	<div id="TabContainer"></div>
</s:if>
<s:elseif test='selectedModule.equals("profil")'>
	<script>
		(function(){var tabView = new YAHOO.widget.TabView({id: 'ProfilTab'});
		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Profil',
	        dataSrc: '<s:url action="%{selectedAction}"></s:url>?selectedModule=profil&selectedId=<s:property value="selectedId"/>',
	        active: true
	    }), tabView);

		YAHOO.plugin.Dispatcher.delegate(new YAHOO.widget.Tab({
	        label: 'Regle',
	        dataSrc: '<s:url action="%{selectedAction}"></s:url>?selectedModule=regle&selectedId=<s:property value="selectedId"/>'
	    }), tabView);
	    
		YAHOO.util.Event.onContentReady('TabContainer', function() {
			tabView.appendTo('TabContainer');
		});
		/*
		var handleClickFct=function handleClick(e) {
			document.getElementById("selectedAction").value="liste";
		};
		var tab0 = tabView.getTab(0);
		tab0.addListener('click', handleClickFct);
		var tab1 = tabView.getTab(1);
		tab1.addListener('click', handleClickFct);
		var tab2 = tabView.getTab(2);
		tab2.addListener('click', handleClickFct);*/
		})();
	</script>
	<div id="TabContainer"></div>
</s:elseif>
<s:else>
	<s:action name="%{selectedAction}" executeResult="true"/>
</s:else>
