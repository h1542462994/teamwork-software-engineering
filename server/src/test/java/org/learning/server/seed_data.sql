/* insert user data */
truncate table user;

insert into user values ('test', 18, 'test@outlook.com', '测试人员', '123456', false);
insert into user values ('test1', 19, 'test1@outlook.com', '测试人员1', '123456', false);
insert into user values ('test2', 20, 'test2@outlook.com', '测试人员2', '123456', true);
insert into user values ('test3', 19, 'test1@outlook.com', '测试人员1', '123456', false);
insert into user values ('test4', 20, 'test2@outlook.com', '测试人员2', '123456', true);
insert into user values ('test5', 19, 'test1@outlook.com', '测试人员1', '123456', false);
insert into user values ('test6', 20, 'test2@outlook.com', '测试人员2', '123456', true);
insert into user values ('test7', 19, 'test1@outlook.com', '测试人员1', '123456', false);
insert into user values ('test8', 20, 'test2@outlook.com', '测试人员2', '123456', true);
insert into user values ('test9', 19, 'test1@outlook.com', '测试人员1', '123456', false);
insert into user values ('test10', 20, 'test2@outlook.com', '测试人员2', '123456', true);

/* insert department data and organization data*/

set foreign_key_checks = 0;
truncate table user_org_node;
truncate table org_node;
set foreign_key_checks = 1;


set foreign_key_checks = 0;
truncate table course;
truncate table course_tag;
truncate table course_course_tags;
set foreign_key_checks = 1;

insert into course values (1,  '网络工程原理是浙江工业大学计算机学院学生大二学习的课程' , '网络工程原理', null);
insert into course values (2, '计算机组成原理是浙江工业大学计算机学院学生大二学习的课程', '计算机组成原理',  null);
insert into course values (3, '单片机原理是浙江工业大学计算机学院学生大三学习的课程', '单片机原理',  null);
insert into course values (4, 'javaee程序是浙江工业大学计算机学院学生大三学习的课程', 'javaee程序',  null);
insert into course values (5,  '软件工程原理是浙江工业大学计算机学院学生大二学习的课程', '软件工程原理', null);
insert into course values (6, 'web应用是浙江工业大学计算机学院学生大二学习的课程', 'web应用',  null);
insert into course values (7, '财务管理是在一定的整体目标下，关于资产的购置（投资），资本的融通（筹资）和经营中现金流量（营运资金），以及利润分配的管理。财务管理是企业管理的一个组成部分，它是根据财经法规制度，按照财务管理的原则，组织企业财务活动，处理财务关系的一项经济管理工作', '财务管理', null);
insert into course values (8, '市场是商品经济的范畴，是一种以商品交换为内容的经济联系形式。对于企业来说，市场是营销活动的出发点和归宿', '市场营销', null);
insert into course values (9, '国际金融由国际收支、国际汇兑、国际结算、国际信用、国际投资和国际货币体系构成，它们之间相互影响，相互制约', '国际金融', null);
insert into course values (10, '西方经济学是指产生并流行于西方国家的政治经济学范式，狭义指西方资产阶级政治经济学范式，广义包括马克思主义政治经济学范式。西方经济学与东方经济学是不同的经济学范式。西方经济学主要是范式概念，而不仅仅是地域概念。改革开放以来流行中国的新自由主义经济学也属于西方经济学。', '西方经济学', null);
insert into course values (11, '现代企业管理的基本原理、方法；具备初步解决企业管理实际问题的能力；掌握现代企业管理的知识与技能，培养适应社会经济发展和企业需要的高素质人才，促进学生全面发展。', '企业管理策略', null);
insert into course values (12, '公共财政管理》是一门综合性的课程，它既要涉及到财政学的内容，又要涉及到管理学的内容；它既要对现实政府财政管理活动实际运作状况进行反映，又不能仅仅停留在对各种规章制度和实际做法的繁琐介绍上。因此，在教学中必须注重将《财政学》和《管理学》的理论知识融会贯通于对财政管理实践活动的讨论分析之中；注重让学生认识和把握财政管理活动内容的整体性和体系感，并通过到实际部门调研增加学生对实际财政管理工作的感性认识。', '公共财政管理', null);


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

/* insert user_department, user_organization */
set foreign_key_checks = 0;
truncate table user_organization;
truncate table user_department;
set foreign_key_checks = 1;

insert into user_organization values (1, 2, 1, 'test');
insert into user_organization values (2, 2, 2, 'test1');
insert into user_organization values (3, 1, 1, 'test2');

insert into user_department values (1, 2, 1, 'test2');

