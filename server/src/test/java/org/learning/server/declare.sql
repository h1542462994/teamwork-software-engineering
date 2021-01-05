drop database if exists chtLearning;

create database chtLearning character set = utf8mb4 collate = utf8mb4_unicode_ci

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
    organization_id int null,
    foreign key department_fk_organization(organization_id) references organization(id)
)