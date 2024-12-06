# 24. 12. 05

## Mybatis
* 사용자 정의 SQL, Procedure 등에 대한 Mapping 기능을 제공하는 Persistance Framework

### MyBatis 기능
* JDBC 코드 생략
* 매개변수 자동 적용
* 검색 결과 자동 적용

### 웹 에플리케이션 프로젝트 구성 예시
![WebApplicationProfectStructure](https://github.com/user-attachments/assets/766921cf-210b-4c30-85e3-9cba2078991d)

## Layered Architecture
* 계층화 구조(Layered Architecture)는 각 계층이 애플리케이션 내에서 특정한 역할을 수행하도록 분리 및 조직화되어 있는 구조
* 계층화 구조는 코드의 독립성을 높이기 때문에, 코드 재사용과 유지보수, 테스트에 장점이 있음
* Presentation Layer(표현 계층)는 모든 사용자 인터페이스(JSP 등) 및 브라우저 통신 로직을 처리하는 역할
* Business Layer(비즈니스 계층)는 요청과 관련된 특정 비즈니스 로직을 실행하는 역할
* Persistence Layer(지속성 계층)는 지속성을 갖는 DB와 연결하여 등록/검색/수정/삭제 기능을 수행하는 역할
* MyBatis는 사용자 정의 SQL, Procedure 등에 대한 매핑(Mapping) 기능을 제공하는 Persistence Framework
* 코드 모듈화 방식은 재사용 및 테스트에 용이

## Spring Framework 세팅 및 코드 작성 실습 (Spring2)
* @Bean & DataSource
* @Configuration
* @Controller
* RedirectAttributes
* log4j
* Test
* HikariCP
1) OracleDB -> JDBC -> DBCP(HikariCP) -> SqlSessionFactory(MyBatis)
* @Mapper

### Mapper.xml / Mapper interface
<pre>
* <select>, <insert>, <update>, <delete> : SQL 쿼리 정의 태그 
</pre>
* 속성 :
1) id : Mapper 인터페이스 메서드 매핑 역할.
2) resultMap : 쿼리 결과를 resultMap에 매핑. <resultMap>태그에 id를 적용
4) resultType = 쿼리 결과를 클래스에 매핑.

* <resultMap> : DB 쿼리 반환 데이터와 Entitiy(VO) 클래스의 멤버 변수 매핑을 정의하는 태그
* 속성 :
1) type : 연결할 클래스의 패키지와 이름
2) id : SQL 쿼리 정의 태그에서 인식되는 이름


* <id> : key 컬럼 매핑 태그
* <result> : 일반 컬럼 매핑 태그
* 속성 : 
1) property : Endtity(VO) 멤버 변수
2) column : DB 테이블 컬럼
