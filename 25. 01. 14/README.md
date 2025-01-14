# 25. 01. 14

## Team Project (EatPlatFrom) 작업 진행

### 주소 기반 지도 시스템 구현 중

1) 기본 좌표(위치)를 현재 위치로 설정이 가능한가?

```
navigator.geolocation.getCurrentPosition((position) => {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
    console.log("위도 : " + latitude);
    console.log("경도 : " + longitude);
    console.log(position);
});

지오코더 API를 활용하여 위치 정보 허용을 한 유저에 한하여 초기 좌표값 수집이 가능

let mapContainer = document.getElementById('map'),
    mapOption = { 
        center: new kakao.maps.LatLng(latitude, longitude), // 지도의 초기 좌표
        level: 3 // 줌 레벨
    };

이후 해당 좌표 값으로 초기 지점 설정,
하지만 정확도가 떨어질 수 있음을 확인.

$.ajax({
	url: "https://api.vworld.kr/req/address?",
	type: "GET",
	dataType: "jsonp",
	data: {
		service: "address",
		request: "GetCoord",
		version: "2.0",
		crs: "EPSG:4326",
		type: "ROAD",
		address: "서울특별시 강남구 봉은사로 524",
		format: "json",
		errorformat: "json",
		key: "1DF985C8-77FD-3F44-981C-8B1EA1BCC7D9"
	},
	success: function (result) {
		console.log(result);
	}
});  

국토교통부에서 제공하는 지오코더 API를 시적용 시도.
허나 해당 API에서 수집하는 좌표 값은 
EPSG:4326 EPSG:4019 같이 지도에서 요구하는 포맷과 다름을 확인
해당 안건 폐기.


최종적으로 정확도가 조금 떨어질 수 있지만,
오차범위 내에 있는 G-map 지오코더 적용
```

2) 등록된 가게의 정보를 어떤 형태의 리스트로 출력하는가?

* 넘버링 페이징 처리
* 스크롤을 통한 lazy-load 처리

```
우선 먼저 고려했던 것은
두 방식의 장단점.

넘버링 페이징 같은 경우는 우선
서버의 부하가 적고,
기존에 적용했던 방식이므로 난이도가 낮다는 장점이 있었지만

사용자의 입장에서 상대적으로 불편한 시스템이라는 단점이 있고,

스크롤을 통한 lazy-load 방식의 경우는
사용자의 입장에서 이용하는 것에 있어 자연스러워 UX가 향상되는 장점이 있지만,
반대로 데이터의 로드가 늘어날 수록 서버의 부하가 심해지고,
해당 부분과 연관해서 사용 기기의 부하가 심해지는 것이 있음.

기존의 본인도 UX를 아예 제외하고 진행할 수도 없을 뿐더러,
서버의 부하도 어느정도 고려해야하는 사항이다보니 꽤나 고민했던 부분인 것 같음.
해당 논제를 두고 레퍼런스를 찾아보던 중
네이버 지도에서 두 방식을 모두 채용한 리스트를 확인.

예시로 한 페이지에 최대 30개의 리스트를 출력할 수 있지만,
초기 데이터는 5개로 제한을 하고,
특정 지점에 대한 스크롤 이벤트가 발생할 경우
추가적으로 데이터를 로드하는 방식.

해당 방식이라면
많은 데이터를 적용했을 때
사용자의 입장에선 직접 조작으로 인한 불편함을 줄일 수 있고,
서버 입장에서도 적은 데이터 + 순차적인 로드를 통해 부하를 줄일 수 있을 것이라 판단.

최종적으로는 넘버링 + 스크롤 lazy-load 방식을 혼합하여 채용.
```




* 그 외 구현 사항

```
1. Header, Footer의 여백으로 인한 풀사이즈 조정

기존 UI에서는 Header/Footer의 Position 배치로 인해
생겨난 여백을 조정하기 위해 단순히 empty_header/footer의
height를 조정해서 여백을 생성했었지만,
특정 요소(vw, vh 같이 화면의 비율에 따라 채우는)에서는 해당 부분의 height를
계산할 필요성을 느낌.

해당 요소에 따라 적용 방식이 다를테니
100vh를 기준으로 header만 존재하는 페이지에서는
.exceptionHeader의 클래스를 부여해서 height를
100vh - headHeight 수치로 적용하여 스크린에 따라 초기 height 값을 설정
이후 반응형 요소가 적용되어야 한다면 resize 시에도 작동하도록
.exceptionHeaderResize 클래스를 따로 생성.

footer까지 존재하는 페이지에선 .fullException, fullExceptionResize 클래스로
위와 동일하게 컨트롤

```