DROP TABLE CV_MENU;
CREATE TABLE CV_MENU(
       ID NUMBER(11) NOT NULL PRIMARY KEY,
       URL VARCHAR2(64),
       PATH VARCHAR2(64),
       COMPONENT VARCHAR2(64),
       NAME VARCHAR2(64),
       ICON_CLS VARCHAR2(64),
       KEEP_ALIVE NUMBER,
       REQUIRE_AUTH NUMBER,
       PARENT_ID NUMBER(11),
       ENABLED NUMBER
);
COMMENT ON TABLE CV_MENU IS 'Convey系统资源表';
COMMENT ON COLUMN CV_MENU.ID IS 'id主键';
COMMENT ON COLUMN CV_MENU.URL IS '资源url';
COMMENT ON COLUMN CV_MENU.PATH IS '系统菜单';
COMMENT ON COLUMN CV_MENU.COMPONENT IS '菜单组件';
COMMENT ON COLUMN CV_MENU.NAME IS '菜单名';
COMMENT ON COLUMN CV_MENU.ICON_CLS IS '菜单图标';
COMMENT ON COLUMN CV_MENU.KEEP_ALIVE IS '是否激活';
COMMENT ON COLUMN CV_MENU.REQUIRE_AUTH IS '是否需要权限';
COMMENT ON COLUMN CV_MENU.PARENT_ID IS '父id';
COMMENT ON COLUMN CV_MENU.ENABLED IS '是否启用';