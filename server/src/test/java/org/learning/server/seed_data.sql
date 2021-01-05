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


set foreign_key_checks = 0;
truncate table course;
truncate table course_tag;
truncate table course_course_tags;
set foreign_key_checks = 1;

insert into course values (1, '网络工程原理', '网络工程原理是浙江工业大学计算机学院学生大二学习的课程' , null);
insert into course values (2, '计算机组成原理', '计算机组成原理是浙江工业大学计算机学院学生大二学习的课程',null);
insert into course values (3, '单片机原理', '单片机原理是浙江工业大学计算机学院学生大三学习的课程',null);
insert into course values (4, 'javaee程序', 'javaee程序是浙江工业大学计算机学院学生大三学习的课程',null);
insert into course values (5, '软件工程原理', '软件工程原理是浙江工业大学计算机学院学生大二学习的课程',null);
insert into course values (6, 'web应用', 'web应用是浙江工业大学计算机学院学生大二学习的课程',null);


insert into course_tag values (1, '编译语言');
insert into course_tag values (2, '网络管理');
insert into course_tag values (3, '前端程序');
insert into course_tag values (4, 'IDE');
insert into course_tag values (5, '计算机组成');
insert into course_tag values (6, '计算机网络');
insert into course_tag values (7, '网络安全');
insert into course_tag values (8, '硬件程序');


insert into course_course_tags values (1, 1);
insert into course_course_tags values (1, 2);
insert into course_course_tags values (1, 3);
insert into course_course_tags values (2, 3);
insert into course_course_tags values (2, 2);
insert into course_course_tags values (2, 1);
insert into course_course_tags values (3, 1);
insert into course_course_tags values (3, 2);
insert into course_course_tags values (3, 3);
insert into course_course_tags values (4, 1);
insert into course_course_tags values (4, 2);
insert into course_course_tags values (4, 3);
insert into course_course_tags values (5, 1);
insert into course_course_tags values (5, 2);
insert into course_course_tags values (5, 3);
insert into course_course_tags values (6, 1);
insert into course_course_tags values (6, 2);
insert into course_course_tags values (6, 3);