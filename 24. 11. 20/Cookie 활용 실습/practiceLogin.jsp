<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<%
		// 저장된 쿠키에서 id와 pw를 가져옵니다.
		String savedId = "";
		String savedPw = "";
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("id".equals(cookie.getName())) {
					savedId = cookie.getValue();
				} else if ("pw".equals(cookie.getName())) {
					savedPw = cookie.getValue();
				}
			}
		}
	%>
   <form action="practiceResult.jsp" method="post">
      아이디<br>
      <input type="text" name="id" value="<%= savedId %>"><br>
      비밀번호<br>
      <input type="password" name="pw" value="<%= savedPw %>"><br>
      <input type="checkbox" name="saveAgreed" value="agreed" <%= !savedId.isEmpty() ? "checked" : "" %>>
      아이디 저장<br><br>
      <input type="submit" value="로그인">
   </form>
</body>
</html>
