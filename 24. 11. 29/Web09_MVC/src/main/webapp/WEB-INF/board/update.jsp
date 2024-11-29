<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.boardTitle }</title>
</head>
<body>
	<h2>게시글 수정</h2>
   <form action="update.do" method="POST">
      <input type="hidden"  name="boardId" value="${vo.boardId }">
      <div>
         <p>제목 : </p>
         <input type="text" name="boardTitle" value="${vo.boardTitle }">
      </div>
      <div>
         <p>작성자 : ${vo.memberId }</p>
         <p>작성일 : ${vo.boardDateCreated }</p>
      </div>
      <div>
         <p>내용 : </p>
         <textarea rows="20" cols="120" name="boardContent">${vo.boardContent }</textarea>
      </div>
      <div>
         <input type="submit" value="등록">
      </div>
   </form>
</body>
</html>