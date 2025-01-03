# 2024. 11. 19

## Servlet 기초

* Servlet은 서버사이드기능을수행하고, JSP는클라이언트사이드기능을수행
* Servlet 클래스는클라이언트요청을DB에전송하고, 응답을클라이언트에전송하는
컨트롤러역할을수행
* Servlet은 HttpServlet, HttpServletRequest, HttpServletResponse 클래스를 제공
* Servlet 클래스URL 매핑방법은web.xml 파일설정과@WebServlet 설정두가지
* ServletContainer는 클라이언트에서Servlet 클래스호출시객체를생성
* 생성된Servlet 객체는재사용
* service()는 HTTP 요청에대해응답하는메서드
* service()는 doGet()/doPost()로 대체 가능
* destroy()는 서버가정상종료될때호출

## Cookie

## 동작방식
* 최초 접속 시 클라이언트에서 서버로 요청 발생
* 서버에서 쿠키를 생성하고 식별키와 사용자 데이터 저장(만료시간설정가능) 
* 응답 헤더(HTTP Set-Cookie)에 쿠키 전송
* 클라이언트에 쿠키 저장
* 재접속 시 같은 도메인 요청에 클라이언트 내에저 장된쿠키를 서버로 전송
* 식별키(JSESSIONID)를 이용하여 동일한 클라이언트인지 확인
* 식별 후 모든 클라이언트 요청(request)  에서 쿠키에 저장된 사용자 데이터 출력 가능(만료전까지)
* Cookie Process 참고 이미지
![Cookie Process](https://github.com/user-attachments/assets/d2451989-df29-4465-887e-4d98c4606269)

## 구성 요소
이름(Name) : 쿠키를 인식하기 위한 이름
* 값(Value) : 쿠키에 저장할 값
* 도메인(Domain) : 쿠키가 저장된 도메인
* 경로(Path) : 쿠키가 저장된 경로(Context Root)
* 만료시간(Expires/Max-Age) : 설정된 쿠키 만료 시간

## 장단점
* 장점: 서버 저장 공간이 절약됨
* 단점: 클라이언트에서 데이터 확인 가능-> 보안 취약


## Session

## 동작 방식
* 최초 접속 시 클라이언트에서 서버로 요청 발생
* 서버에서 쿠키를 생성하고 식별키를 저장
* 사용자 데이터를 서버에 저장 (HttpSession 객체 저장)
* 응답 헤더(HTTP Set-Cookie)에쿠키전송
* 클라이언트에 쿠키 저장
* 재접속 시 같은 도메인 요청에 클라이언트 내에 저장된 쿠키를 서버로 전송
* 식별키(JSESSIONID)를 이용하여 동일한 클라이언트인지 확인
* 클라이언트 실행 중 또는 만료되기 전까지 사용자 데이터를 출력가능(JSP는 session 내장 객체에서 데이터참조)

## 구성 요소
* void setAttribute(String name, Object value) : attribute 데이터 저장
* Object getAttribute(String name) : attribute 데이터 출력
* void removeAttribute(String name) : attribute 데이터 삭제
* void setMaxInactiveInterval(int interval) : session 만료 시간 설정(초 단위)
* void invalidate() : session 객체 무효화

## 장단점
* 장점: 쿠키에 비해 보안이 강화됨
* 단점: 사용자가 많을 수록 서버 부담 증가

## 정리
* HTTP는 상태 정보가 유지 되지 않는 무상태 프로토콜(Stateless Protocol)
* 상태 유지(Stateful)를 위해서는 클라이언트와 서버가 서로 식별할 수 있어야함
* 서버가 클라이언트를 식별하기 위해 JSESSIONID 식별키를 사용
* 쿠키(Cookie)는 클라이언트에 데이터를 저장하여 상태 정보를 유지하는 방식
* 세션(Session)은 서버에 데이터를 저장하여 상태정보를 유지하는 방식
* 쿠키는 보안에 취약하지만, 서버공간을 절약할 수 있음
* 세션은 서버 공간에 부담이 증가 하지만, 보안이 강화됨