<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="QueryBean" scope="page" class="db.beans.QueryBean"/>
<jsp:setProperty property="*" name="QueryBean"/>
<jsp:useBean id="User" class="user.User" scope="page" />
<jsp:setProperty name="User" property="userID" />
<jsp:setProperty name="User" property="userPassword" />
<jsp:setProperty name="User" property="userName" />
<jsp:setProperty name="User" property="userGender" />
<jsp:setProperty name="User" property="userEmail" />

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("utf-8");
	
	QueryBean.getConnection();
	
	int res = 0;
	
	String userID = User.getUserID();
	String userPassword = User.getUserPassword();
	String userName = User.getUserName();
	String userGender = User.getUserGender();
	String userEmail = User.getUserEmail();
	
	System.out.println("userID : " + userID);
	System.out.println("userPassword : " + userPassword);
	System.out.println("userName : " + userName);
	System.out.println("userGender : " + userGender);
	System.out.println("userEmail : " + userEmail);
	
	if (User.getUserID() == null || User.getUserPassword() == null || User.getUserName() == null || 
			User.getUserGender() == null || User.getUserEmail() == null) {
		
	} else {
		res = QueryBean.joinUser(userID, userPassword, userName, userGender, userEmail);
	}
	
	
	out.println("[");
	out.println("{ ");
	out.println("\"RESULT_OK\": \"" + res + "\" ");
	out.println("} ");
	out.println("]");
	
	// 0 : 입력되지 않은 항목 있음
	// -1 : 이미 존재하는 아이디가 있음
	// 1 : 회원가입 완료
	System.out.println("res: " + res);
%>