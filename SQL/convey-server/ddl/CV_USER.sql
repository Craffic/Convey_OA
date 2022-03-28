DROP TABLE CV_USER;
CREATE TABLE CV_USER(
         ID NUMBER(11) NOT NULL PRIMARY KEY,
         WORK_ID NUMBER(8) NOT NULL,
         ACCT VARCHAR2(30),
         NAME_ZH VARCHAR2(32),
         PASSWORD VARCHAR2(255),
         PHONE VARCHAR2(11),
         EMAIL VARCHAR2(30),
         ICON_URL VARCHAR2(255),
         POS_ID VARCHAR2(11),
         DPT_ID NUMBER(11),
         REMARK VARCHAR2(255),
         BEGIN_DATE DATE,
         CONVERT_DATE DATE,
         WORK_STAT NUMBER(1),
         LEAVE_DATE DATE,
         CREATED_DATE DATE,
         CREATED_BY   VARCHAR2(40),
         UPATE_DATE DATE,
         UPDATE_BY VARCHAR2(40)
  );
COMMENT ON TABLE CV_USER IS 'Convey系统用户表';
COMMENT ON COLUMN CV_USER.ID IS 'id主键';
COMMENT ON COLUMN CV_USER.WORK_ID IS '工号';
COMMENT ON COLUMN CV_USER.ACCT IS '系统账号';
COMMENT ON COLUMN CV_USER.NAME_ZH IS '中文姓名';
COMMENT ON COLUMN CV_USER.PASSWORD IS '密码';
COMMENT ON COLUMN CV_USER.PHONE IS '联系号码';
COMMENT ON COLUMN CV_USER.EMAIL IS '邮箱';
COMMENT ON COLUMN CV_USER.ICON_URL IS '头像url';
COMMENT ON COLUMN CV_USER.POS_ID IS '职位id';
COMMENT ON COLUMN CV_USER.DPT_ID IS '所属部门id';
COMMENT ON COLUMN CV_USER.REMARK IS '备注';
COMMENT ON COLUMN CV_USER.BEGIN_DATE IS '入职日期';
COMMENT ON COLUMN CV_USER.CONVERT_DATE IS '转正日期';
COMMENT ON COLUMN CV_USER.WORK_STAT IS '在职状态';
COMMENT ON COLUMN CV_USER.LEAVE_DATE IS '离职日期';
COMMENT ON COLUMN CV_USER.CREATED_DATE IS '创建时间';
COMMENT ON COLUMN CV_USER.CREATED_BY IS '创建人';
COMMENT ON COLUMN CV_USER.UPATE_DATE IS '更新时间';
COMMENT ON COLUMN CV_USER.UPDATE_BY IS '更新人';

ALTER TABLE CV_USER ADD IDCARD_NO VARCHAR2(18);
ALTER TABLE CV_USER ADD GENDER VARCHAR2(10);

--INDEX
ALTER TABLE CV_USER ADD CONSTRAINT ID_CARD UNIQUE (ID, IDCARD_NO);