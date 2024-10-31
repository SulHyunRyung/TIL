# 2024. 10. 31 TIL

## 1. Side Project - Inside

사이드프로젝트 Inside 프로그램 제작 진행 중.
* 기능 구현을 위한 DAO 및 DAOImpl 의 메서드 작성
* 사용되는 테이블의 유효성 체크 후 구조 변경
해당 프로젝트 진행 현황 ) [[프로젝트 진행 현황]](https://github.com/SulHyunRyung/INSIDE/tree/main)

## 2. 기능 메서드의 트랜잭션 처리

Side Project [ Inside ] 진행 중,

각 기능의 메서드들이 동시 다발성으로 작동 될 경우 에러가 있을 가능성을 확인.

추후 Chat GPT, 서칭을 통해 Java에서 쿼리문을 이용하면 자동으로 커밋이 되는 것을 확인 후,


![transaction1](https://github.com/user-attachments/assets/22391196-c12a-4330-88a9-0ed32502fd31)

자동 커밋 해제 및 예외 사항 시 롤백을 진행할 수 있도록 기능들의 메서드를 수정을 진행.

![transaction2](https://github.com/user-attachments/assets/cd8236cf-8cff-431c-8c01-a154369c5c7d)

첨부된 이미지처럼 코드를 수정하여, 각 기능마다 수동으로 커밋하여 각 기능이 실행될 때 마다

일정한 브레이크를 걸어서 우선순위와 유사한 형태를 부여할 수 있도록 진행함.

Result. 기능이 실행될 때 마다 순차적으로 커밋이 되기에 동시 다발성 작동의 트러블을 줄일 수 있었음.




