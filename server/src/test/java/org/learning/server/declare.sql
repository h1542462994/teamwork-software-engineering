drop database if exists chtLearning;

create database chtLearning character set = utf8mb4 collate = utf8mb4_unicode_ci;

use chtLearning;

create table user
(
    uid      varchar(255) primary key,
    age      int          null,
    email    varchar(255) null,
    name     varchar(255) not null,
    password varchar(255) null,
    sex      boolean      null
);

create table organization
(
    id          int primary key,
    description varchar(255) null,
    name        varchar(255) null
);

create table department
(
    id              int primary key,
    description     varchar(255) null,
    name            varchar(255) null,
    organization_id int          null references organization (id)
);

create table course
(
    id   int primary key,
    info varchar(255) null,
    name varchar(255) null,
    pic  varchar(255) null
);

create table course_tag
(
    id   int primary key,
    name varchar(255) null
);

create table course_course_tags
(
    courses_id     int not null references course (id),
    course_tags_id int not null references course_tag (id),
    primary key (courses_id, course_tags_id)
);

create table user_department
(
    id            int primary key,
    level         int not null default 0,
    department_id int not null references department (id),
    user_uid      varchar(255) references user (uid)
);

create table user_organization
(
    id              int primary key,
    level           int not null default 0,
    organization_id int not null references organization (id),
    user_uid        varchar(255) references user (uid)
);

create table user_engage_department
(
    recordid     int primary key,
    personid     varchar(255) references user (uid),
    departmentid int(11) references department (id),
    starttime    datetime,
    endtime      datetime,
    reason       varchar(255),
    isagreed     boolean,
    canreapply   boolean
);

create table admin_invite_person
(
    recordid       int primary key,
    adminid        varchar(255) references user (uid),
    invitepersonid varchar(255) references user (uid),
    departmentid   int(11) references department (id),
    starttime      datetime,
    endtime        datetime,
    reason         varchar(255),
    isagreed       boolean,
    canreapply     boolean
);

create table releasecourse
(
    releasecourseid int(11) primary key,
    courseid        int(11) references chtLearning.course (id),
    ownerid         varchar(255) references chtLearning.user (uid),
    releasetime     datetime,
    classendtime    datetime,
    totaltask       int(11),
    departmentdeny  int(11)
);
create table coursenode
(
    courseid     int(11) references releasecourse(releasecourseid)primary key ,
    nodeid       int(11) primary key,
    nodename     varchar(255),
    resourcetype varchar(255),
    resourceurl  varchar(255)
);

create table courserecord
(
    releasecourseid int(11) references releasecourse (releasecourseid)primary key ,
    nodeid          int(11) references coursenode (nodeid) primary key ,
    userid          varchar(255) references chtLearning.user (uid)primary key ,
    totaltime       int(11)
);

create table userreleasedcourse
(
    userid    varchar(255) references chtLearning.user (uid) primary key,
    courseid  int(11) references releasecourse (releasecourseid) primary key,
    jointime  datetime,
    totaltask int(11)
);

create table courseadmin
(
    personid  varchar(255) references chtLearning.user (uid) primary key,
    identity  varchar(255) primary key,#只有main，sub，normal之分
    releaseid int(11) references releasecourse (releasecourseid) primary key
);