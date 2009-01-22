<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<br><br>
<input type="button" value="Tester un peripherique" onclick="webdomotic.outilTestDialog.show()"/>
<br><br>
<input type="button" value="Synchroniser la console" onclick="webdomotic.wait.show()"/>
<br><br>
<input type="button" value="Reinitialiser la console" onclick="webdomotic.wait.show()"/>
<!-- Formulaire pour le popup de l'edition -->
<div id='outilTestContainer'> 
<div class="hd"></div> 
<div class="bd"> 
<form action="testPeripherique.action" method="POST" id='outilTest_frm'>
	<label for='identifiant'>Identifiant:</label>
	<input type="textbox" name="identifiant" value="A01" size="3" maxlength="3"/>
	<div class="clear"></div>
	<label for="ordre">Ordre:</label>
	<input type="radio" name="ordre" value="1" checked/> On
	<input type="radio" name="ordre" value="0" /> Off
</form>
</div>
</div>
<div id="wait"></div>
<script>
	if(typeof(webdomotic.outilTestDialog)!="undefined" && webdomotic.outilTestDialog.body != null){
		webdomotic.outilTestDialog.destroy();
	}
	if(typeof(webdomotic.wait)!="undefined" && webdomotic.wait.body != null){
		webdomotic.wait.destroy();
	}
	//Define various event handlers for Dialog
	var handleSubmit = function() {
		webdomotic.outilTestDialog.hide();
		webdomotic.wait.show();
		this.submit();
	};
	var handleCancel = function() {
		this.cancel();
	};
	//Instantiate the Dialog
	webdomotic.outilTestDialog = new YAHOO.widget.Dialog("outilTestContainer", 
				{ width : "30em",
				  fixedcenter : true,
				  modal:true,
				  visible : false, 
				  constraintoviewport : true,
				  buttons : [ { text:"Tester", handler:handleSubmit, isDefault:true },
							  { text:"Annuler", handler:handleCancel } ]
				 } );
	
	var handleSuccess = function(o) {
		setTimeout("webdomotic.wait.hide();",1000);
	};
	
	var handleFailure = function(o) {
		webdomotic.wait.hide();
		webdomotic.outilTestDialog.show();
		alert("Erreur de l'envoi de la commande: " + o.status);
	};
	
	webdomotic.outilTestDialog.callback = { success: handleSuccess,failure: handleFailure };
	webdomotic.outilTestDialog.render(document.body);



	// WAITING Panel
	webdomotic.wait = new YAHOO.widget.Panel("wait",  
											{ width:"240px", 
											  fixedcenter:true, 
											  close:true, 
											  draggable:false, 
											  modal:true,
											  visible:false,
											  effect:{effect:YAHOO.widget.ContainerEffect.FADE, duration:0.5} 
											} 
										);

	webdomotic.wait.setHeader("Envoi de la commande...");
	webdomotic.wait.setBody('<img src="http://us.i1.yimg.com/us.yimg.com/i/us/per/gr/gp/rel_interstitial_loading.gif" />');
	webdomotic.wait.render(document.body);
	
</script>
