DROP TABLE CV_USER_ROLE;
CREATE TABLE CV_USER_ROLE(
       ID NUMBER(11) NOT NULL PRIMARY KEY,
       USER_ID NUMBER(11),
       ROLE_ID NUMBER(11),
       CREATED_DATE DATE,
       CREATED_BY   VARCHAR2(40),
       UPATE_DATE DATE,
       UPDATE_BY VARCHAR2(40)
);
COMMENT ON TABLE CV_USER_ROLE IS 'Convey系统用户角色映射表';
COMMENT ON COLUMN CV_USER_ROLE.ID IS 'id主键';
COMMENT ON COLUMN CV_USER_ROLE.USER_ID IS '用户id';
COMMENT ON COLUMN CV_USER_ROLE.ROLE_ID IS '角色';
COMMENT ON COLUMN CV_USER.CREATED_DATE IS '创建时间';
COMMENT ON COLUMN CV_USER.CREATED_BY IS '创建人';
COMMENT ON COLUMN CV_USER.UPATE_DATE IS '更新时间';
COMMENT ON COLUMN CV_USER.UPDATE_BY IS '更新人';