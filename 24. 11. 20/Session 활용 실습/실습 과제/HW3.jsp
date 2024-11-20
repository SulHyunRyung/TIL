<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
   1. 로그인 정보 전송 페이지(HW3.jsp)
   - <form action="HW3_login_auth.jsp" method="post">
   - id, pw input 태그 생성
    --%>
	<form action="HW3_login_auth.jsp" method="POST">
		<label for="userid"> 
			ID 
			<input type="text" name="userid">
		</label><br><br>
		<label for="userpw"> 
			PW 
			<input type="password" name="userpw">
		</label><br><br>
		<input type="submit" value="제출">
	</form>
</body>
</html>