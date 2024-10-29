# 2024. 10. 29 TIL
 
Side Project 1 (INSIDE) 프로젝트 진행 중
USER 식별 코드인 UID의 원하는 구성 ( ex : U00001 ) 형태처럼
특정 자릿수 + 문자열을 포함하려고 구현하던 중 이슈 발생.


![try_1](https://github.com/user-attachments/assets/e895d538-d4db-478f-9384-1cd11973c123)

Try 1. 이미지처럼 시퀀스 생성부터 자릿수 지정.
* ER1 시스템 상으로 구현되지 않은 기능.
 
이후 해당 기능 명령어 탐색 중 LPAD&RPAD 및 || (CONCAT 함수) 명령어 발견.
#참고 [https://ajdahrdl.tistory.com/152](url)
#참고2 [https://okky.kr/questions/357425](url)


![try_2](https://github.com/user-attachments/assets/bb55cf2f-af5d-47f6-9b74-70493baadeeb)

Try 2. 링크의 참고글의 내용을 사용하여 TEST 테이블, 시퀀스를 생성해서 시도

![try_2_result](https://github.com/user-attachments/assets/cf2d28d5-a202-4f2f-b304-ef1e37531601)

Try 2 - Result 

원하는 값을 출력함.

해당 이슈 풀이과정 ) https://github.com/SulHyunRyung/TIL/issues/1

