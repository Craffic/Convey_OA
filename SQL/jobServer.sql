--创建社会人员表
DROP TABLE PEOPLE;
CREATE TABLE PEOPLE(
         IDCARD_NO VARCHAR2(18) NOT NULL PRIMARY KEY,
         NAME VARCHAR2(40) NOT NULL,
         GENDER VARCHAR2(10),
         AGE NUMBER,
         NATIVE_PLACE_CODE NUMBER,
         HOME_ADDRESS VARCHAR2(100),
         WORK_ADDRESS VARCHAR2(100),
         PROFESSION_CODE NUMBER,
         GRANDUATE_SCHOOL_CODE NUMBER,
         CREATED_DATE DATE,
         CREATED_BY   VARCHAR2(40),
         UPATE_DATE DATE,
         UPDATE_BY VARCHAR2(40)
  );
COMMENT ON COLUMN PEOPLE.IDCARD_NO IS '身份证号';
COMMENT ON COLUMN PEOPLE.NAME IS '姓名';
COMMENT ON COLUMN PEOPLE.GENDER IS '性别 M:男性 F:女性 空：未知';
COMMENT ON COLUMN PEOPLE.AGE IS '年龄';
COMMENT ON COLUMN PEOPLE.NATIVE_PLACE_CODE IS '籍贯';
COMMENT ON COLUMN PEOPLE.HOME_ADDRESS IS '家庭地址';
COMMENT ON COLUMN PEOPLE.WORK_ADDRESS IS '工作地址';
COMMENT ON COLUMN PEOPLE.PROFESSION_CODE IS '职业编码';
COMMENT ON COLUMN PEOPLE.GRANDUATE_SCHOOL_CODE IS '毕业学校编码';
COMMENT ON COLUMN PEOPLE.CREATED_DATE IS '创建时间';
COMMENT ON COLUMN PEOPLE.CREATED_BY IS '创建人';
COMMENT ON COLUMN PEOPLE.UPATE_DATE IS '更新时间';
COMMENT ON COLUMN PEOPLE.UPDATE_BY IS '更新人';

--字典表
CREATE TABLE OA_DICT(
                        ID VARCHAR2(32) NOT NULL PRIMARY KEY,
                        ITEM_NAME VARCHAR2(50) NOT NULL,
                        P_KEY NUMBER(6),
                        KEY NUMBER(6),
                        VALUE VARCHAR2(20),
                        COMMENTS VARCHAR2(50),
                        CREATED_DATE DATE,
                        CREATED_BY   VARCHAR2(40),
                        UPATE_DATE DATE,
                        UPDATE_BY VARCHAR2(40)
);
COMMENT ON TABLE OA_DICT IS '字典表';
COMMENT ON COLUMN OA_DICT.ID IS 'id主键';
COMMENT ON COLUMN OA_DICT.ITEM_NAME IS '字典项';
COMMENT ON COLUMN OA_DICT.P_KEY IS '父key';
COMMENT ON COLUMN OA_DICT.KEY IS 'key';
COMMENT ON COLUMN OA_DICT.VALUE IS 'value';
COMMENT ON COLUMN OA_DICT.COMMENTS IS '备注';
COMMENT ON COLUMN OA_DICT.CREATED_DATE IS '创建时间';
COMMENT ON COLUMN OA_DICT.CREATED_BY IS '创建人';
COMMENT ON COLUMN OA_DICT.UPATE_DATE IS '更新时间';
COMMENT ON COLUMN OA_DICT.UPDATE_BY IS '更新人';

CREATE INDEX IDX_ITEM ON OA_DICT(ITEM_NAME, P_KEY, KEY, VALUE);