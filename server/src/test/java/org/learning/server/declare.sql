drop database if exists chtLearning;

create database chtLearning character set = utf8mb4 collate = utf8mb4_unicode_ci;

use chtLearning;

create table user (
    uid varchar(255) primary key,
    age int null,
    email varchar(255) null,
    name varchar(255) not null,
    password varchar(255) null,
    sex boolean null
);

create table organization (
    id int primary key,
    description varchar(255) null,
    name varchar(255) null
);

create table department (
    id int primary key ,
    description varchar(255) null,
    name varchar(255) null,
    organization_id int null references organization(id)
);

create table course (
    id int primary key,
    info varchar(255) null,
    name varchar(255) null,
    pic varchar(255) null
);

create table course_tag (
    id int primary key,
    name varchar(255) null
);

create table course_course_tag (
    courses_id int not null references course(id),
    course_tags_id int not null references course_tag(id),
    primary key (courses_id, course_tags_id)
);
