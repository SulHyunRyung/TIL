# 25. 01. 06

## Team Project(Eat Platform) 진행

* 주소 검색 기능 (API) 적용 진행 중.
* 검색 기능 구조 변경

```
<select id="getStoresWithPaging" resultMap="StoreResultMap">
		SELECT *
		FROM (
		SELECT
		store.*, ROW_NUMBER() OVER (ORDER BY STORE_REG_DATE DESC) AS RN
		FROM STORE store
		LEFT JOIN MENU menu ON store.STORE_ID = menu.STORE_ID
		<where>
			<if test="keyword != null and keyword != ''">
				AND (
				REPLACE(STORE_NAME, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
				OR REPLACE(FOOD_CATEGORY, ' ', '') LIKE '%' || REPLACE(#{keyword}, '
				', '') || '%'
				OR REPLACE(STORE_COMMENT, ' ', '') LIKE '%' || REPLACE(#{keyword}, '
				', '') || '%'
				OR REPLACE(DESCRIPTION, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ',
				'') || '%'
				OR REPLACE(MENU_NAME, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ',
				'') || '%'
				OR REPLACE(MENU_COMMENT, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ',
				'') || '%'
				)
			</if>
		</where>
		)
		WHERE RN &gt;= #{startRow} AND RN &lt;= #{endRow}
	</select>

	<!-- 전체 게시글 카운트 -->
	<select id="getTotalStoresCount" resultType="int">
		SELECT COUNT(*)
		FROM
		STORE
		LEFT JOIN MENU menu ON store.STORE_ID = menu.STORE_ID
		<where>
			<if test="keyword != null and keyword != ''">
				AND (
				REPLACE(STORE_NAME, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
				OR REPLACE(FOOD_CATEGORY, ' ', '') LIKE '%' || REPLACE(#{keyword}, '
				', '') || '%'
				OR REPLACE(STORE_COMMENT, ' ', '') LIKE '%' || REPLACE(#{keyword}, '
				', '') || '%'
				OR REPLACE(DESCRIPTION, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ',
				'') || '%'
				OR REPLACE(MENU_NAME, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ',
				'') || '%'
				OR REPLACE(MENU_COMMENT, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ',
				'') || '%'
				)
			</if>
		</where>
	</select>
```
[기존코드]

* 컬럼의 데이터 사이의 공백을 무시한 채로 키워드와 비교 후 검색
* 예시: 데이터 : "서울시 맛집" = 키워드 : "서울시맛집" (가능)

```
	<select id="getStoresWithPaging" resultMap="StoreResultMap" parameterType="java.util.Map">
	  SELECT *
	  FROM (
	    SELECT
	      store.*, ROW_NUMBER() OVER (ORDER BY STORE_REG_DATE DESC) AS RN
	    FROM STORE store
	    LEFT JOIN MENU menu ON store.STORE_ID = menu.STORE_ID
       <where>
           <if test="keywords != null and keywords.size() > 0">
               AND (
                   <foreach collection="keywords" item="keyword" separator="OR">
                       REPLACE(STORE_NAME, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(FOOD_CATEGORY, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(STORE_COMMENT, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(DESCRIPTION, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(MENU_NAME, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(MENU_COMMENT, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                   </foreach>
               )
           </if>
       </where>
	 	 )
		WHERE RN &gt;= #{startRow} AND RN &lt;= #{endRow}
	</select>

	<!-- 전체 게시글 카운트 -->
	<select id="getTotalStoresCount" resultType="int" parameterType="java.util.Map">
		SELECT COUNT(*)
		FROM
		STORE store
		LEFT JOIN MENU menu ON store.STORE_ID = menu.STORE_ID
       <where>
           <if test="keywords != null and keywords.size() > 0">
               AND (
                   <foreach collection="keywords" item="keyword" separator="OR">
                       REPLACE(STORE_NAME, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(FOOD_CATEGORY, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(STORE_COMMENT, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(DESCRIPTION, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(MENU_NAME, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                       OR REPLACE(MENU_COMMENT, ' ', '') LIKE '%' || REPLACE(#{keyword}, ' ', '') || '%'
                   </foreach>
               )
           </if>
       </where>
	</select>
```

[수정코드]
* 기존 공백 개념 + 복합 키워드 기능 추가.
* 예시 : 데이터 : "서울 한우 대창" = 키워드 : "서울 대창" (가능)
* 추가적으로 %20(공백)을 사이에 두고 여러 키워드 동시 검색 가능
* 예시 : 데이터 : "서울 한우 대창", "함흥냉면" = 키워드 : "한우 냉면" (두 가지 결과 모두 나옴)
* 예시2 : 데이터 : "서울 한우 대창", "맛있는 국수집", 키워드 : "한우 맛있는국수" (두 가지 결과가 모두 나옴)

[정리]
* 우선 기존 단순 조건문을 사용하는 쿼리에서 조건+반복문으로 변경됨.
* 추가적으로 복합 키워드를 반영하기 위해 기존에 있던 키워드의 타입을 리스트로 변경함.

```
<pre>
    우선 제일 어려웠던 경험은,
    파라미터 타입에 대한 값에 대한 것이었던 것 같음.

    getStoresWithPaging 같은 경우는 기존에도 startRow, EndRow 두 개의 값과 String타입의 keyword를 받고 있었기에
    원래에도 여러 값을 한 번에 넘겨줄 수 있는 HashMap을 사용했었음.
    그러다보니 keyword의 변환 처리 이외에는 크게 어려웠던 부분이 없었던 반면에

    getTotalStoresCount 같은 경우는 사실 검색 조건만 같으면 될 것이라는 생각 때문에 계속 헤맸던 것 같음.
    그 중에서도 오래 속 썩이던 부분은

    org.springframework.web.util.NestedServletException: Request processing failed; nested exception is 
    org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: 
    Parameter 'keywords' not found. Available parameters are [arg0, collection, list]

    해당 오류인데, 처음에는 Service 혹은 Mapper 단에서 매핑하는 부분을 잘못 했나라고 생각했었고,
    이후에도 잘못된 부분을 찾지 못해 Controller까지 흘러 올라가 keyword(String) -> keywords(ArrayList)로 
    변환하는 과정에서 잘못 됐나,
    아니면 select문의 parameterType 문제가 있나, 이러한 주제에 오랫동안 사로잡혀 있었던 것 같음.

    아무리 찾아도 문제를 모르겠던 시점에서
    그냥 하나씩 다 지워보면서 시도 했을 때
    getTotalStoresCount의 WHERE절을 통째로 지웠을 때 부분적으로 작동하는 것을 확인하고,
    그제서야 초점이 getStoresWithPaging에서 벗어났음.

    이후 Errmsg, 여태 남겨두었던 로그들을 하나씩 돌려보면서 깨달았던 것은
    parameterType의 문제는 맞았다.
    당연하게도 기존의 getTotalStoresCount는 값을 String keyword 하나
    혹은 아예 안받고 있었기에 당연하게 생각을 안했던 부분이었지만,
    해당 메서드는 Map으로 parameter를 넘기지 않는 것을 간과하고 있던 것.
    애초에 기존에는 크게 신경 쓸 메서드도 아니었고,
    그냥 같은 조건으로 검색한 게시글의 숫자를 세어준다고만 생각했으니까 
    똑같이 작업하면 되겠지. 라고 생각했던게 컸던거 같다.

    이후 getTotalStoresCount 메서드도 HashMap으로 keyword(ArrayList)의 parameter를 넘겨주고
    타입을 설정해주는 것으로 문제를 해결함.

    조금 정리 같은걸 해보자면,
    물론 초점을 잘못 잡긴 했지만,
    원래 포커스에서는 크게 문제가 없던게 오히려 더 어려웠던 부분인 것 같다.
    차라리 진짜 keywords가 값이 안들어오거나 진짜 매핑이 안되었던 것이라면
    오히려 더 빨리 찾았을텐데. 하면서

    예전에 프론트를 공부하면서 혹은 업무를 진행하면서도 느끼는 것이지만,
    고생은 했지만 오히려 이런 경험이 더 강하게 각인되는 부분도 있는 것 같다.

    예전부터 실수를 한 번 하고나면 오히려 더욱 조심하게 되었으니까.
    이번에도 힘들었지만 그런 경험으로 기억하려한다.
</pre>
```
