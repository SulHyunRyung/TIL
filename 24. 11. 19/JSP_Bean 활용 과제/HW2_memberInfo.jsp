<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
    	* {
    		font-size: 16px;
    		font-family: 'Malgun Gothic';
    		font-weight: bold;
    		line-height: 32px;
    	}
    </style>
    <title>회원 정보</title>
</head>
<body>
    <h1>회원 가입 정보</h1>
    <jsp:useBean id="member" class="edu.web.homework.MemberVO" />
    <jsp:setProperty property="*" name="member" />

    <pre><%= member.toString() %></pre>
</body>
</html>

