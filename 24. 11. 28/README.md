# 24. 11. 28

## Ajax

### $.ajax()
* ajax load 메소드를 간략화한 메소드
* 기본형태
* $.ajax({name : value, name : value, ...});

### name으로 선택할 수 있는 요소
* type : GET or POST
* url : 경로
* data : 보낼 데이터 패키지
* success : 호출 성공 시 콜백 내용

### 사용 예시

$('button').click(function(){
	$.ajax({
		type : "get",
		url : "../ajax_test.do",
		data : {
			id : "test", // request parameter
			pw : "1234"
		},
		success : function(result) {
			console.log(result);
			$('#output').html(result);
		}
	});	// End ajax
}); // End btn.click

### Get

$('button').click(function(){
	$.get('url', function(data, status){
		alert('Data : ' + data + "\n status : " + status);
	}); // End Get
}); // End btn.click

### Post

$('button').click(function(){
	$.post("url", 
			{
				name : "kang", 
				city : "seoul"
			},
			function(data, status){
				alert("Data : " + data + "\n status : " + status);
			}); // End post()
}); // End btn.click
			

### Ajax로 JSON 데이터 호출하기

$.ajax({
	type: "get",
	url : "06_json_test.txt",
	dataType: "json",
	success : function(result){
		let output = "";
		output += "이름: " + result.name + "<br>";
		output += "나이: " + result.age + "<br>";
		output += "애완동물:<br>";
		$.each(result.pets, function(index, pet) {
			output += "# " + pet.animal + " (이름: " + pet.name + ")<br>";
		});
		$("#output").html(output);
	} // End Success
}); // End Ajax

### Servlet과 JSON 간의 통신 방법 

(InfoServelt.java)

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Java 객체를 JSON 배열 형태로 변경
	// * json 라이브러리 다운로드
	
	String userid = "test";
	String password = "1234";
	String email = "test@test.com";
	
	// JSON Array 객체 생성
	JSONArray jsonArray = new JSONArray();
	
	for(int i = 0; i < 4; i++) {
		// JSON 객체 생성
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userid", userid);
		jsonObject.put("password", password);
		jsonObject.put("email", email);
		jsonArray.add(jsonObject);
	}
	
	// JSON 객체 전송
	response.getWriter().append(jsonArray.toString());
}

(07_json_example.html)

getData();
function getData() {
	$.getJSON('../info.do', function(result){
		console.log(typeof(result));
		
		let list = "";
		for (x in result) {
			list += result[x].userid + '<br>'
				+ result[x].password + '<br>'
				+ result[x].email + '<br>'
		}
		$('#info').html(list);
	}); // End getJSON
} // End getData

## MVC(Model-View-Controller) Pattern

### Model
* 개발자가 처리할 데이터를 표현하는 클래스
* 데이터의 변경과 조작을 위한 비즈니스 규칙을 표현하는 클래스

### View
* 애플리케이션의 사용자 인터페이스 영역

### Controller
* 사용자와의 커뮤니케이션
* 애플리케이션의 특정 로직을 처리하는 클래스 영역
* 애플리케이션의 전체적인 흐름 관리

### 장/단점

* 장점
* 하나의 모델로 여러가지 뷰를 구현하는 것이 가능
* 컴포넌트를 쉽게 재사용 할 수 있는 구조
* 한 영역의 컴포넌트에 변경이 발생해도 다른 영역의 컴포턴트에 미치는 영향을 최소화 할 수 있는 구조

* 단점
* 복잡성의 증가로 구현 난이도가 높음
* 모든 컴포넌트 간의 상호작용을 고려할 필요가 있는 구조


## DBCP
* 데이터베이스와 연결된 커넥션을 미리 만들어서 풀(pool) 속에 저장해 두고 있다가 필요할 
    때에 커넥션을 풀에서 가져다 쓰고 다시 풀에 반환하는 기법
* 커넥션을 생성하는 데 드는 연결 시간이 소비되지 않는다.
* 커넥션을 재사용하기 때문에 생성되는 커넥션 수가 많지 않다.
* DB의 부하를 줄이고 유동적으로 연결을 관리할 수 있다.

### DBCP 동작 원리
* 웹 컨테이너가 실행되면서 Connection 객체를 미리 Connection Pool에 생성
* Connection이 필요할 때마다 Pool에서 객체를 가져다 쓰고 반환
