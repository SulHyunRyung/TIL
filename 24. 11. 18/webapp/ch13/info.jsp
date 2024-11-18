<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	/* 		String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String emailAgree = request.getParameter("emailAgree");
			String introduce = request.getParameter("introduce");	 */
	%>

	<jsp:useBean id="member" class="web.ch10.bean.MemberBean" scope="request" />
	<jsp:setProperty property="*" name="member" />


	<h2>회원 정보</h2>
	<p>
		아이디 :
		<%=member.getUserid()%>
	</p>
	<p>
		비밀번호 :
		<%=member.getPassword()%>
	</p>
	<p>
		이메일 :
		<%=member.getEmail()%>
	</p>
	<p>
		이메일 수신 여부 :
		<%=member.getEmailAgree()%>
	</p>
	<p>
		자기소개 :
		<%=member.getIntroduce()%>
	</p>
</body>
</html>