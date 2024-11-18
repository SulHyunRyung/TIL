<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 가입 페이지</h2>
	<form action="info.jsp" method="POST">
		<p>아이디</p>
		<input type="text" name="userid" placeholder="아이디 입력" required="required">
		<p>패스워드</p>
		<input type="password" name="password" placeholder="비밀번호 입력" required="required">
		<p>이메일</p>
		<input type="email" name="email" placeholder="ex)example@naver.com" required="required">
		<p>이메일 수신여부</p>
		<input type="radio" name="emailAgree" value="yes" checked>예
		<input type="radio" name="emailAgree" value="no">아니오
		<p>자기소개</p>
		<textarea rows="4" cols="30" name="introduce" placeholder="자기소개 입력" required="required"></textarea>
		<input type="submit" value="전송">
		
	</form>
</body>
</html>