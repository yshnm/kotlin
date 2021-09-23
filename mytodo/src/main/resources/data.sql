delete from TASK;
delete from SUB_TASK;
delete from NEXT_SUB_TASK;

INSERT INTO TASK(TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 'タスク1タイトル', 'タスク1内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
INSERT INTO TASK(TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(2, 'タスク2タイトル', 'タスク2内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
INSERT INTO TASK(TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(3, 'タスク3タイトル', 'タスク3内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  1);
INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 1,  'サブタスク1-1タイトル', 'サブタスク1-1内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 2,  'サブタスク1-2タイトル', 'サブタスク1-2内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 3,  'サブタスク1-3タイトル', 'サブタスク1-3内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  1);
INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(3, 1,  'サブタスク3-1タイトル', 'サブタスク3-1内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
INSERT INTO NEXT_SUB_TASK(TASK_ID, SUB_TASK_ID, NEXT_SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 1, 1, 'ネクストサブタスク1-1-1タイトル', 'ネクストサブタスク1-1-1内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
INSERT INTO NEXT_SUB_TASK(TASK_ID, SUB_TASK_ID, NEXT_SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 1, 2, 'ネクストサブタスク1-1-2タイトル', 'ネクストサブタスク1-1-2内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
INSERT INTO NEXT_SUB_TASK(TASK_ID, SUB_TASK_ID, NEXT_SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 1, 3, 'ネクストサブタスク1-1-2タイトル', 'ネクストサブタスク1-1-2内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  1);
INSERT INTO NEXT_SUB_TASK(TASK_ID, SUB_TASK_ID, NEXT_SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(3, 1, 1, 'ネクストサブタスク3-1-1タイトル', 'ネクストサブタスク3-1-1内容', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  1);