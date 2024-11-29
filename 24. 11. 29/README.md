# 24. 11. 29

## WEB-INF (Web Information)
* Context Root 하위의 정보에 대해 접근할 수 있지만 WEB-INF에는 접근할 수 없다.
* 브라우저에서 url로 jsp 파일 등을 직접 요청하는 경우를 제한하는 것
* 브라우저가 반드시 참조해야 하는 JS, CSS, img 등은 참조할 수 있도록 Context Root의 바로 하위에 static 폴더를 만들어 관리


## JSP & Servlet을 활용한 게시판 사이트 제작 

### index.jsp
* BoardId를 PK로 최근 5개의 게시물이 출력되도록 페이징 처리
* 게시글 제목 클릭 시 req data 전송으로 해당 게시글 상세 페이지로 이동

### register.jsp
* 페이지 내 Input 태그로 req data 전송 및 전달 받아 게시글 데이터 DB로 전송

### update.jsp
* index.jsp(list)에서 받아온 req 데이터로 기존 게시글 정보를 출력한 뒤 수정 후 DB에 전달하는 역할까지 수행.


### detail.jsp
* index.jsp(list)에서 받아온 req 데이터로 DB에서 작성자,제목,내용,작성일,BoardId 등의 데이터를 표시함
