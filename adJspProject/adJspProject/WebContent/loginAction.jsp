<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="QueryBean" scope="page" class="db.beans.QueryBean"/>
<jsp:setProperty property="*" name="QueryBean"/>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("utf-8");
	
	String userID    = request.getParameter("userID") == null ? ""    : request.getParameter("userID").trim();
	String userPassword  = request.getParameter("userPassword") == null ? ""  : request.getParameter("userPassword").trim();
	
	System.out.println("id: "    + userID);
	System.out.println("name: "  + userPassword);
	
	QueryBean.getConnection();
	
	int res = 0;
	
	try {
		res = QueryBean.login(userID, userPassword);
	} catch(Exception e) {
		out.print(e.toString());
	} finally {
		QueryBean.closeConnection();
	}
	
	out.println("[");
	out.println("{ ");
	out.println("\"RESULT_OK\": \"" + res + "\" ");
	out.println("} ");
	out.println("]");
	
	System.out.println("res: " + res);
%>