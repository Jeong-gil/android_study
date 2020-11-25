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
	String title    = request.getParameter("title") == null ? ""    : request.getParameter("title").trim();
	String content  = request.getParameter("content") == null ? ""  : request.getParameter("content").trim();
	
	System.out.println("title: "    + title);
	System.out.println("content: "  + content);
	
	QueryBean.getConnection();
	
	int res = 0;
	
	try {
		res = QueryBean.insertContent(userID, title, content);
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