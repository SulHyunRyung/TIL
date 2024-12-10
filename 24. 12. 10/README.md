# 24. 12. 10

## Transaction

* 트랜잭션은  데이터베이스에 대한 연산의 집합(예: INSERT, UPDATE, DELETE)을 논리적으로 단일 작업 단위로 묶어서 처리하는 것을 의미
* 다중 사용자 및 동적 환경에서 데이터 무결성과 일관성을 보장하는 데이터베이스 시스템에서 트랜잭션이 필수.
* 주요 개념
1) 커밋(Commit):  트랜잭션 내의 모든 작업이 성공적으로 완료되었음을 나타내는 작업을 의미
2) 롤백(Rollback) : 트랜잭션을 취소하고 이전 상태로 되돌리는 작업을 의미

## ACID(Atomicity, Consistency, Isolation, Durability) 특징
* 원자성(Atomicity): 트랜잭션 내의 모든 작업이 단일 단위로 실행되도록 보장. 트랜잭션 내의 작업이 실패하면 전체 트랜잭션이 롤백(Rollback)되고 트랜잭션에 의한 모든 변경 사항이 취소되어 데이터베이스는 원래 상태로 유지
* 일관성(Consistency): 트랜잭션 전후에 데이터베이스가 일관된 상태로 유지되도록 보장. 이는 외래 키 제약 조건, 고유 제약 조건 등 모든 무결성 제약 조건이 유지되고 데이터가 모든 유효성 검사 규칙을 만족한다는 의미
* 격리(Isolation): 트랜잭션의 중간 상태가 커밋(Commit)될 때까지 다른 트랜잭션에 표시되지 않도록 수행. 격리는 동시 트랜잭션이 서로 간섭하는 것을 방지하여 데이터 무결성을 유지하고 경합 상태를 방지
* 내구성(Durability): 시스템 오류, 충돌 또는 정전이 발생하는 경우에도 커밋된 트랜잭션에 의해 변경된 내용이 유지되도록 보장. 트랜잭션이 커밋되면 해당 변경 사항은 데이터베이스에 영구적으로 저장되며 손실될 수 없음

### Transaction 동작 방식 예시

![Transaction](https://github.com/user-attachments/assets/85e88526-c92b-42f8-8808-4d04758977ea)

### Transaction 사용 예시
* 예시 - 계좌 이체
1) 이체 요청: 고객이 A 계좌에서 B 계좌로 자금 이체를 요청
2) 계좌 차감: A 계좌에서는 이체 금액이 차감
3) 계좌 입금: B 계좌에는 이체 금액이 입금
4) 프로세스 완료: 모든 단계가 성공적으로 완료되면 트랜잭션은 성공적으로 완료되었음을 보장
5) 문제 발생 : A 계좌 이체 금액이 차감되었지만, B 계좌에 입금이 실패한 경우, 계좌간 데이터 불일치를 초래

## @Transactional
* JDBC에서 개발자가 트랜잭션 관리를 위한 코드를 작성 가능
* 스프링은 @Transactional로 간단하게 트랜잭션 관리가 가능
* AOP를 사용하는 방식
* 클래스, 메서드, 인터페이스에서 설정 가능하나 일반적으로 비즈니스 계층의 메서드에 설정
* 여러 속성을 이용하여 정밀하게 관리 가능

### Transaction 주요 속성

![TransactionAttribute](https://github.com/user-attachments/assets/2cfe0b00-7742-4516-8af3-58f939b11700)

## Spring security
* Spring security는 인증, 권한 부여 및 취약점 공격 방지를 제공하는 프레임워크
* Spring security는 Servlet Filter를 기반으로 동작
* 인증 기능은 AuthenticationManagerBuilder를 객체를 사용하여 설정 
* 보안 기능은 HttpSecurity 객체를 사용하여 설정

### Spring security 구현 순서
1) 스프링 프로젝트 생성
2) pom.xml 설정 : 스프링 시큐리티 모듈 설정
3) Java Configuration 구성 : RootConfg.java, ServletConfig.java, WebConfig.java 구현
4) Security 설정 : SecurityInitializer.java, SecurityConfig.java 구현



## AuthenticationManagerBuilder
* inMemoryAuthentication(): 메모리 기반의 사용자 인증을 설정하는 메서드. 애플리케이션 메모리에 사용자 인증 및 보안을 설정할 수 있음
* jdbcAuthentication(): JDBC를 사용하여 데이터베이스를 통해 사용자 인증을 설정하는 메서드. 데이터베이스에 저장된 사용자 정보를 사용하여 인증을 처리할 수 있음
* userDetailsService(): UserDetailsService 인터페이스를 구현한 사용자 정의 서비스를 제공하여 사용자 인증을 설정하는 메서드.
* eraseCredentials(): 인증 이후에 사용자 자격 정보를 삭제해 보안을 강화하는 메서드.

## 취약점 공격

### CSRF(Cross-Site Request Forgery, 크로스 사이트 요청 위조)
* 특정 웹 사이트가 사용자의 요청을 신뢰하는 상태를 이용한 공격

### CSRF 예시
![CSRFExample](https://github.com/user-attachments/assets/fd5bf085-ed28-46b3-9627-c803c2df96fe)


### XSS(Cross-Site Scripting)
* 특정 사용자가 웹 사이트를 신뢰하는 상태를 이용한 공격
* 공격자가 웹 사이트에 스크립트를 직접 삽입하여 공격
* 데이터 입력 form이나 URL 파라미터를 통해 공격
* 사용자의 세션이나 쿠키를 탈취하여 개인정보를 유출할 수 있으며, 사용자에게 악의적인 행동을 유도할 수도 있음


### 방어 기능 구현 순서
* CSRF 공격 보호 기능 구성 : SecurityConfig.java, login.jsp, admin.jsp
* XSS 공격 보호 기능 설정 : send.jsp, output.jsp, ExampleController.java SecurityConfig.java