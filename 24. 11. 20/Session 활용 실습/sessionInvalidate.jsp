<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>세션 속성(attribute)값 제거/invalidate(무효화)</h1>
	<%
		// 세션 attribute에 데이터 저장
		session.setAttribute("password", 123);
	
		// 세션 attribute 출력
		Enumeration<String> attrNames = session.getAttributeNames();
		while(attrNames.hasMoreElements()){
			String name = attrNames.nextElement();
			out.println(name + "<br>");
		}
		
		out.println("<p>userid 속성 제거</p>");
		session.removeAttribute("userid");
		
		attrNames = session.getAttributeNames();
		while(attrNames.hasMoreElements()){
			String name = attrNames.nextElement();
			out.println(name + "<br>");
		}
		
		session.invalidate(); // 세션 무효화 : 모든 세션 속성을 제거
		// 유효하지 않은 세션 : 세션 속성이 아무것도 없거나 세션이 invalidate된 경우
		
		// 현재 세션의 유효(valid) 여부 체크
		if(request.isRequestedSessionIdValid()) {
			out.println("우효다제<br>");	
		} else {
			out.println("우효하지 않다 이말이야<br>");
		}
	%>
</body>
</html>