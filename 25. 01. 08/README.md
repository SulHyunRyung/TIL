# 25. 01. 07

## Team Project(Eat Platform) 진행

* 예약 관련 캘린더 세부 사항 수정 및 디버깅

1) 기존 시스템에서 영업시간이 오후 ~ 오전 즉 다음 날로 넘어가는 데이터에서 시간 선택 레이블이 나오지 않는 문제가 발생

```
ex)
영업시간 : 17:05 - 05:05
![preImg](https://github.com/user-attachments/assets/f9ccc509-2fb8-4a74-966d-7634ff9c9613)

이미지처럼 오후 -> 오전 즉 00시가 넘어가는 날은 다음날로 인식되어 달력이 출력되지 않음을 확인.

해결 방안으로는 구조의 약간을 변화를 주기로 함.
기존에는 오픈시간~마감시감까지의 타임 레이블(30분 단위)를 순차적으로 출력 했었는데,

예시의 영업시간으로는 17:30 18:00 18:30 ...
이런 상황에서는 00:00 이후는 당일인가 익일인가를 고민 후에 
출력되는 날짜는 당일 기준으로 정렬으로 변경

2025-01-07 날짜 선택 시
0:30부터 출력이 되게 하되,
05:30 ~ 16:30 즉 오픈 전 시간은 이전 영업일의 시간이 되는 개념

그리고 해당 포맷으로 변경됨에 따라 
UI 또한 오전, 오후로 구분 후에 분류 후 출력되게 변경

![changeImg](https://github.com/user-attachments/assets/1442b681-d9c5-4cca-a3c3-35467f763cc3)

위 이미지처럼 UI 및 구조 변경을 완료.
```

2) 날짜, 시간 선택 시 해당 값을 data-value로 사용할 수 있도록 설정

```
ex)
2025-01-07 오후 08:00 선택 시

data-time-value(timeValue) = 20:00
data-date-value = 2025-01-07
selectTimeHour = 20
selectTimeMinutes = 00


script에서 해당 값들을 사용할 수 있게 각각
data-time-value, data-date-value에 해당 날짜, 시간을 삽입.

* currentTime은 30분 싸이클로 생성되는 그 시간을 할당한 변수

const timeSlotText = currentTime.toLocaleTimeString([], { hour: 'numeric', minute: '2-digit', hour12: true });
* View단에 User가 보는 시간

const timeSlotText24 = currentTime.toLocaleTimeString([], { hour: 'numeric', minute: '2-digit', hour12: false });
* 데이터를 처리하기 위한 시간 데이터

const timeSlotText24Split = timeSlotText24.split(':',2);
const timeSlotText24Hour = timeSlotText24Split[0];
const timeSlotText24Minutes = timeSlotText24Split[1];
const timeSlot = $('<button>').text(timeSlotText).attr('data-time-value', timeSlotText24);
const dayCell = $('<div>').text(day).attr('data-date-value', dateValue);

추가적으로 View단에선 오전/오후 12시간제로 표시되지만,
스크립트에선 24시간제로 값을 사용할 수 있도록 설정
이후 시간/분으로 분할해서 사용할 수 있도록 변수 할당 완료.

let timeValue;
let dateText;

timeValue = $(this).data("timeValue"); (시간이 설정된 버튼을 클릭하면 실행되는 콜백)
let timeValueSplit = timeValue.split(':');
selectTimeHour = timeTextSplit[0];
selectTimeMinutes = timeTextSplit[1];
```

* 주소 검색 API 적용 완료 및 데이터 정제 진행 중.

```
사용하는 데이터 필드 정리

roadAddress (도로명 주소)
jibunAddress (지번 주소)
sido (시/도)
sigungu (시/군/구)
bname1 (읍/면) : 동 일 경우는 공백
bname2 (동/리)
bname (동/리) ()
```

