# 24. 12. 18

## Team Project (Eat Platform) 진행

### View 페이지 구성을 위한 레퍼런스 조사

* [Referance1] (https://www.siksinhot.com/)
* [Referance2] (https://www.diningcode.com/)
* [Referance3] (https://www.bluer.co.kr/)

* 1번 레퍼런스에서 전체적인 구조의 배치를 참고.
* 2번 레퍼런스에서 뷰 페이지의 구성 기능을 참고
* 3번 레퍼런스에서 카드형 배너의 아이디어를 참고.

    index (메인 페이지)

    [index](https://sulhyunryung.github.io/Case/)

    * best (별점 랭킹) 구성, 반응형 스타일 추가
    * category (카테고리별 랭킹) 구성, 반응형 스타일 추가

    ### 가게 정보 수정 페이지 구현

    * 수정 정보 입력 페이지(updateStore)에서 데이터를 POST 타입으로 기능을 처리하는 modify 페이지로 전달
    * Session에 들어있는 userId와 정보를 수정하려는 글의 userId가 다를 경우 차단 기능 추가.

## Team Project (Eat Platform) 회의록

### 예약 기능에 시간 별 예약을 처리하는 방법.
* DB에 저장된 날짜, 예약으로 SELECT COUNT를 구한 후, 최대 예약 제한과 비교
* REST API로 실시간으로 해당 값을 비교 후 해당 시간대 예약 컨트롤

