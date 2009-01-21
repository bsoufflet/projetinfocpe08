<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pages/loginCheck.jsp" />
<h2>Bonjour, bienvenue sur WebDomotic. <br />
<b>Date de session: </b><%=new Date(session.getLastAccessedTime())%></h2>