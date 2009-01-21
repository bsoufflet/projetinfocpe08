<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<s:if test='resultGetObject[3].equals("")'>
	<select name='<s:property value="resultGetObject[2]"/>'>
		<s:iterator value="resultGetObject[0]">
			<option value='<s:property value="top[0]"/>'
				<s:if test='resultGetObject[1].equals(top[0])'>selected="selected"</s:if>>
			<s:property value="top[1]" /></option>
		</s:iterator>
	</select>
</s:if>
<s:else>
	<s:iterator value="resultGetObject[0]">
		<s:if test='resultGetObject[1].equals(top[0])'><s:property value="top[1]"/></s:if>
	</s:iterator>
</s:else>