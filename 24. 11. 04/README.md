# 2024. 11. 04 TIL

## 1. Side Project - Inside

사이드프로젝트 Inside 프로그램 제작 진행 중.
* UserInfoUpdate(회원 정보 수정) 창 GUI 구현 및 기능 구현


## 2. 비밀번호 보안 / Hashing
Side Project [ Inside ] 진행 중,

MEMBER_LIST 테이블 관리 중 PW 관련해서 보안 강화의 목적으로 암호화가 필요함.

[[# 참고]](https://kogle.tistory.com/89)

해당 참고 글을 참고하여 Hashing 에 대한 정보를 얻음.

Hashing 된 값을 변환, 출력하는데 문제가 없지만, 테이블에 삽입하는 과정에서 에러 발생.

https://github.com/SulHyunRyung/TIL/issues/6

해당 Issue 와 연결하여 해결 완료.

## 3. Issue. Password Hashing 중 데이터 삽입 오류
Side Project [ Inside ] 진행 중,

USER_PW를 해싱하여 정보를 암호화 하는데 있어서 변환 및 출력이 가능함을 확인했지만,

해당 테이블 컬럼에 데이터 삽입이 안되는 현상을 발견함.

[[# 참고]](https://kogle.tistory.com/89)

해당 참고 글에서 언급한 hash된 값의 길이는 60글자 고정이라는 문구를 확인.

USER_PW의 DateType을 VARCHAR2(60)으로 변경 후 해결 완료.

## 4. BoardFrame GUI 구성 중 한글 폰트 깨짐 현상
Side Project [ Inside ] 진행 중,

![BoardTitleError](https://github.com/user-attachments/assets/16b2d8ea-9674-431c-95ba-9083a161edfb)

해당 이미지처럼 상단 타이틀 부분에서 한글 폰트가 깨져서 출력되는 오류 발생.
[[ #참고 ]](https://m.blog.naver.com/sulin00/222169913234)

![BoardTitleErrorCode](https://github.com/user-attachments/assets/09a7de45-279b-4868-8082-b0fc724ae5e7)

[Try 1.] 해당 참고글을 토대로 해당 타이틀 레이블의 코드를 확인하여 Arial 폰트인 것을 확인 후에 굴림 폰트로 변경 시도.

![BoardTitleErrorSucsess](https://github.com/user-attachments/assets/fce8374c-7661-4cbf-be48-dba722630c7d)

[Try 1.] - Result 

정상적으로 레이블이 출력됨을 확인.
