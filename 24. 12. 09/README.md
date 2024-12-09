# 24. 12. 09

## 비동기 방식을 활용한 댓글 기능 구현

### 댓글 기능 요구사항
* 댓글은 특정 게시글 페이지에서 비동기 방식으로 수행되어야 한다.
* 댓글을 생성할 수 있어야 한다.
* 댓글들을 조회할 수 있어야 한다.
* 댓글을 수정할 수 있어야 한다.
* 댓글을 삭제할 수 있어야 한다.


### 댓글 테이블 생성

```
CREATE TABLE REPLY (
    REPLY_ID NUMBER PRIMARY KEY,
    BOARD_ID NUMBER,
    MEMBER_ID VARCHAR2(10),
    REPLY_CONTENT VARCHAR2(300),
    REPLY_DATE_CREATED TIMESTAMP DEFAULT SYSDATE
);

CREATE SEQUENCE  REPLY_SEQ  MINVALUE 1 MAXVALUE 10000000 
INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE;
```

### ReplyVO 작성

```
CREATE TABLE REPLY (
    REPLY_ID NUMBER PRIMARY KEY,
    BOARD_ID NUMBER,
    MEMBER_ID VARCHAR2(10),
    REPLY_CONTENT VARCHAR2(300),
    REPLY_DATE_CREATED TIMESTAMP DEFAULT SYSDATE
);

CREATE SEQUENCE  REPLY_SEQ  MINVALUE 1 MAXVALUE 10000000 
INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE;
```

### ReplyMapper Query 작성

```
<resultMap type="com.mokcoding.ex03.domain.ReplyVO" id="replyResultMap">
   <id property="replyId" column="REPLY_ID" /> 
   <result property="boardId" column="BOARD_ID" /> 
   <result property="memberId" column="MEMBER_ID" />
   <result property="replyContent" column="REPLY_CONTENT" />
   <result property="replyDateCreated" column="REPLY_DATE_CREATED" />
</resultMap>

<insert id="insert">
   INSERT INTO REPLY (REPLY_ID, BOARD_ID, MEMBER_ID, REPLY_CONTENT)
   VALUES (REPLY_SEQ.NEXTVAL, #{boardId}, #{memberId}, #{replyContent})
</insert>

<select id="selectListByBoardId" resultMap="replyResultMap">
   SELECT * FROM REPLY
   WHERE BOARD_ID = #{boardId}
   ORDER BY REPLY_ID
</select>

<update id="update">
   UPDATE REPLY
   SET REPLY_CONTENT = #{replyContent}
   WHERE REPLY_ID = #{replyId}
</update>

<delete id="delete">
   DELETE FROM REPLY
   WHERE REPLY_ID = #{replyId}
</delete>
```

### ReplyService/Implement, Controller 작성 (파일첨부 Spring03)

* POSTMAN을 통한 테스트

1) insert (POST // ex03/reply/3)

![InsertTest](https://github.com/user-attachments/assets/d6b2b5fa-e1e3-4d2d-b0d1-e8609f235268)

2) selectListByBoardId (GET // ex03/reply/all/3)

![SelectTest](https://github.com/user-attachments/assets/b64e8c9d-60c3-483d-ae9f-9a2a30351066)

3) update (PUT // ex03/reply/1)

![UpdateTest](https://github.com/user-attachments/assets/f1310603-207d-45dc-9a3a-145eb307efcc)

4) delete (DELETE // ex03/reply/1/1)

![DeleteTest](https://github.com/user-attachments/assets/c67869d7-a323-4997-99b4-af597e742214)

* HTML 페이지 내에서 전체 기능 확인 테스트

![ViewTest](https://github.com/user-attachments/assets/67b2cb99-44f2-4558-8c15-ffcb340139d7)

## Aspect
* 일반적으로 메소드에 특정 기능을 적용시킬 때 사용
	
### Pointcut을 지정하는 방법
1) @Pointcut 어노테이션 안에서 지정
2) 동일한 Pointcut이 반복되는 것을 피할 수 있음
3) 한 번 지정한 Poincut을 여러 advice 메소드에서 참조