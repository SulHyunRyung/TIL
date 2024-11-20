<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String saveAgreed = request.getParameter("saveAgreed");

		if ("agreed".equals(saveAgreed)) {
			Cookie idCookie = new Cookie("id", id);
			Cookie pwCookie = new Cookie("pw", pw);
			Cookie saveAgreedCookie = new Cookie("saveAgreed", "agreed");

			idCookie.setMaxAge(10 * 60);
			pwCookie.setMaxAge(10 * 60);
			saveAgreedCookie.setMaxAge(10 * 60);

			response.addCookie(idCookie);
			response.addCookie(pwCookie);
			response.addCookie(saveAgreedCookie);
		} else {
			
			Cookie idCookie = new Cookie("id", "");
			Cookie pwCookie = new Cookie("pw", "");
			Cookie saveAgreedCookie = new Cookie("saveAgreed", "");

			idCookie.setMaxAge(0);
			pwCookie.setMaxAge(0);
			saveAgreedCookie.setMaxAge(0);

			response.addCookie(idCookie);
			response.addCookie(pwCookie);
			response.addCookie(saveAgreedCookie);
		}
	%>
	<h2>로그인 결과</h2>
	<p><strong><%= id %></strong>님 환영합니다!</p>
</body>
</html>
