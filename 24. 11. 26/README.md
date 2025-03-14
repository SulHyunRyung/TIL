# 2024. 11. 26

## [Session_활용_과제_프로젝트](https://github.com/SulHyunRyung/SessionControl)
* 코드 리뷰 및 로직 수정
* Session의 접근 방식이나 사용 이유에 대해 조금 더 알게된 계기였음.

## Servlet Filter

### Servlet Filter란?

* 사용자 인증이나 로깅과 같은 기능들은 모든 Servlet이나 JSP가 공통적으로 필요로 함.
* 이러한 공통적인 기능들을 Servlet이 호출되기 전에 수행(전처리)되게 하고 싶거나
* Servlet이 호출 되고 난 뒤에 수행(후처리) 하고 싶으면 공통적인 기능들을 Servlet Filter로 구현하면 된다.

### Filter의 라이프 사이클
* Filter는 Servlet과 비슷한 라이프사이클을 가지며 생성, 초기화, Filter, 종료의 4단계로 이루어짐
* 또한 Servlet Container는 Filter 객체가 초기화 파라미터에 접근하는데 사용하는 환경설정 객체(FilterConfig)의 레퍼런스를 제공한다. 
* Servlet Container가 Filter의 init() 메소드를 호출하면 Filter 인터페이스는 바로 요청을 처리할 수 있는 상태가 된다.
* Servlet이 service() 메소드를 이용해서 요청을 처리한 것 처럼 Filter는 doFilter() 메소드를 통해서 요청을 처리한다. 
* 모든 요청에 대한 처리가 끝나면 destroy() 메소드가 호출되면서 Filter는 비활성 상태로 변경된다.

### FilterChain
* Filter는 연속적인 작용을 수행한다. Filter 객체가 수행해야 할 부분인 doFilter() 메소드로 인자로 전달되는 것이 FilterChain 객체이다. 
* FilterChain 객체는 Filter의 수행과정을 연속적으로 하기 위한 방법이다. 웹 Container가 FIlterConfig 객체와 함께 FilterChain 인터페이스를 구현한 객체를 생성한다.

### doFilter() 메소드
* 가장 핵심인 Filtering이 이루어지는 메소드이다.

![FilterExample](https://github.com/user-attachments/assets/d6b3c527-499c-450c-b77b-7911d951ca54)

* Filter는 한번만 수행되는 것이 아니라, 요청을 받을 때 수행되고 chain.doFilter()를 통해 다음 부분으로 넘겨준다. 
* 다음 모든 부분이 모두 수행되면 다시 Filter로 완전한 응답객체와 함께 제어권이 넘어오게 된다. 
* 위의 코드처럼 chain.doFilter()를 기준으로 전처리 부분과 후처리 부분으로 나누는 이유다.

​따라서 chain.doFIlter()가 doFilter() 메소드 안에 없다면 Servlet의 수행결과를 알 수 없다. 
즉 chain,doFilter() 메소드를 사용하여 다음 단계인 진짜 Servlet을 수행한 후 결과를 다시 받는 것이다.

## EL/JSTL

### EL/JSTL 란?

### EL (Expression Language)
* page, request, session, application 관련 데이터 출력 코드를 간략하게 사용할 수 있는 표기법
* 자바빈 컴퍼넌트를 쉽게 접근하게 도와줌
* 집합 객체(list, String[])에 대한 접근 방법을 제공
* 수치 연산, 관계 연산, 논리 연산자 제공
* 표현 언어만의 기본 객체 제공(범위 객체)

### EL 기본 객체
* pageContext : JSP의 page 기본 객체와 동일
* pageScope : page 기본 객체에 저장된 속성의 <속성, 값> 매핑을 저장한 Map 객체
* requestScope : request 기본 객체에 저장된 속성의 <속성, 값> 매핑을 저장한 Map 객체
* sessionScope : session 기본 객체에 저장된 속성의 <속성, 값> 매핑을 저장한 Map 객체
* applicationScope : application 기본 객체에 저장된 <속성, 값> 매핑을 저장한 Map 객체
* param : 요청 파라미터의 <파라미터이름, 값> 매핑을 저장한 Map 객체, 타입은 String (request.getParameter("파라미터이름")과 동일)
* paramValues : 요청 파라미터의 <파라미터이름, 값> 매핑을 저장한 Map 객체, 타입은 String[] (request.getParameterValues("파라미터이름")과 동일)
* cookie : <쿠키이름, 쿠키값> 매핑을 저장한 Map 객체 (request.getCookies()와 동일)
* initParam : 초기화된 파라미터의 <파라미터이름, 값> 매핑을 저장한 Map 객체 (application.getInitParameter("파라미터이름")과 동일)      

### EL 사용 예시
<%
	pageContext.setAttribute("num1", 1);
	request.setAttribute("num2", 2);
	request.setAttribute("num3", 3);
	application.setAttribute("num4", 4);
	
	// 기존 방식으로 값 출력
	int number1 = (Integer) pageContext.getAttribute("num1");
	int number2 = (Integer) request.getAttribute("num2");
%>

<h1>변수 사용</h1>
<p>JSP 변수의 합 : <%=number1 + number2 %></p>
<p>page num1 : ${num1 }</p>
<p>request num2 : ${num2 }</p>
<p>session num3 : ${num3 }</p>
<p>application num4 : ${num4 }</p> 

<%-- param.(파라미터이름) == request.getParameter(파라미터이름) --%>

### JSTL (JSP Standard Tag Library)
* JSP 태그의 경우 HTML 태그의 가독성을 떨어트림
* 이를 해결하기 위해 JSTL을 사용
* JSP의 다양한 커스텀 태그를 모아놓은 라이브러리
* 반복, 조건, SQL 등 다양한 기능을 사용

### JSTL Tag
* core : 변수 지원, 흐름 제어, URL 처리 등. 접두어 * c
* xml : xml 코어, 흐름 제어, xml 변환. 접두어 * x
* 데이터베이스 : SQL. 접두어 * SQL.

### JSTL 라이브러리 추가
* http://tomcat.apache.org/taglibs/standard/ 접속
* version standard 1.1 Getting the Taglib download 링크 클릭
* binaries/jakarta*taglibs*standard*1.1.2.zip 다운로드
* jakarta*taglib*standard*1.1.2/lib 경로의 jstl.jar, 
* standard.jar 파일을 해당 프로젝트의 WEB*INF/lib 폴더에 저장
* 필요한 taglib을 uri로 지정

### c:out (prefix="c")
* 변수의 값을 출력해준다.

### c:set (prefix="c")
* 변수에 값을 저장한다. setAttriubte 와 동일한 역할을 한다.

### c:forEach (prefix="c")
* 범위안에서 반복문을 수행한다. for와 동일한 역할을 한다.
* begin : 시작 값, end : 마지막 값 , step : 증가 단 다음 소스는 i라는 변수를 사용하여 
* 1부터 10까지 1씩 증가하며 반복문을 수행한다.

### c:if (prefix="c")
* 조건문을 수행한다. if와 동일한 역할을 한다.

### c:choose / c:when (prefix="c")
* if, else 구문 역할을 한다.

### c:url (prefix="c")
* url 주소를 생성해준다.

### c:include (prefix="c")
* JSP에서 페이지 include와 같은 역할을 한다.