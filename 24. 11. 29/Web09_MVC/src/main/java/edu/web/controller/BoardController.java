package edu.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.domain.BoardVO;
import edu.web.persistence.BoardDAO;
import edu.web.persistence.BoardDAOImple;
import edu.web.util.PageCriteria;

@WebServlet("*.do") // .do url로 요청된 HTPP 호출에 대해 반응
public class BoardController extends HttpServlet {
	private static final String BOARD_URL = "/WEB-INF/board/";
	private static final String MAIN = "index";
	private static final String LIST = "list";
	private static final String REGISTER = "register";
	private static final String DETAIL = "detail";
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";
	private static final String EXTENSION = ".jsp";
	private static final String SERVER_EXTENSTION = ".do";

	private static final long serialVersionUID = 1L;
	private static BoardDAO dao;

	public BoardController() {
		dao = BoardDAOImple.getInstance();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String requestMethod = req.getMethod();
		System.out.println("경로 : " + requestURI);
		System.out.println("방식 : " + requestMethod);

		if (requestURI.contains(LIST + SERVER_EXTENSTION)) {
			System.out.println("list.do 호출");
			list(req, res);
		} else if (requestURI.contains(REGISTER + SERVER_EXTENSTION)) {
			System.out.println("register.do 호출");
			if (requestMethod.equals("GET")) { // doGet
				registerGET(req, res);
			} else if (requestMethod.equals("POST")) { // doPost
				registerPOST(req, res);
			}
		} else if (requestURI.contains(DETAIL + SERVER_EXTENSTION)) {
			System.out.println("detail.do 호출");
			detail(req, res);
		} else if (requestURI.contains(UPDATE + SERVER_EXTENSTION)) {
			System.out.println("update.do 호출");
			if (requestMethod.equals("GET")) { // doGet
				updateGET(req, res);
			} else if (requestMethod.equals("POST")) { // doPost
				updatePOST(req, res);
			}
		}  else if (requestURI.contains(DELETE + SERVER_EXTENSTION)) {
				System.out.println("delete.do 호출");
				if (requestMethod.equals("POST")) { // doPost
					deletePOST(req, res);
				}
			}
		}




	// register.jsp 페이지 호출
	private void registerGET(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("registerGET()");

		String path = BOARD_URL + REGISTER + EXTENSION;
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, res);
	} // End registerGET

	// register.jsp form 에서 전송한 데이터를 DB 테이블에 등록
	// 그리고 index.jsp로 이동
	private void registerPOST(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("registerPOST()");
		String boardTitle = req.getParameter("boardTitle");
		String memberId = req.getParameter("memberId");
		String boardContent = req.getParameter("boardContent");

		BoardVO vo = new BoardVO(0, boardTitle, boardContent, memberId, null);
		System.out.println(vo);
		int result = dao.insert(vo);
		System.out.println("결과 : " + result);

		if (result == 1) {
			res.sendRedirect("index.jsp");
		}
	} // End registerPOST

	// 전체 게시글 내용(list)을 조회하고, 그 데이터를 ist.jsp 페이지에 전송
	private void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("list()");
		
		String page = req.getParameter("page");
		PageCriteria criteria = new PageCriteria();
		
		if(page != null) {
			criteria.setPage(Integer.parseInt(page));
		}
		
		List<BoardVO> list = dao.select(criteria);

		String path = BOARD_URL + LIST + EXTENSION;
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		
		req.setAttribute("list", list);
		dispatcher.forward(req, res);
	} // End list
	
	// DB 테이블에서 상세 게시글 조회 데이터를 가져와서
	// detail.jsp 페이지로 전송
	private void detail(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("detail()");
		
		int boardId= Integer.parseInt(req.getParameter("boardId"));
		BoardVO vo = dao.select(boardId);
		System.out.println(vo);
		
		String path = BOARD_URL + DETAIL + EXTENSION;
		RequestDispatcher dispatcher =
				req.getRequestDispatcher(path);
		req.setAttribute("vo", vo);
		dispatcher.forward(req, res);
	} // End detail

	// DB 테이블에서 상세 게시글 조회 데이터를 가져와서
	// update.jsp 페이지에 전송
	private void updateGET(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("updateGET()");
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		BoardVO vo = dao.select(boardId);
		
		String path = BOARD_URL + UPDATE + EXTENSION;
		RequestDispatcher dispatcher =
				req.getRequestDispatcher(path);
		req.setAttribute("vo", vo);
		dispatcher.forward(req, res);
	} // updateGET
	
	private void updatePOST(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		String boardTitle = req.getParameter("boardTitle");
		String boardContent = req.getParameter("boardContent");
		
		BoardVO vo = new BoardVO(boardId, boardTitle, boardContent, null, null);
		int result = dao.update(vo);
		System.out.println("결과 : " + result);
		
		if(result == 1) {
			String path = DETAIL + SERVER_EXTENSTION;
			res.sendRedirect(path + "?boardId=" + boardId);
		}
	} // updatePOST
	
	// 선택된 게시글 번호를 전송받아서, DB 테이블에서 게시글 데이터 삭제
	// 삭제가 완료되면, index.jsp로 이동
	private void deletePOST(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int result = dao.delete(boardId);
		System.out.println(boardId + "번 삭제 완료!");
		if(result == 1) {
			res.sendRedirect(MAIN + EXTENSION);
		}
	} // deletePOST
} // End BoardController
