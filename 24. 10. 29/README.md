# 2024. 10. 29 TIL


 # Issue 1.
 
Side Project 1 (INSIDE) 프로젝트 진행 중
USER 식별 코드인 UID의 원하는 구성 ( ex : U00001 ) 형태처럼

특정 자릿수 + 문자열을 포함하려고 구현하던 중 이슈 발생.


![try_1](https://github.com/user-attachments/assets/e895d538-d4db-478f-9384-1cd11973c123)

Try 1. 이미지처럼 시퀀스 생성부터 자릿수 지정.
* ER1 시스템 상으로 구현되지 않은 기능.
 
이후 해당 기능 명령어 탐색 중 LPAD&RPAD 및 || (CONCAT 함수) 명령어 발견.

[[참고]](https://ajdahrdl.tistory.com/152) / [[참고2]](https://okky.kr/questions/357425)


![try_2](https://github.com/user-attachments/assets/bb55cf2f-af5d-47f6-9b74-70493baadeeb)

Try 2. 링크의 참고글의 내용을 사용하여 TEST 테이블, 시퀀스를 생성해서 시도

![try_2_result](https://github.com/user-attachments/assets/cf2d28d5-a202-4f2f-b304-ef1e37531601)

Try 2 - Result 

원하는 값을 출력함.

해당 이슈 풀이과정 ) https://github.com/SulHyunRyung/TIL/issues/1

# Issue 2.

TIL 작성 중 기존 작성 방식이 너무 과정이 복잡하고 불편함을 느낌.

![origin](https://github.com/user-attachments/assets/38b7f1c6-fe52-41af-ac33-c0553e7bde8c)

기존 작성 방식은 이슈 발생 시 첨부 이미지처럼 해당 이슈의 대한 이미지를 따고,

PhotoShop 프로그램을 사용해서 이미지 파일로 기록 및 저장하는 시스템.

개선 방안에 대해 알아보던 중 Git Repository의 ReadMe 및 Issue 기능을 활용해보기 위해 알아봄.

[[참고]](https://lsh424.tistory.com/37) / [[참고2]](https://worthpreading.tistory.com/83)

첨부된 링크의 참고글을 활용하여 기존 방식에서 Issue 게시글 등록 후 수정/코멘트 추가 형식으로 방식을 수정함.

후에 해당 날짜 ReadMe에 종합하여 업로드 및 날짜별 Log.txt 파일에 간략하게 기록하는 방식을 적용.

![example](https://github.com/user-attachments/assets/7593ec90-4e80-40a7-92bc-d536d7303fec)



첫 적용 예시 ) [https://github.com/SulHyunRyung/TIL/issues/1](url)

최종적으로 기존 PhotoShop 작업 방식보다 작업 과정을 줄일 수 있었음.

추가적으로 가시성이 올라가 추후 확인용으로도 상대적으로 만족스러운 결과라고 생각함.


# 3 Side Project - Inside

사이드 프로젝트 Inside 프로그램 제작 진행 중.
* 프로젝트 Git Setting
* Logo 제작
* SRS 구성 및 세부사항 조정
* 각 기능 테이블 구성

해당 프로젝트 진행 현황 ) [[프로젝트 진행 현황]](https://github.com/SulHyunRyung/INSIDE/tree/main)


