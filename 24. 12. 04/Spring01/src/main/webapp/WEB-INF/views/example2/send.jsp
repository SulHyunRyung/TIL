<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 전송 실습</title>
</head>
<body>
   <!-- action="send" -> example2/send의 의미 -->
   <form action="info" method="POST">
      <input type="text" name="name"><br>
      <input type="text" name="email"><br>
      <input type="date" name="birthDate"><br>
      <input type="submit" value="전송">
   </form>
</body>
</html>
