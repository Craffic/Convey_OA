insert into OA_DICT (ID, ITEM_NAME, P_KEY, KEY, VALUE, COMMENTS, CREATED_DATE, CREATED_BY, UPDATE_DATE, UPDATE_BY) values (SYS_GUID(), 'WORK_STAT', null, 0, '离职', null, SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM');
insert into OA_DICT (ID, ITEM_NAME, P_KEY, KEY, VALUE, COMMENTS, CREATED_DATE, CREATED_BY, UPDATE_DATE, UPDATE_BY) values (SYS_GUID(), 'WORK_STAT', null, 1, '在职', null, SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM');
insert into OA_DICT (ID, ITEM_NAME, P_KEY, KEY, VALUE, COMMENTS, CREATED_DATE, CREATED_BY, UPDATE_DATE, UPDATE_BY) values (SYS_GUID(), 'WORK_STAT', null, 2, '请假', null, SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM');
insert into OA_DICT (ID, ITEM_NAME, P_KEY, KEY, VALUE, COMMENTS, CREATED_DATE, CREATED_BY, UPDATE_DATE, UPDATE_BY) values (SYS_GUID(), 'WORK_STAT', null, 3, '出差', null, SYSDATE, 'SYSTEM', SYSDATE, 'SYSTEM');
commit;