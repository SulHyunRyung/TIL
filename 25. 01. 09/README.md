# 25. 01. 09

## Team Project(Eat Platform) 진행

## Spring Security 적용

* 기존 Session 인증 방식에서 Spirng Security를 통한 인증으로 구조 변경.
* 그에 따른 Session 방식으로 데이터를 가져오거나, 인증하는 페이지의 코드 수정.

1) newStore / register (insert)

- 세션, DB의 UserId를 비교하여 본인 검증 절차를 Spring Security의 인증 ID + DB ID 비교 방식으로 변경
- 기존 세션에서 UserId의 값을 가져와 JSP 페이지에서 해당 데이터를 삽입하는 부분 제거

2) updateStore / modify (update)

- 세션, DB의 UserId를 비교하여 본인 검증 절차를 Spring Security의 인증 ID + DB ID 비교 방식으로 변경


3) newMenu / register (insert)

- 파라미터의 StoreId를 통해 검색한 UserId와 세션의 ID를 비교하는 검증을 세션 부분을 Spring Security 방식으로 변경


## 주소 정보 입력 기능 추가

* newStore에서 신규 매장 등록 시 주소 데이터를 입력받도록 구현
* daumPost API 사용