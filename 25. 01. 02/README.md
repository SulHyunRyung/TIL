# 25. 01. 02

## Spring Security

- Spring security 설정을 진행하면 CSRF 기능이 자동으로 적용된다.
- CSRF는 모든 form post 데이터를 전송할 때 전송되는 데이터를 검증하는 기능이다.
- 따라서 form post 및 ajax post 데이터를 전송할 때 CSRF 토큰을 같이 전송해야 한다. 

* <header>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

* form POST
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">

## 검색 기능 구현 예시

* Mapper

```
SELECT
    <choose>
        <when>
            WHRER --- LIKE % ---
        </when>
        <when>
            WHRER --- LIKE % ---
        </when>
        <when>
            WHRER --- LIKE % ---
        </when>
    </choose>
```

## Team Project(Eat Platform) 진행

* 리뷰 작성 시 작성란 컨텐츠 초기화 기능 추가. (img, text)
* review tag list 호환 완료
* review 작성하기 슬라이드 기능 추가
