<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
</head>
<body>
    <h1>회원 가입</h1>
    <form method="POST" action="HW2_memberInfo.jsp">
        <label for="userid">아이디:</label>
        <input type="text" id="userid" name="userid" required><br><br>

        <label for="password">패스워드:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" required><br><br>

        이메일 수신 여부:
        <label><input type="radio" name="emailAgree" value="yes" required checked> 수신</label>
        <label><input type="radio" name="emailAgree" value="no"> 비수신</label><br><br>

        관심사항: <br>
        <label><input type="checkbox" name="interest" value="IT"> IT/인터넷</label><br>
        <label><input type="checkbox" name="interest" value="movie"> 영화</label><br>
        <label><input type="checkbox" name="interest" value="sports"> 스포츠</label><br>
        <label><input type="checkbox" name="interest" value="music"> 음악</label><br><br>

        <label for="phone">핸드폰:</label>
        <input type="text" id="phone" name="phone" required><br><br>

        <label for="introduce">자기소개:</label><br>
        <textarea id="introduce" name="introduce" rows="5" cols="30"></textarea><br><br>

        <button type="submit">제출</button>
    </form>
</body>
</html>
