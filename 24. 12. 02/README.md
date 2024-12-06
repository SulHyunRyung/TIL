# 24. 12. 02

## JSP & Servlet을 활용한 게시판 사이트 제작 
* 게시글 페이징 처리

[Query]

public static final String SQL_SELECT_PAGESCOPE =
    "SELECT * FROM (" +
    "SELECT ROW_NUMBER() OVER (ORDER BY BOARD_ID DESC) AS RN, BOARD.* FROM BOARD" +
   ") WHERE RN BETWEEN ? AND ?";

* BoardId(게시글 식별 ID) 정렬 및 ?개부터 ?개까지 분리하는 쿼리 작성

[PageCriteria]

package edu.web.util;

// 브라우저에서 보여질 페이지 번호와
// 한 페이지에서 보여질 게시글의 개수를 저장하는 클래스
// -> paging 처리에 필요한 start와 end 번호를 알 수 있음
public class PageCriteria {
	private int page; // 현재 페이지 번호
	private int numsPerPage; // 한 페이지의 게시글 개수
	
	public PageCriteria() {
		this.page = 1;
		this.numsPerPage = 5;
	}
	
	public PageCriteria(int page, int numsPerPage) {
		this.page = page;
		this.numsPerPage = numsPerPage;
	}

	// getter/setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumsPerPage() {
		return numsPerPage;
	}

	public void setNumsPerPage(int numsPerPage) {
		this.numsPerPage = numsPerPage;
	}
	
	// 현재 보여지는 페이지의 시작 글 일련번호(rn)
	public int getStart() {
		return (this.page - 1) * this.numsPerPage + 1;
	}
	
	// 현재 보여지는 페이지의 마지막 글 일련번호(rn)
	public int getEnd() {
		return this.page * this.numsPerPage;
	}
	
}

* 해당 파일에 페이징 시작 번호, 끝 번호를 구하는 코드 구현

[PageMaker]

package edu.web.util;

// 페이지 번호들의 링크를 만들기 위한 유틸리티 클래스
public class PageMaker {
	private PageCriteria criteria;
	private int totalCount; // 전체 게시글 개수
	private int numsOfPageLinks; // 페이지 번호 링크의 개수
	private int startPageNo; // 시작 페이지 링크 번호
	private int endPageNo; // 끝 페이지 링크 번호
	private boolean hasPrev; // 화면에 보이는 시작 페이지 번호보다 작은 숫자의 페이지가 있는 지
	private boolean hasNext; // 화면에 보이는 끝 페이지 번호보다 큰 숫자의 페이지가 있는 지
	
	public PageMaker() {
		this.numsOfPageLinks = 5;
	}
	
	public PageCriteria getCriteria() {
		return criteria;
	}
	
	public void setCriteria(PageCriteria criteria) {
		this.criteria = criteria;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getNumsOfPageLinks() {
		return numsOfPageLinks;
	}
	
	public int getStartPageNo() {
		return startPageNo;
	}
	
	public int getEndPageNo() {
		return endPageNo;
	}
	
	public boolean isHasPrev() {
		return hasPrev;
	}
	
	public boolean isHasNext() {
		return hasNext;
	}
	
	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산 및 세팅
	public void setPageData() {
		int totalLinkNo = (int) Math.ceil((double) totalCount / criteria.getNumsPerPage());
		int temp = (int) Math.ceil((double) criteria.getPage() / numsOfPageLinks) * numsOfPageLinks;
		
		if (temp > totalLinkNo) {
			endPageNo = totalLinkNo;
		} else {
			endPageNo = temp;
		}
		
		startPageNo = ((endPageNo - 1) / numsOfPageLinks) * numsOfPageLinks + 1;
		
		if (startPageNo == 1) {
			hasPrev = false;
		} else {
			hasPrev = true;
		}
		
		if (endPageNo * criteria.getNumsPerPage() >= totalCount) {
			hasNext = false;
		} else {
			hasNext = true;
		}
		// Math.ceil (올림)
		// Math.floor (버림)
		
	}
	
} // end PageMaker

* 페이지 번호들의 링크를 만들기 위한 유틸리티 클래스

[DAO]

List<BoardVO> select(PageCriteria criteria);

* DAO에 해당 쿼리를 사용하기 위한 메소드 선언
* DAOImple 같은 경우는 기존 select와 동일하되, 사용 쿼리만 SQL_SELECT_PAGESCOPE로 변경

[Servlet]

String page = req.getParameter("page");
PageCriteria criteria = new PageCriteria();
		
if(page != null) {
	criteria.setPage(Integer.parseInt(page));
}
		
List<BoardVO> list = dao.select(criteria);

String path = BOARD_URL + LIST + EXTENSION;
RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		
req.setAttribute("list", list);
PageMaker pageMaker = new PageMaker();
		
int totalCount = dao.getTotalCount();
pageMaker.setTotalCount(totalCount);
pageMaker.setCriteria(criteria);
pageMaker.setPageData(); // 적용된 값들로 pageMaker의 필드 값 계산
req.setAttribute("pageMaker", pageMaker);

[JSP] (list.jsp)

[게시글 정보]
<c:forEach var="vo" items="${list }">
	<tr>
		<td>${vo.boardId }</td>
		<td><a href="detail.do?boardId=${vo.boardId }">${vo.boardTitle }</a></td>
		<td>${vo.memberId }</td>
		<td>${vo.boardDateCreated }</td>
	</tr>
</c:forEach>

[페이징 리스트]
```
<ul>
    <c:if test="${pageMaker.hasPrev}">
        <li>
            <a href="list.do?page=${pageMaker.startPageNo - 1}">&lt; 이전</a>
        </li>
    </c:if>
    
    <c:forEach begin="${pageMaker.startPageNo}" end="${pageMaker.endPageNo}" var="num">
        <li>
            <a href="list.do?page=${num}">${num}</a>
        </li>
    </c:forEach>
    
    <c:if test="${pageMaker.hasNext}">
        <li>
            <a href="list.do?page=${pageMaker.endPageNo + 1}">다음 &gt;</a>
        </li>
    </c:if>
</ul>
```
* Servelt, Pagecriteria, PageMaker의 코드들을 활용하여 페이징 처리 

### 기능 로직 정리
```
1) 전체 게시글을 n개부터 n개까지 분리
2) 페이징 넘버 start, end을 파라미터로 받음
3) PageMarker, PageCriteria 코드를 이용해  번호 리스트 상호작용 시
   페이징 넘버 조정
4) 번호 리스트에 forEach문을 사용하여 필요한 만큼 번호 출력
5) 이전, 다음 게시글이 있을 때만 <이전,다음> 표시 되도록 조건 설정
 

* 게시글 내 댓글 작성, 수정, 삭제 기능 구현
* 기존 insert, select, update, delete 쿼리를 사용하여 구현

[replyAdd]

$("#btnAdd").click(function(){
	let boardId = $("#boardId").val();
	let memberId = $("#memberId").val();
	let replyContent = $("#replyContent").val();
	
	let obj = {
		'boardId' : boardId,	
		'memberId' : memberId,	
		'replyContent' : replyContent,	
	};
	console.log(obj);
	
	// $.ajax 송수신
	$.ajax({
		type : 'POST',
		url : 'replies/add',
		data : {'obj' : JSON.stringify(obj)}, // parse JSON
		success : function(result) {
			console.log(result);
			if(result == 'success') {
				alert('성공이다냥');
			}
		}
	}); // End $.ajax
}); // End #btnAdd.click

JSONObject jsonObject = (JSONObject) parser.parse(obj);

int boardId = Integer.parseInt((String) jsonObject.get("boardId"));
String memberId = (String) jsonObject.get("memberId");
String replyContent = (String) jsonObject.get("replyContent");

ReplyVO vo = new ReplyVO(0, boardId, memberId, replyContent, null);
System.out.println(vo);

int result= dao.insert(vo);
if(result == 1 ) {
	res.getWriter().append("success");
}

* ajax를 활용하여 비동기 방식으로 댓글 등록하도록 구현.

[replyList]

function getAllreplies() {
	let boardId = $('#boardId').val();
	// url에 boardId 전송
	let url = 'replies/all?boardId=' + boardId;
		
	// ajax로 전체 데이터 GET
	$.getJSON(url, function(data){
		let list = '';

			$(data).each(function(){
				console.log(this);
					
				// replyDateCreated는 string 타입이므로 date 타입으로 변경
				let replyDateCreated = new Date(this.replyDateCreated);
				list += 
				    '<div class="reply_item">' +
			            '<input type="hidden" id="replyId" value="' + this.replyId + '">' +
			        t   his.memberId + '&nbsp;&nbsp;' +
			            '<input type="text" id="replyContent" value="' + this.      replyContent + '">&nbsp;&nbsp;' +
		    	        replyDateCreated + '&nbsp;&nbsp;' +
	    		        '<button class="btn_update">수정</button>' +
    			        '<button class="btn_delete">삭제</button>' +
				    '</div>';
			}); // $(data).html(list);
			$('#replies').html(list);
		}); // End getJSON
	} // End getAllreplies
			
private void replyList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	System.out.println("replyList");
	int boardId = Integer.parseInt(req.getParameter("boardId"));
	List<ReplyVO> list = dao.select(boardId);
		
	JSONArray jsonArray = new JSONArray();
	for(int i = 0; i < list.size(); i++) {
	JSONObject jsonObject = new JSONObject();
		ReplyVO vo = list.get(i);
		jsonObject.put("replyId", vo.getReplyId());
		jsonObject.put("boardId", vo.getBoardId());
		jsonObject.put("memberId", vo.getMemberId());
		jsonObject.put("replyContent", vo.getReplyContent());
		jsonObject.put("replyDateCreated", vo.getReplyDateCreated().toString());
		jsonArray.add(jsonObject);
	} 
	System.out.println(jsonArray.toString());
	res.setContentType("application/json");
	System.out.println("List size: " + list.size()); 
	res.getWriter().append(jsonArray.toJSONString());
}

* Servlet으로 받을 데이터를 구성한 후 JSON, ajax로 데이터 추출 및 적용 후 출력


[replyUpdate]

		$('#replies').on('click', '.reply_item .btn_update', function(){
		    console.log(this);

		    // 선택된 수정 버튼과 같은 노드에 있는 replyId, replyContent 값을 저장
		    let replyId = $(this).prevAll('#replyId').val();
		    let replyContent = $(this).prevAll('#replyContent').val();
		    console.log("Reply ID: " + replyId + ", Reply Content: " + replyContent);
		    
		    let data = {
		        replyId: replyId,
		        replyContent: replyContent
		    };
		    
		    $.ajax({
		        url: 'replies/update',
		        type: 'POST',
		        data: { obj: JSON.stringify(data) },  
		        success: function(response) {
		            if(response === 'success') {
		                alert('댓글이 수정되었습니다.');
		                getAllreplies();
		            } else {
		                alert('댓글 수정에 실패했습니다.');
		            }
		        }
		    }); // End $.ajax
		}); // End #replies .btn_update.on.
        
    private void replyUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String obj = req.getParameter("obj");
	    System.out.println("Update Data: " + obj);

	    JSONParser parser = new JSONParser();
	    try {
	        JSONObject jsonObject = (JSONObject) parser.parse(obj);
	        
	        int replyId = Integer.parseInt((String) jsonObject.get("replyId"));
	        String replyContent = (String) jsonObject.get("replyContent");

	        ReplyVO vo = new ReplyVO(replyId, 0, null, replyContent, null);  // boardId, memberId는 수정하지 않음
	        System.out.println(vo);
	        int result = dao.update(vo);  
	        if(result == 1) {
	            res.getWriter().append("success");
	        }
	    } catch (ParseException e) {
	        e.printStackTrace();
	        res.getWriter().append("error");
	    }
	}

* .on 메소드로 해당 수정 버튼에 관련된 replyId, replyContent에 
* 이후 해당 데이터를 송수신하여 DAOImple에 있는 update 메소드로 수정 구현

[replyDelete]

		$('#replies').on('click', '.reply_item .btn_delete', function(){
		    console.log(this);

		    // 선택된 삭제 버튼과 같은 노드에 있는 replyId 값을 저장
		    let replyId = $(this).prevAll('#replyId').val();
		    console.log("Reply ID: " + replyId);
		    
		    if (confirm('댓글을 삭제하시겠습니까?')) {
		        $.ajax({
		            url: 'replies/delete',
		            type: 'POST',
		            data: { replyId: replyId },
		            success: function(response) {
		                if(response === 'success') {
		                    alert('댓글이 삭제되었습니다.');
			                getAllreplies();
		                } else {
		                    alert('댓글 삭제에 실패했습니다.');
		                }
		            }
		        }); // End $.ajax
		    }
		}); // End #replies .btn_delete.on.click

    private void replyDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String replyIdStr = req.getParameter("replyId");
	    System.out.println("Delete Data: " + replyIdStr);

	    try {
	        int replyId = Integer.parseInt(replyIdStr);
	        int result = dao.delete(replyId);
	        if(result == 1) {
	            res.getWriter().append("success");
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        res.getWriter().append("error");
	    }
	}

* 수정과 비슷한 방식으로 replyId에 접근 및 데이터 송수신 후 delete 실행.
```
