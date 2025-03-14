# 24. 11. 27

## Ajax (Asynchronous JavaScript And XML)
		
### Ajax를 사용하는 이유
* 웹 페이지가 로드된 후 서버에서 데이터를 불러오기 위해
* 새로고침 없이 페이지를 업데이트
* 백그라운드에서 웹 서버에 데이터 전송

## XML ( Extensible Markup Language) Http

### XMLHttp 사용 예시
```
<div id="output">
	<h2>XMLHttpRequest Object</h2>
	<button onclick="loadDoc()">내용변경</button>
</div>
<script>
    function loadDoc() {
    	let xhttp = new XMLHttpRequest(); // Browser 내장 request 객체
    	xhttp.onreadystatechange = function() {
    		console.log(this.readyState);
    		console.log(this.status);
    		if(this.readyState == 4 && this.status == 200) {
    			console.log(this.responseText);
                document.getElementById("output").innerHTML = this.responseText;
    	    }
        }
        xhttp.open("GET", "01_demo_test.txt", true);
        // open(method, url, asnyc) : xhttp에 보낼 정보 세팅
	    // method : 요청 타입 GET or POST
	    // url : 파일 욫어 서버 위치
 	    // async : true(비동기) or false(동기)
        xhttp.send();
}
</script>
```

* 01_demo_test,txt
```
<h2>텍스트</h2>
<p id="p1">P텍스트</p>



![Start](https://github.com/user-attachments/assets/781708d1-1094-44c0-9063-4f3535c2f4fa)
![Click](https://github.com/user-attachments/assets/c1ed8b9c-93e9-49f2-9725-81ea5ec67451)
![Console](https://github.com/user-attachments/assets/f4ade339-3382-4681-b07a-c6f057bd0a03)

이미지와 같이 버튼을 클릭할 경우 01_demo_test.txt 파일의 내용으로 변경되며,
Console 창에 State와 Status, txt의 내용이 출력된다.
```

### Get/Post 요청 방식 예시

* 공통
```
<h2>XMLHttpRequest 요청</h2>
<button onclick="loadDocGet()">get 요청</button>
<button onclick="loadDocPost()">post 요청</button>
<p id="output"></p>
```

* Get방식
```
function loadDocGet() {
	let xhttp = new XMLHttpRequest(); // Browser 내장 request 객체
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			document.getElementById("output").innerHTML = this.responseText;
		}
	}
	xhttp.open("GET", "02_date.jsp?method=get&content=Hello", true);
	xhttp.send();
}
```

* Post방식
```
function loadDocPost() {
	let xhttp = new XMLHttpRequest(); // Browser 내장 request 객체
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			document.getElementById("output").innerHTML = this.responseText;
		}
	}
	xhttp.open("POST", "02_date.jsp", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("method=post&content=Hllo");
}
```

* 데이터 사용 예시
```
<h2>XML 파일 호출</h2>
<button onclick="loadDoc()">CD 컬렉션 호출</button>
<br>
<table id="output"></table>
<script>
	function loadDoc() {
		let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseXML);
				myFunction(this.responseXML)
			}
		}
		xhttp.open("GET", "03_cd_catalog.xml", true);
		xhttp.send();
	}

    function myFunction(xmlDoc) {
    	var table = "<tr><th>Artist</th><th>Title</th><th>Country</th></tr>";
    	var x = xmlDoc.getElementsByTagName("CD");
    	var i;
    	for (i = 0; i < x.length; i++) {
    		table += "<tr><td>"
    				+ x[i].getElementsByTagName("ARTIST")[0].childNodes[0].nodeValue
    				+ "</td>"
    				+ "<td>"
    				+ x[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue
    				+ "</td>"
    				+ "<td>"
    				+ x[i].getElementsByTagName("COUNTRY")[0].childNodes[0].nodeValue
    				+ "</td></tr>";
        }
        
    	document.getElementById("output").innerHTML = table;
    }
</script>
```

### jQuery Ajax
* jQuery는 Ajax의 다양한 기능을 사용할 수 있음
* jQuery Ajax 메소드를 사용하면 서버에서 텍스트, HTML, XML 또는 JSON 요청이 가능
* $(selector).load(URL, data, callback);
* URL : 파일 또는 서버 통신 경로
* data : 보낼 데이터
* callback : load가 수행된 이후에 해야할 기능 정의


## 하루 정리
* Front-end 업무를 볼 때 봤던 Ajax를 다른 관점으로 볼 수 있었던 기회였던 것 같다.
* 부족했던 XML에 관하여 알아볼 수있어 좋았던 것 같다.
