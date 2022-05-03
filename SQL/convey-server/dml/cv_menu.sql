insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (1, '/', null, null, '所有', null, null, null, null, 1);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (2, '/', '/home', 'Home', '员工资料', 'el-icon-user-solid', null, 1, 1, 1);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (3, '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', null, 1, 1, 0);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (4, '/', '/home', 'Home', '薪资管理', 'fa fa-money', null, 1, 1, 0);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (5, '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', null, 1, 1, 0);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (6, '/', '/home', 'Home', '系统管理', 'el-icon-s-tools', null, 1, 1, 0);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (7, '/user/basic/**', '/user/basic', 'UserBasic', '基本资料', null, null, 1, 2, 1);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (8, '/user/advanced/**', '/user/adv', 'UserAdv', '高级资料', null, null, 1, 2, 1);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (9, '/', '/home', 'Home', '社会调研', 'el-icon-document', null, 1, 1, 1);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (10, '/social/query/**', '/social/query', 'SocialQuery', '社会人员查询', null, null, 1, 9, 1);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (11, '/social/static/**', '/social/static', 'SocialStatic', '社会人员统计', null, null, 1, 9, 1);

insert into CV_MENU (ID, URL, PATH, COMPONENT, NAME, ICON_CLS, KEEP_ALIVE, REQUIRE_AUTH, PARENT_ID, ENABLED)
values (12, '/sys/department/**', '/sys/department', 'Department', '部门管理', null, null, 1, 6, 1);
commit;
