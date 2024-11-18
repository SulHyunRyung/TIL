<%@page import="web.ch10.bean.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useBean</title>
</head>
<body>
	<jsp:useBean id="member" class="web.ch10.bean.MemberBean">
	</jsp:useBean>
	
	<%
		// useBean 코드와 동일한 코드
		// MemberBean member = new MemberBean();
	%>
	
	<%-- useBean 사용 방법1 --%>
	<jsp:setProperty property="userid" name="member" value="guest"/>
	
	<p>id 출력 : <jsp:getProperty name="member" property="userid" /></p>
	
	<%-- useBean 사용 방법2 --%>
	<% member.setEmail("test@test.com"); %>
	
	<p>email 출력 : <%= member.getEmail() %></p>

</body>
</html>









