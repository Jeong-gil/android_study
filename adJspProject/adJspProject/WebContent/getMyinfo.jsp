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

String userID = request.getParameter("userID") == null ? "" : request.getParameter("userID").trim();

QueryBean.getConnection();

ArrayList resArr = new ArrayList();
try {
	resArr = QueryBean.searchTitleByuserID(userID);
} catch(Exception e) {
	out.print(e.toString());
} finally {
	QueryBean.closeConnection();
}

out.println("{");
out.println("\"datas\":[");

if(resArr.size() == 0) {
	out.println("]");
	out.println("}");
} else {
	out.println("{");
	out.println("\"userID\": \""			+ (String) resArr.get(0) + "\", ");
	out.println("\"userPassword\": \""			+ (String) resArr.get(1) + "\", ");
	out.println("\"userName\": \""			+ (String) resArr.get(2) + "\", ");
	out.println("\"userGender\": \""	+ (String) resArr.get(3) + "\"");
	out.println("\"userEmail\": \""	+ (String) resArr.get(3) + "\"");
	out.println("} ");
	
	out.println("]");
	out.println("}");
}
%>