<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<jsp:include page="/pages/loginCheck.jsp" />
Bonjour, bienvenue sur WebDomotic. <br />
<b>Date de session: </b><%=new Date(session.getLastAccessedTime())%>