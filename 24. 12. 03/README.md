# 24. 12. 03

	private void loginGET(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("loginGET() 호출");
		req.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(req, res);
	}

	private void loginPOST(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("loginPOST() 호출");
		String memberId = req.getParameter("memberId");
		String password = req.getParameter("password");

		System.out.println("memberId : " + memberId);
		System.out.println("password : " + password);

		if (memberId.equals("test") && password.equals("1234")) {
			HttpSession session = req.getSession();
			session.setAttribute("memberId", memberId);
			session.setMaxInactiveInterval(600);
			res.sendRedirect("index.jsp");

		} else {
			res.sendRedirect("login.go");
		}
	}

* login.jsp에서 테스트용 계정(test&1234)로 로그인 시 세션을 생성
* <c:if test="${sessionScope.memberId == vo.memberId }">
* <c:if test="${empty sessionScope.memberId }">
* 이후 session을 사용하여 사용자를 비교하여, 로그인/아웃, 글 삭제/수정, 댓글 작성/삭제/수정을 컨트롤함.

		if (memberId.equals("test") && password.equals("1234")) {
			HttpSession session = req.getSession();
			session.setAttribute("memberId", memberId);
			session.setMaxInactiveInterval(600);
				
			if(!targetURL.equals("") && targetURL != null) {
				res.sendRedirect(targetURL);
			} else {
				res.sendRedirect("index.jsp");
			}
			

		} else {
			res.sendRedirect("login.go");
		}

* 글 작성 버튼을 클릭 했을 때 memberId 세션이 없다면, 로그인 페이지로 이동시키고 로그인에 성공할 경우 다시 글 작성 페이지로 이동시킴.


### Board, Reply MVC drawing