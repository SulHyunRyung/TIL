# 2024. 11. 21

## Session 활용 과제 프로젝트 진행

![Session_활용_과제_프로젝트](https://github.com/SulHyunRyung/SessionControl)

# Session 활용 데이터 전달 활용 프로젝트
* Session을 활용하여, 회원 정보를 저장 및 활용을 연습하는 미니 프로젝트
* 회원가입, 로그인, 회원 정보 조회, 회원 정보 수정, 회원 탈퇴(비활성) 기능 구현
* JSP와 Servlet을 활용하여 구성

## Register

![Register](https://github.com/user-attachments/assets/cb92713a-8cf2-45b8-9e38-c557d2c44276)
![RegisterRequired](https://github.com/user-attachments/assets/a4c4743e-7c0e-4612-a90d-79b641486fbf)

* login.jsp에서 회원 가입 버튼을 통해 이동 가능
* 기본적으로 모든 필드(emailAgree 항목 제외)에 required 속성으로 빈 필드를 방지.

![RegisterProcess1](https://github.com/user-attachments/assets/8fcad524-ecc8-4d64-8653-59a49b29fd76)
![RegisterProcess2](https://github.com/user-attachments/assets/3e0d203c-c3f0-42ba-b8e8-38a369328e2c)
![RegisterProcess3](https://github.com/user-attachments/assets/be123e0b-c710-4410-877b-fdc4019e4b60)
![RegisterProcess4](https://github.com/user-attachments/assets/6017f671-4082-4dc3-8037-b85436d9a32c)
![RegisterProcess5](https://github.com/user-attachments/assets/3980dc69-6400-49cb-befc-5509fb1bcd89)

* 해당 폼에서 알맞은 정보를 기입한 후 가입 버튼을 누르면 RegisterServlet으로 이동하여 작업을 진행.
* 우선 해당 Servlet을 정식 루트가 아닌 잘못된 접근 시도 및 로그인된 상태로 접근 시도 시 차단 처리.
* 이후 입력한 정보를 파라미터에서 가져옴. emailAgree 같은 경우만 체크를 하지 않으면 'N'으로 수동 지정.
* 관심사항(interest)의 경우는 다수를 선택할 수 있기에 다른 값과 다르게 배열로 받아옴
* 입력한 id를 토대로 selectByUesrId 쿼리를 사용하여 DB에서 비교하여 동일한 id로 가입 시도 시 차단
* 이전 탈퇴 회원 id를 사용 시도 시 예외로 탈퇴 회원 알림 문구 출력.
* 필드의 readonly나 required 강제로 해제한 후 진행하는 것을 방지 하기 위해 양식이 맞지 않으면 차단.
* 계정 활성화 상태(status)는 임의로 'active'로 삽입, 이외는 필드에 입력된 값을 삽입.

## Login

![Login](https://github.com/user-attachments/assets/b8371860-763f-47da-b514-8cc4413af137)

* 홈페이지의 시작점인 로그인 페이지, 비정상적인 접속을 통해 (세션이 없거나 유효하지 않으면)은 해당 페이지로 돌아옴

![LoginRequired](https://github.com/user-attachments/assets/3104ef0b-b72f-4de8-b8ee-437958b2c674)

* 기본적으로 ID, PW는 필수사항으로 미기재 시 로그인 기능이 활성화 되지 않음.

![LoginProcess1](https://github.com/user-attachments/assets/2942a8b1-85b0-4adb-815b-be0a58f22bc1)
![LoginProcess2](https://github.com/user-attachments/assets/e2fbc376-3c5c-4030-9516-f33b1c64b2ec)
![LoginProcess3](https://github.com/user-attachments/assets/3103e29d-d666-4018-8664-ee514b43001e)
![LoginProcess4](https://github.com/user-attachments/assets/f364adc3-d76e-433b-8b4f-37079986470a)

* 첨부 이미지처럼 로그인을 시도하면 LoginServlet으로 이동하여 작업을 진행.
* DAOImplement의 SeleteByUserId 메소드를 통해 입력한 userid를 통해 DB에서 userid, password를 서치를 실행
* 그 후 Servlet의 doPost에서 입력한 id, pw를 파라미터로 얻은 후 해당 메소드를 실행해 데이터를 비교
* id와 pw가 일치하면 userid로 세션을 생성한 후 alert으로 메세지 출력 후 main.jsp로 이동함
* id나 pw가 일치하지 않는다면 로그인 실패 alert를 출력한 후 이전 페이지(login.jsp)로 다시 이동.
* 로그인에 성공하면 userid의 세션을 60초 동안 부여


![InactiveMemeber](https://github.com/user-attachments/assets/fc8d8eba-7adf-4097-bc87-ae9ae3caa741)

* 추후 탈퇴 기능을 설명하겠지만, 기본적으로 탈퇴(비활성화)된 회원은 로그인 단계에서 세션을 생성하지 않음.

## Main

![Main](https://github.com/user-attachments/assets/7c037a69-2325-4e7d-a215-54efe742002e)
![Main2](https://github.com/user-attachments/assets/8fb2aaf9-6b72-4bda-8338-323905340b8d)

* 로그인에 성공하여 들어오면 해당 이미지처럼 세션에서 정보를 얻어와 출력함.
* 유효한 세션이 아닌 경우 (잘못된 형식의 접근)을 시도하면 alert 출력 후 login.jsp로 강제 이동

## Update

![UpdateAccount1](https://github.com/user-attachments/assets/642a206e-bc56-46d0-9d72-8a659c6e484a)
![UpdateAccount2](https://github.com/user-attachments/assets/6165df73-016b-4817-a7f8-dff7f3fbcb42)
![UpdateAccount3](https://github.com/user-attachments/assets/b1317fb5-4123-4663-a58a-b05619af5cc9)
![UpdateAccount4](https://github.com/user-attachments/assets/eb707ab7-2fe6-41fc-8f81-35b96f05c044)

* main.jsp의 회원 정보 수정 버튼을 통해 이동 가능.
* 잘못된 형식의 접근으로 접속 시 차단 기능 구현
* 회원 가입과 유사하게 강제로 속성을 바꿔서 수정을 시도하는 접근을 차단 처리
* 알맞은 정보를 입력하면 updateAccount 메소드를 사용하여 DB에 정보를 업데이트
* 여기서도 interest 항목만 배열로 값을 얻어오고 전달 되도록 처리

## Logout

![Logout](https://github.com/user-attachments/assets/226579d1-6ce2-40f7-a4f7-189c352b8c41)

* main.jsp에서 로그아웃을 선택하여 해당 작업 진행.
* 세션이 있을 경우 (일반적인 경우) 세션을 제거하고 alert 출력 후 login.jsp로 이동
* 세션이 없을 경우 (비정상적인 접근) 잘못된 접근을 알리는 alert 출력 후 login.jsp로 이동

## DeleteAccount

![DeleteAccount](https://github.com/user-attachments/assets/bdf3efae-298c-43e8-beb1-8ec2b8893b2c)
![DeleteAccount2](https://github.com/user-attachments/assets/411182f0-0fe3-45df-a12a-288e033aa1b2)
![DeleteAccount3](https://github.com/user-attachments/assets/99fc0889-85f3-401f-a2e2-8b8e636d3707)
![DeleteAccount4](https://github.com/user-attachments/assets/fd1e73c2-1ca1-4f13-b5aa-8cad04898b3b)

* main.jsp에서 회원 탈퇴 선택하면 해당 작업 진행.
* 다른 페이지와 마찬가지로 비정상적인 접근을 차단하는 기능 구현
* 세션의 userid로 SELETE 쿼리를 실행하여 DB에 접근
* DELETE 문을 사용하는게 아닌 STATUS 컬럼 값을 inactive(비활성)으로 변경하여 기존 데이터는 유지
* 이후 STATUS inactive인 계정은 로그인이 선행되는 활동을 제한(회원 가입 포함)

## Filter

![Filter](https://github.com/user-attachments/assets/ad589044-4c9f-4d66-b1b8-594bc1965a03)

* Filter java 파일을 생성하여 DB로 데이터가 오가는 페이지들에 'UTF-8' 설정

# HTML LayerPopup

Session을 활용한 미니 프로젝트 진행 중,
같이 진행하는 동료분이 JS의 alert의 타이틀 부분을 변경하고 싶다고 해서
단순하게 제작한 레이어 팝업 결과물.
이전에 프론트엔드 업무를 진행할 때도 종종 사용했던 기억이 있어
정형화, 정리하여 파일화 하기로 함.

[[ 해당 작업물 파일 ]](https://github.com/SulHyunRyung/Personal-Library/tree/main/layerPopup)

![LayerPopup](https://github.com/user-attachments/assets/488233dc-3f45-4e1f-babc-a3e3279c1e00)
![description](https://github.com/user-attachments/assets/d4233aff-61d5-44d3-9b77-81d4f9e2a97a)

* 다른 사용자를 위한 img 설명서 첨부.


