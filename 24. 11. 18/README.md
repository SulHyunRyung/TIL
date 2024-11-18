# 2024. 11. 18 TIL

## IP 주소 (Internet Protocol address)
* 네트워크 상에서 컴퓨터를 식별하기 위해 부여된 위치 주소.
* IPv4란 일반적으로 사용하는 IP 주소 형식이며, 오랜 사용으로 인해 고갈 현상을 해결하기 위한 IPv6가 출현.

## Port 번호
* 인터넷이나 다른 네트워크 메시지가 서버에서 도착할 때 전달될 특정 프로세스를 식별하는 
번호 
* 프로세스는 컴퓨터 내에서 실행되는 특정 프로그램을 의미

## 웹 클라이언트 (Web Client)
* 일반 사용자가 웹 브라우저를 통해 작업하는 공간을 의미
* 일반적인 프론트엔드 사이드가 이에 해당됨

## 웹 서버 (Web Server) 
* 웹 클라이언트가 요청하는 정적 컨텐츠를 제공하는 서버.
( 정적 컨텐츠 : 컴퓨터에 저장되어 변화하지 않는 파일 )
* 클라이언트에서 requset를 WAS에 보내고 요청에 대한 WAS 처리 결과를 클라이언트에 전달해주는 역할 (response)

## WAS(Web Applicatoin Server)
* DB에 대한 요청(CRUD)이나 로직 처리를 요구하는 동적 컨텐츠를 제공하기 위한 Application Server
* 기본적으로 J2EE의 스펙을 구현하며, Servlet이나 JSP로 작성된 애플리케이션을 실행하는 소프트웨어
* WAS의 경우 Web Server가 없어도 서버의 역할을 수행 가능
* Apache Tomcat, Oracle WebLogic Application Server, IBM WebSphere Application Server 
등

## Web Container(Servlet Container)
* 웹 서버의 동적 데이터 반환을 위한 중간자 역할의 소프트웨어 모듈
* 웹 서버의 구성 요소로 Java Servlet과 상호작용
* Web Server + WAS + DB 연동 구조
![ServerWASDB구조](https://github.com/user-attachments/assets/b9985568-4e93-49c7-9038-7809201bab0d)

* 웹 서비스 흐름
![WebServiceFlow](https://github.com/user-attachments/assets/bd4747f5-e2f3-4e44-8ffd-4141085b7318)

## Java Servlet
* HTTP 통신을 requset/response 기능을 제공하는 자바 클래스로, HTTP 통신이 기본적으로 구비가 되어야 함.
* 동적 웹 페이지를 생성하기 위한 Java 표준 기술
* 클라이언트와의 통신으로 전반적인 클라이언트와 서버 간의 requset/response를 처리하는 도구

## Servlet Contatiner
* 웹 서버와 Servlet 간의 통신을 제공
* Jakarta EE 애플리케이션을 위한 런타임 환경 제공 (JVM)
* Servlet 생명 주기 관리
* Servlet Container와 Servlet 구조
![ServletStructure](https://github.com/user-attachments/assets/3131b195-39ab-4bdb-9db2-dc87812aa091)

## JSP
* Java Servlet 기반으로 구축된 웹 기술
* 정적 컨텐츠(HTML)과 동적 컨텐츠(Java)를 모두 포함한 형태
* JSP 컴파일 과정

## JSP tag

	<%-- Declaration(선언문) --%>

	<%!
		public int add(int x, int y) {
			return x + y;
		}
	%>
	
	<%-- Scriptlet --%>
	<%
		// 지역 변수 선언
		int result = add(5, 20);
	
		// JSP에서 출력하는 방법
		// 1) 로그
		System.out.println("result = " + result);
		
		// 2) JspWrite 객체를 사용하여 응답(response)으로 출력
		out.write("<p>result =" + result + "</p>\r\n");
		
		// 3) Expresstion 사용	
	%>

	<p>결과 <%=result %></p>
	
	<% 
		Date date = new Date();
	%>
	
	<p><%=date %></p>

## JSP variable

	<%
		int inputMoney = 1000000;
		int period = 24;
		double interest = Double.parseDouble("2.4");
		
		int totalInputMoney = inputMoney * period;
		double totalInterest = totalInputMoney * interest / 100;
		double totalReceiveMoney = totalInterest + totalInputMoney;
		
		DecimalFormat df = new DecimalFormat("￦###,###,###");
	%>
	
	<h1>적금 계산</h1>
	<h2>입력받은 정보</h2>
	<p>월 급액 : <%=df.format(inputMoney) %></p>
	<p>적금 기간 : <%=period %></p>
	<p>년 이자 : <%=interest %></p>
	<hr>
	<p>총 납입 금액 : <%=df.format(totalInputMoney) %></p>
	<p> 발생 이자 : <%=df.format(totalInterest) %></p>
	<p>총 수령액 : <%=df.format(totalReceiveMoney) %></p>

## condition

	<%
		int day = 4; // 지역 변수
	%>
	
	<%
		if(day == 1 || day == 7) {
	%>
		<p>오늘은 주말입니다.</p>
	<%
		} else {
	%>
		<p>오늘은 평일입니다.</p>
	<%
		}
	%>

## Implicit Object (내장 객체)
* out : JspWriter 클래스 객체. 클라이언트출력정보객체
* request : HttpServletRequest 클래스 객체. HTTP 요청(request)정보 제공
* response : HttpServletResponse 클래스 객체. HTTP 응답(response)정보 제공
* config : ServletConfig 클래스 객체. Servlet 설정 정보 제공
* application : ServletContext 클래스 객체. 애플리케이션(application) 정보 제공
* session : HttpSession 클래스 객체. HTTP 세션(Session) 정보 제공
* pageContext : PageContext 클래스 객체. 다른 내장 객체정보제공
* page : Object 클래스객체. jsp 페이지의서블릿객체
* exception : Throwable 클래스 객체. 예외처리제공
* 페이지 정보 제공 범위
![페이지 정보 제공 범위](https://github.com/user-attachments/assets/a0a17454-db5e-4812-b0da-4a28fce080b2)

## JSP parameter, attribute
* String getParameter(String name) :
* - request는 form에서 전송도니 데이터를 불러오는 parameter를 제공
* - request는 객체에 Form Data가 저장되고, getParameter()로 데이터 참조
		
*  GET 방식 url 정보
* http://localhost:8080/ : 도메인 정보
* Web06_JSP_Servlet : Context Root
* /ch12/result.jsp : 페이지 경로
* ?name=213&age=1 : query string
* parameter : name, age
		
* attribute 방식
* setAttribute(String name, Object o)
* pageContext, requser, session, application 내장 객체에는
* attribute 방식으로 데이터를 저장하거나 불러올 수 있음

* * parameter와 attribute의 차이점
* - parameter와 attribute는 비슷한 기능을 수행
* - parameter는 html form 태그를 사용하여 전송 수행
* - attribute는 setAttribute를 사용하여 전송 수행
* - parameter는 String 타입만 전송 가능
* - attribute는 request 객체에만 존재하고, client에서 server로 데이터를 전송할 때 사용
* - attribute는 주로 server 데이터를 client로 전송할 때 사용