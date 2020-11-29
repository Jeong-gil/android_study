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
	String userPassword    = request.getParameter("userPassword") == null ? ""    : request.getParameter("userPassword").trim();
	String userName  = request.getParameter("userName") == null ? ""  : request.getParameter("userName").trim();
	String userEmail  = request.getParameter("userEmail") == null ? ""  : request.getParameter("userEmail").trim();
	
	System.out.println("userID: "    + userID);
	System.out.println("userPassword: "    + userPassword);
	System.out.println("userName: "  + userName);
	System.out.println("userEmail: "  + userEmail);
	
	QueryBean.getConnection();
	
	int res = 0;
	
	try {
		res = QueryBean.UpdateMyInfo(userID, userPassword, userName, userEmail);
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