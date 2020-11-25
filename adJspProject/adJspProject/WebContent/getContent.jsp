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

String no = request.getParameter("no") == null ? "" : request.getParameter("no").trim();

QueryBean.getConnection();

String strNo = "";
try {
	strNo = QueryBean.getContentByNo(no);
} catch(Exception e) {
	out.print(e.toString());
} finally {
	QueryBean.closeConnection();
}

out.println("{");
out.println("\"datas\":[");

if(strNo.equals("")) {
	out.println("]");
	out.println("}");
} else {
	out.println("{");
	out.println("\"content\": \""	+ strNo + "\"");
	out.println("} ");
	
	out.println("]");
	out.println("}");
}
%>