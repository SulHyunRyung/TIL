# 24. 12. 06

## Spring 활용 실습 (Spring02, Sprint03)

* MockMvc (get, post)
* Service & Controller
*       ↕ = @RestController
* @RequestBody
1) HTTP 요청으로 전송된 JSON 데이터를 Java 객체로 변환해주는 스프링 어노테이션
2) Controller 메서드 상단에 지정하면 자동으로 매개변수에 데이터 바인딩(data-binding)
3) 주로 REST API에서 클라이언트가 서버에 보낸 데이터를 수신하고 처리하는데 사용

* @ResponseBody
1) Java 객체를 JSON 데이터로 변환해주는 스프링 어노테이션 
2) Controller 메서드 상단에 지정하면 JSP를 리턴하는 방식에서 JSON 데이터를 리턴하는 방식으로 변경됨
3) 주로 REST API에서 서버가 클라이언트에 JSON 데이터를 수신할 때 사용

* @RestController
1) 해당 클래스가 JSON 데이터로 메서드 값을 리턴하도록 지정하는 스프링 어노테이션
2) @ResponseBody를 사용하지 않아도 메소드가 자동으로 JSON 데이터 반환
3) 해당 클래스를 RESTful 하게 사용할 때 선언

* ResponseEntity
* PathVariable


## REST API
* REST API는 웹 서비스 간에 통신을 위한 소프트웨어 인터페이스
* 다양한 형태의 클라이언트 및 서버 간의 통신을 단순화
1) 웹 브라우저 - 서버 간 통신
2) 앱 애플리케이션 - 서버 간 통신
* 표준 HTTP 메서드와 상태 코드를 사용하여 통신하는 설계 스타일

### REST API의 특징
* Resource : 백엔드 서버의 비즈니스 로직을 제공하는 각각의 개별적인 자원이나 엔터티(Entity)를 표현. 이 리소스는 일반적으로 서버가 관리하고 클라이언트가 조작할 수 있는 데이터의 단위
* URI (Uniform Resource Identifier) : 리소스를 식별하고 위치(URL) 또는 이름(URN)을 나타내는 표준화된 방법을 제공. 인터넷 상에서 리소스를 유일하게 식별하는 용도.
* 표준 HTTP 메서드 사용 : REST API는 표준 HTTP 메서드를 사용하여 리소스에 대해 다양한 작업을 수행
1) GET: 리소스를 조회
2) POST: 리소스를 생성
3) PUT: 리소스를 수정
4) DELETE: 리소스를 제거
* 데이터 송수신 : JSON 또는 XML과 같은 다양한 형태의 리소스를 전송할 수 있음.
* 데이터는 클라이언트에서 비동기 방식으로 화면에 출력

## JSON(JavaScript Object Notation)
* 자바스크립트 기반 경량 데이터 교환 형식
* 생성 및 변환이 쉬운 데이터 형식 
* 텍스트 형태로 전송되며 여러 시스템에서 호환 가능
* 많은 프로그래밍 언어에서 JSON을 다루는 라이브러리 제공 

### JSON의 특징
* 간결하고 가독성이 좋음: 텍스트 기반으로 구조화되어 있어 사람이 쉽게 읽고 쓸 수 있음
* 키-값 쌍(key-value pair): 데이터는 키-값 쌍으로 이루어져 있으며, 키는 문자열이고 값은 다양한 데이터 타입을 지원
* 다양한 데이터 타입 지원: 문자열, 숫자, 불리언, 배열, 객체(다른 JSON 객체), null 등 다양한 데이터 유형을 지원
* 계층 구조: JSON 객체 안에 또다른 JSON 객체나 배열을 중첩하여 사용할 수 있음


### 학습 정리

* REST(Representational State Transfer) API는 웹 서비스 간에 통신하기 위한 소프트웨어 인터페이스
* REST API는 다양한 클라이언트 및 서버 간의 통신을 단순화
* 데이터 통신은 JSON 형식으로 수행
* JSON은 자바스크립트 기반 경량 데이터 교환 형식
* JSON은 전송할 때 데이터를 문자열 타입으로 변경하여 전송해야 함
* Spring MVC는 jackson-databind를 사용하여 JSON을 바인딩
* 클라이언트에서 서버로 전송되는 JSON 데이터 바인딩은 @RequestBody를 사용
* 서버에서 클라이언트로 전송되는 JSON 데이터 바인딩은 @ResponseBody를 사용
