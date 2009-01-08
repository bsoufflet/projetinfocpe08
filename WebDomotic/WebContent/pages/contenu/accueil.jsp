<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<jsp:include page="/pages/loginCheck.jsp" />
ACCUEIL
<br/>
Welcome, you have logined. <br />
<b>Session Time: </b><%=new Date(session.getLastAccessedTime())%>