# 25. 01. 03

## Team Project(Eat Platform) 진행

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
* Header search_input 검색 기능 추가
* 공백 유무 상관없이 검색 가능
* 가게 이름, 카테고리, 가게 간단 소개, 가게 상세 설명, 메뉴 이름, 메뉴 설명에서 필터링 되어 검색 가능 

## 주소 검색 시스템
* 해당 기능의 Kakao API 분석 중

## 필요 컬럼 정리

* zonecode(우편번호)
* address(기본 주소)
* roadAddress(도로명 주소)
* jibunAddress(지번 주소)
* buildingName (건물명)
* sido (시/도)
* sigungu (시/군/구)
* bname (동/리)
* bname1 (읍/면)
* bname2 (동/리)

### 보류
* (?) autoRoadAddress
* (?) autoJibunAddress
