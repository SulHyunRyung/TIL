<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		2. 로그인 인증 페이지(HW3_login_auth.jsp)
		   - 전송받은 id, pw를 확인하여 id는 "test", pw는 "1234"일 경우
		     id 세션을 생성(만료 시간은 자유롭게 설정)
		   - 세션을 생성한 이후에 HW3_login_result.jsp 페이지로 이동
		   - 페이지 이동 코드
		   ㄴ out.print("<script>location.href='HW3_login_result.jsp'</script>");
		   - 전송받은 id, pw가 "test", "1234"가 아닌 경우, HW3.jsp 페이지로 이동
   --%>

	<%
		request.setCharacterEncoding("UTF-8");
	
   		String userid = request.getParameter("userid");
  		String userpw = request.getParameter("userpw");
  		
  		if (userid.equals("test") && userpw.equals("1234")) {
  			session.setAttribute("userid", userid);
  			session.setAttribute("userpw", userpw);
  			session.setMaxInactiveInterval(60);
  			response.sendRedirect("HW3_login_result.jsp");
  		} else {
  			out.print("<script>alert('ID 혹은 패스워드가 틀립니다.\\n다시 확인해주세요.'); location.href='HW3.jsp';</script>");  		
  			out.close();
  		}
   %>

	<h2>
		<%=userid %>
	</h2>

	<h2>
		<%=userpw %>
	</h2>
</body>
</html>