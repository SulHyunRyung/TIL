<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	* {
		text-align: center;
	}
	body {
		display: inline-block;
	}
	table, th, td {
		border-style : solid;
		border-width : 1px;
		text-align : center;
		margin: 0 auto;
	}
	input[type="button"] {
		display: block;
	}
	
	ul {
		list-style-type : none;
	}
	
	li {
		display : inline-block;
	}
</style>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h1>게시판</h1>	
	<a href="register.do">
		<input type="button" value="글 작성">
	</a>
	<table>
		<tr>
			<th style="width : 60px">번호</th>
			<th style="width : 700px">제목</th>
			<th style="width : 120px">작성자</th>
			<th style="width : 100px">작성일</th>
		</tr>
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.boardId }</td>
					<td><a href="detail.do?boardId=${vo.boardId }">${vo.boardTitle }</a></td>
					<td>${vo.memberId }</td>
					<td>${vo.boardDateCreated }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>








