<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
	   3. 로그인 결과 페이지(HW3_login_result.jsp)
	   - 세션에서 id 값을 꺼내서 HTML 태그에 출력
	   - 만약, 로그인을 하지 않고 url로 접속할 경우 '로그인 해주세요!!'라고 alert를 띄우고
	     HW3.jsp 페이지로 이동
	   - alert 띄우는 코드
	     ㄴ out.print("<script>alert('로그인 해주세요!!');</script>");
	--%>
	<%
		request.setCharacterEncoding("UTF-8");
		String userid = (session != null) ? (String) session.getAttribute("userid") : null;
	
	    if (userid == null) { // 로그인하지 않은 경우
	        out.print("<script>");
	        out.print("alert('로그인 해주세요!!');");
	        out.print("location.href='HW3.jsp';"); // 로그인 페이지로 이동
	        out.print("</script>");
	        out.close();
	    } else { // 로그인된 경우
	%>
	    <h1>환영합니다, <%= userid %> 님</h1>
	    <a href="HW3_logout.jsp">로그아웃</a>
	<%
    	}
	%>
</body>
</html>