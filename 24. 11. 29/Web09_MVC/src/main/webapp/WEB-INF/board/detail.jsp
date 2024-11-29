<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
</head>
<body>
	<h2>게시글 조회</h2>
	<div>
		<p>글 번호 : ${vo.boardId }</p>
	</div>
	<div>
		<p> 제목 : </p>
		<p>${vo.boardTitle }</p>
	</div>
	<div>
		<p>작성자 : ${vo.memberId}</p>
		<p>작성일 : ${vo.boardDateCreated}</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.boardContent }</textarea>
	</div>
	
	<a href="index.jsp">
		<input type="button" value="목록">
	</a>
	
	<a href="update.do?boardId=${vo.boardId }">
		<input type="button" value="수정">
	</a>
	<form action="delete.do" method="POST">
		<input type="hidden" name="boardId" value="${vo.boardId }">
		<input type="submit" value="삭제">
	</form>
</body>
</html>