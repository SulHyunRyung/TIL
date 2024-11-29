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
      <jsp:forward>: 다른 페이지로 요청을 포워딩할 때 사용.
     - 여기서는 "list.do"라는 URL로 요청을 포워딩.
     - 클라이언트는 "list.do"의 결과를 받게 되며, 브라우저 URL에는 포워딩된 경로가 표시되지 않음.
     - 포워딩된 요청은 서버 내부에서 처리되므로 클라이언트가 이를 인식하지 못함.
    --%>


   <jsp:forward page="list.do"></jsp:forward>
</body>
</html>

