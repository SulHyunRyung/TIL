# 24. 12. 16

## Team Project (Eat Platfrom) 진행

## Team Project 회의진행

## 리뷰 추천/ 신고 기능 추가 논의

### [REVIEW_REPORT_LIST] - 리뷰 신고

REVIEW_ID NUMBER
USER_ID VARCHAR2(100)
REPORT_MESSAGE VARCHAR(1000)

### [REVIEW_LIKE_LIST] - 리뷰 추천

REVIEW_ID NUMBER
USER_ID VARCHAR2(100)

* 해당 컨텐츠를 위한 테이블 생성

## 메인 페이지 구성 논의

[Main](https://github.com/hsukim001/eatPlatform-springLegacy/tree/main/Case)


## Team Project (Eat Platfrom) 진행

* // 표시는 끝난 작업을 의미
* - 는 진행 중을 표시

1) main(index) 구성 - (Case.folder)
* 차후 진행될 페이지 프론트단 선 구현 진행

- 공용 common.css 구성
- Common 요소인 Header/Footer 구현, 반응형 스타일 추가
- recommend (추천 리뷰 배너) JS, CSS 구현, 반응형 스타일 추가



### 페이지 구성
1) 식당 정보 등록 /store/register //
- 등록 정보 입력 /store/newStore // (프론트 진행 전)
2) 식당 정보 수정 /store/modify
3) 식당 정보 삭제 store/delete
4) 검색 -- 차후 작업