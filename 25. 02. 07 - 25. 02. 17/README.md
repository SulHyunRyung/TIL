# 2025. 02. 12 - 2025. 02. 17

## Team Project EatPlatForm 개발 진행

/* PRODUCT_SEQ */
CREATE SEQUENCE PRODUCT_SEQ
START WITH 1
MAXVALUE 100000
INCREMENT BY 1
NOCACHE
NOCYCLE
NOORDER;

/* MAIN_CATEGORY_SEQ */
CREATE SEQUENCE MAIN_CATEGORY_SEQ
START WITH 1
MAXVALUE 100000
INCREMENT BY 1
NOCACHE
NOCYCLE
NOORDER;

/* SUB_CATEGORY_SEQ */
CREATE SEQUENCE SUB_CATEGORY_SEQ
START WITH 1
MAXVALUE 100000
INCREMENT BY 1
NOCACHE
NOCYCLE
NOORDER;


/* TABLE PRODUCT */
CREATE TABLE PRODUCT (
    PRODUCT_ID NUMBER PRIMARY KEY,
    PRODUCT_STORE_ID NUMBER NOT NULL,
    SELLER_ID VARCHAR2(50) NOT NULL,
    PRODUCT_NAME VARCHAR2(400) NOT NULL,
    PRODUCT_PRICE NUMBER NOT NULL,
    PRODUCT_CATEGORY VARCHAR2(100) DEFAULT '기타',
    PRODUCT_IMG VARCHAR2(4000),
    PRODUCT_DETAIL_IMG VARCHAR2(4000),
    PRODUCT_BUNDLE NUMBER DEFAULT 1,
    PRODUCT_STOCK NUMBER DEFAULT 0,
    CREATEAT TIMESTAMP DEFAULT SYSTIMESTAMP,
    UPDATEDAT TIMESTAMP DEFAULT SYSTIMESTAMP
);

/* PRODUCT FK : STORE_ID */
ALTER TABLE PRODUCT 
ADD CONSTRAINT fk_product_store
    FOREIGN KEY (PRODUCT_STORE_ID)
    REFERENCES STORE (STORE_ID)
    ON DELETE CASCADE;
    
/* PRODUCT TRIGGER: UPDATEAT */    
CREATE OR REPLACE TRIGGER trg_product_updated_at
BEFORE UPDATE ON PRODUCT
FOR EACH ROW
BEGIN
    :NEW.UPDATEAT := SYSTIMESTAMP;
END;
/

/* PRODUCTVO */
public class ProductVO {
    private Integer productId;
    private Integer productStoreId;
    private String sellerId;
    private String productName;
    private Integer productPrice = 0;
    private Integer productBundle = 1;
    private Integer productStock = 0;
    private Timestamp createAt;
    private Timestamp updatedAt;
}

ALTER TABLE PRODUCT DROP COLUMN PRODUCT_CATEGORY;


CREATE TABLE PRODUCT_MAIN_CATEGORY (
    MAIN_CATEGORY_ID NUMBER PRIMARY KEY,
    MAIN_CATEGORY_NAME VARCHAR2(100) NOT NULL UNIQUE
);

CREATE TABLE PRODUCT_SUB_CATEGORY (
    SUB_CATEGORY_ID NUMBER PRIMARY KEY,
    MAIN_CATEGORY_ID NUMBER NOT NULL,
    SUB_CATEGORY_NAME VARCHAR2(100) NOT NULL,
    CONSTRAINT FK_SUBCATEGORY_MAIN FOREIGN KEY (MAIN_CATEGORY_ID) 
        REFERENCES PRODUCT_MAIN_CATEGORY(MAIN_CATEGORY_ID) ON DELETE CASCADE
);

CREATE TABLE PRODUCT_CATEGORY(
    PRODUCT_ID NUMBER NOT NULL,
    MAIN_CATEGORY_ID NUMBER NOT NULL,
    SUB_CATEGORY_ID NUMBER NOT NULL,
    PRIMARY KEY (PRODUCT_ID, MAIN_CATEGORY_ID, SUB_CATEGORY_ID),
    CONSTRAINT FK_CATEGORY_PRODUCT FOREIGN KEY (PRODUCT_ID) 
        REFERENCES PRODUCT(PRODUCT_ID) ON DELETE CASCADE,
    CONSTRAINT FK_CATEGORY_MAIN FOREIGN KEY (MAIN_CATEGORY_ID) 
        REFERENCES PRODUCT_MAIN_CATEGORY(MAIN_CATEGORY_ID),
    CONSTRAINT FK_CATEGORY_SUB FOREIGN KEY (SUB_CATEGORY_ID) 
        REFERENCES PRODUCT_SUB_CATEGORY(SUB_CATEGORY_ID)
);




```
상품 구매 & 결제 시스템 도입을 위해
상품 관련 테이블 구성 및 시스템 도입 중.
기본적으로는 평범한 데이터 INSERT, UPDATE, DELETE + SELECT 로 이루어져 있지만,
카테고리 관련해서 1:N 테이블 구조를 채택
카테고리 같은 경우는 간단하게 설명하면
상품마다 대분류, 소분류가 지정되며
해당 데이터 테이블 분리.
현재 관리자 권한의 계정에서
(대분류/소분류) 카테고리의 등록, 조회, 수정, 삭제 비동기로 구현 완료.
```