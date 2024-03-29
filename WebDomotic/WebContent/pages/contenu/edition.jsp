<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<!-- Formulaire pour le popup de l'edition -->
<div id='EditFrmContainer_<s:property value="selectedModule"/>'> 
<div class="hd"></div> 
<div class="bd"> 
<form action="save.action" method="POST" id='frm_edition_<s:property value="selectedModule"/>'>
	<input type="hidden" name="selectedModule" value="<s:property value="selectedModule"/>"/>
	<input type="hidden" name="selectedId" value="<s:property value="selectedId"/>"/>
	<s:iterator value="fieldToDisplay">
		<s:if test='top[0].equals("id")'>
			<s:if test='top[1].equals("")'>
				<input type="hidden" name="id" value="0"/>
			</s:if>
			<s:else>
				<input type="hidden" name="id" value='<s:property value="top[1]"/>' class="required validate-number"/>
			</s:else>		
		</s:if>
		<s:elseif test='top[3].equals("object_compte")'>
			<s:if test='isAdmin.equals("true")'>
				<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
				<div id='fill_object_<s:property value="selectedModule"/>_<s:property value="top[0]"/>'></div> 
				<script>
				var initObj=new Object();
				initObj.action='<s:url action="fillobjectname"></s:url>';
				initObj.data='module=<s:property value="top[0]"/>&id=<s:property value="top[1]"/>';
				initObj.container='fill_object_<s:property value="selectedModule"/>_<s:property value="top[0]"/>';
				YAHOO.util.Event.onContentReady('frm_detail_<s:property value="selectedModule"/>',webdomotic.fill_object_names, initObj);
				</script>
			</s:if>
			<s:else>
				<input type="hidden" name='<s:property value="top[0]"/>' value='<s:property value="userId"/>' class="required validate-number"/>
			</s:else>
		</s:elseif>
		<s:elseif test='top[3].equals("object_maison")||top[3].equals("object_piece")'>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<div id='fill_object_<s:property value="selectedModule"/>_<s:property value="top[0]"/>'></div> 
			<script>
			var initObj=new Object();
			initObj.action='<s:url action="fillobjectname"></s:url>';
			initObj.data='module=<s:property value="top[0]"/>&id=<s:property value="top[1]"/>';
			initObj.container='fill_object_<s:property value="selectedModule"/>_<s:property value="top[0]"/>';
			YAHOO.util.Event.onContentReady('frm_detail_<s:property value="selectedModule"/>',webdomotic.fill_object_names, initObj);
			</script>
		</s:elseif>
		<s:elseif test='top[3].equals("periode")'>
			<input type="hidden" name='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' class="required"/>
			<div class="clear"></div>
			<label for="jours_periode">Jours:</label>
			<input type="checkbox" name="jours_periode" value="2" /> Lu
			<input type="checkbox" name="jours_periode" value="3" /> Ma
			<input type="checkbox" name="jours_periode" value="4" /> Me
			<input type="checkbox" name="jours_periode" value="5" /> Je
			<div class="clear"></div>
			<label for="jours_periode" style="visibility:hidden">:</label>
			<input type="checkbox" name="jours_periode" value="6" /> Ve
			<input type="checkbox" name="jours_periode" value="7" /> Sa
			<input type="checkbox" name="jours_periode" value="1" /> Di
			<div class="clear"></div>
			<label for="heure_periode">Heure de debut:</label>
			<input type="textbox" name='heure_periode' class='validate-number' value='' size='2' maxlength='2' class="required"/> Heures 
			<input type="textbox" name='minute_periode' class='validate-number' value='' size='2' maxlength='2' class="required"/> Minutes
			<div class="clear"></div>
			<label for="duree_periode">Duree: (0:illimite)</label>
			<input type="textbox" name='duree_periode' class='validate-number' value='' size='4' maxlength='4'/> Minutes
			<div class="clear"></div>
			<label for="repetition_periode">Repetition toutes les:</label>
			<input type="textbox" name='repetition_periode' class='validate-number' value='' size='4' maxlength='4'/> Minutes
			<script>
			var initEdObj=new Object();
			initEdObj.formId='frm_edition_<s:property value="selectedModule"/>';
			initEdObj.context='edition';
			YAHOO.util.Event.onContentReady('frm_detail_<s:property value="selectedModule"/>',webdomotic.init_periode, initEdObj);
			</script>
		</s:elseif>
		<s:elseif test='top[0].equals("identifiant")'>
			<div class="clear"></div>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/> (3 caracteres):</label>
			<input type="textbox" name='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' class="required validate-alphanum" size='3' maxlength='3'/>
			<div class="clear"></div>
		</s:elseif>
		<s:elseif test='top[3].equals("text")'>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<input type="textbox" name='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' class="required"/>
		</s:elseif>
		<s:elseif test='top[3].equals("number")'>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<input type="textbox" name='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' class="required validate-number"/>
		</s:elseif>
		<s:elseif test='top[3].equals("password")'>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<input type="password" name='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' onchange="document.getElementById('passchange').value='true'"/>
			<input type="hidden" name="passchange" id="passchange" value="false" />
		</s:elseif>
		<s:elseif test='top[3].equals("select")'>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<select name='<s:property value="top[0]"/>'>
				<option value="client">Client</option>
				<option value="administrateur">Admin</option>
			</select>
		</s:elseif>
		<s:elseif test='top[3].equals("bool")'>
			<div class="clear"></div>
			<label for='<s:property value="top[0]"/>'><s:property value="top[2]"/>:</label>
			<input type="checkbox" name='<s:property value="top[0]"/>_chk' id='<s:property value="top[0]"/>_chk' value='<s:property value="top[1]"/>'
			onchange="webdomotic.init_edition_etat()" <s:if test='top[1].equals("1")'>CHECKED</s:if>/>
			<input type="hidden" name='<s:property value="top[0]"/>' id='<s:property value="top[0]"/>' value='<s:property value="top[1]"/>' />
		</s:elseif>
	</s:iterator>
</form>
</div>
</div>
<script>
//<s:if test='selectedId.equals("0")'>
	YAHOO.util.Event.onDOMReady(webdomotic.createYUIEditionForm("EditFrmContainer_<s:property value="selectedModule"/>",true,'<s:property value="selectedModule"/>'));
//</s:if>
//<s:else>
	YAHOO.util.Event.onDOMReady(webdomotic.createYUIEditionForm("EditFrmContainer_<s:property value="selectedModule"/>",false,'<s:property value="selectedModule"/>'));
//</s:else>
</script>