/* insert user data */
truncate table user;

insert into user values ('test', 18, 'test@outlook.com', '测试人员', '123456', false);
insert into user values ('test1', 19, 'test1@outlook.com', '测试人员1', '123456', false);
insert into user values ('test2', 20, 'test2@outlook.com', '测试人员2', '123456', true);

/* insert department data and organization data*/

truncate table department;
set foreign_key_checks = 0;
truncate table organization;
set foreign_key_checks = 1;

insert into organization values (1, '计算机学院组织', '计算机学院');
insert into organization values (2, '信息学院组织', '信息学院');

insert into department values (1, '软件工程1801部门', '软件工程1801', 1);
insert into department values (2, '软件工程1802部门', '软件工程1802', 1);
insert into department values (3, '软件工程1803部门', '软件工程1803', 1);
insert into department values (4, '软件工程1804部门', '软件工程1804', 1);
insert into department values (5, '软件工程1805部门', '软件工程1805', 1);

insert into department values (6, '通信工程1801部门', '通信工程1801', 2);
insert into department values (7, '通信工程1802部门', '通信工程1802', 2);
insert into department values (8, '通信工程1803部门', '通信工程1803', 2);