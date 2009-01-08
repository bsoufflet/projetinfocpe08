<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
String authorized = (String) session.getAttribute("authorized");
if ("yes" != authorized) {
	request.setAttribute("authorized", "no");
    request.setAttribute("Error", "Session has ended.  Please login.");
%>
<jsp:forward page="/pages/login.jsp" />
<%
}
%>
      