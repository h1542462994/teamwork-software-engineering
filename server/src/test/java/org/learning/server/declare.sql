drop database if exists chtLearning;

create database chtLearning character set = utf8mb4 collate = utf8mb4_unicode_ci;

use chtLearning;

create table if not exists course_record_media
(
    id int auto_increment
        primary key,
    data varchar(255) null,
    process int not null,
    media_id int null
);

create table if not exists course_tag
(
    id int auto_increment
        primary key,
    name varchar(255) null
);

create table if not exists media
(
    id int auto_increment
        primary key,
    name varchar(255) null,
    `order` int null
);

create table if not exists org_node
(
    id int auto_increment
        primary key,
    description varchar(255) null,
    name varchar(255) null,
    parent_id int null,
    public bit not null
);

create table if not exists organization
(
    id int not null
        primary key,
    description varchar(255) null,
    name varchar(255) null
);

create table if not exists department
(
    id int not null
        primary key,
    description varchar(255) null,
    name varchar(255) null,
    organization_id int null,
    constraint department_fk_organization
        foreign key (organization_id) references organization (id)
);

create index department_fk_organization
    on department (organization_id);

create table if not exists user
(
    uid varchar(255) not null
        primary key,
    age int null,
    email varchar(255) null,
    name varchar(255) not null,
    password varchar(255) null,
    sex tinyint(1) null
);

create table if not exists course
(
    id int auto_increment
        primary key,
    info varchar(255) null,
    name varchar(255) null,
    pic varchar(255) null,
    create_time datetime(6) null,
    edit_time datetime(6) null,
    in_edit bit not null,
    owner_uid varchar(255) null,
    constraint course_fk_user_owner
        foreign key (owner_uid) references user (uid)
);

create table if not exists chapter
(
    id int auto_increment
        primary key,
    name varchar(255) null,
    `order` int default 0 null,
    course_id int null,
    constraint chapter_fk_course
        foreign key (course_id) references course (id)
);

create table if not exists course_admin_users
(
    course_id int not null,
    admin_users_uid varchar(255) not null,
    constraint course_admin_users_fk_course
        foreign key (course_id) references course (id),
    constraint course_admin_users_fk_admin_user
        foreign key (admin_users_uid) references user (uid)
);

create table if not exists course_course_tags
(
    courses_id int not null,
    course_tags_id int not null,
    constraint course_course_tags_fk_course_tag
        foreign key (course_tags_id) references course_tag (id),
    constraint course_course_tags_fk_course
        foreign key (courses_id) references course (id)
);

create table if not exists course_open
(
    id int auto_increment
        primary key,
    end_time datetime(6) null,
    is_edit bit not null,
    start_time datetime(6) null,
    course_id int null,
    org_node_id int null,
    constraint course_open_fk_org_node
        foreign key (org_node_id) references org_node (id),
    constraint course_open_fk_course
        foreign key (course_id) references course (id)
);

create table if not exists course_comment_area
(
    id int auto_increment
        primary key,
    chapter_id int null,
    course_open_id int null,
    constraint course_comment_area_fk_chapter
        foreign key (chapter_id) references chapter (id),
    constraint course_comment_area_fk_course_open
        foreign key (course_open_id) references course_open (id)
);

create table if not exists comment
(
    id int auto_increment
        primary key,
    message varchar(255) null,
    course_comment_area_id int null,
    user_uid varchar(255) null,
    user_to_uid varchar(255) null,
    constraint comment_fk_user
        foreign key (user_uid) references user (uid),
    constraint comment_fk_user_to
        foreign key (user_to_uid) references user (uid),
    constraint comment_fk_course_comment_area
        foreign key (course_comment_area_id) references course_comment_area (id)
);

create table if not exists course_record_area
(
    id int auto_increment
        primary key,
    course_open_id int null,
    user_uid varchar(255) null,
    constraint course_record_area_fk_user
        foreign key (user_uid) references user (uid),
    constraint course_record_area_fk_course_open
        foreign key (course_open_id) references course_open (id)
);

create table if not exists course_record_chapter
(
    id int auto_increment
        primary key,
    tab_index int not null,
    chapter_id int null,
    constraint course_record_chapter_fk_chapter
        foreign key (chapter_id) references chapter (id)
);

create table if not exists course_record_area_course_record_chapters
(
    course_record_area_id int not null,
    course_record_chapters_id int not null,
    constraint UK_974tns0bbt5upm2gxrd85ldls
        unique (course_record_chapters_id),
    constraint FKfm3yv7h0iw9hq1ye03f0ewhpo
        foreign key (course_record_chapters_id) references course_record_chapter (id),
    constraint FKoeg5iymabetan10vgr4jtq2r4
        foreign key (course_record_area_id) references course_record_area (id)
);

create table if not exists course_record_chapter_course_record_medias
(
    course_record_chapter_id int not null,
    course_record_medias_id int not null,
    constraint UK_r2h7xxnkghw9gbphj5llj6dfp
        unique (course_record_medias_id),
    constraint FK88yh7n0lo5asvbxt77m8dyuwk
        foreign key (course_record_medias_id) references course_record_media (id),
    constraint FKfwyyxp79bl4u59o353rht1w3d
        foreign key (course_record_chapter_id) references course_record_chapter (id)
);

create table if not exists resource
(
    id int auto_increment
        primary key,
    data varchar(255) null,
    name varchar(255) null,
    type int not null,
    course_id int null,
    constraint resource_fk_course
        foreign key (course_id) references course (id)
);

create table if not exists media_resource
(
    media_id int not null,
    resource_id int not null,
    constraint media_resource_fk_resource
        foreign key (resource_id) references resource (id),
    constraint media_resource_media_id_fk
        foreign key (media_id) references media (id)
);

create table if not exists user_department
(
    id int auto_increment
        primary key,
    level int not null,
    department_id int null,
    user_uid varchar(255) null,
    constraint user_department_fk_department
        foreign key (department_id) references department (id),
    constraint user_department_fk_user
        foreign key (user_uid) references user (uid)
);

create table if not exists user_favorite_tags
(
    user_uid varchar(255) not null,
    favorite_tags_id int not null,
    constraint user_favorite_tags_fk_course_tag
        foreign key (favorite_tags_id) references course_tag (id),
    constraint user_favorite_tags_fk_user
        foreign key (user_uid) references user (uid)
);

create table if not exists user_org_node
(
    id int auto_increment
        primary key,
    level int not null,
    org_node_id int null,
    user_uid varchar(255) null,
    constraint user_org_node_fk_org_node
        foreign key (org_node_id) references org_node (id),
    constraint user_org_node_fk_user
        foreign key (user_uid) references user (uid)
);

create table if not exists user_org_node_invitation
(
    id int auto_increment
        primary key,
    org_node_id int null,
    user_uid varchar(255) null,
    constraint user_org_node_invitation_fk_user
        foreign key (user_uid) references user (uid),
    constraint user_org_node_invitation_fk_org_node
        foreign key (org_node_id) references org_node (id)
);

create table if not exists user_organization
(
    id int auto_increment
        primary key,
    level int not null,
    organization_id int null,
    user_uid varchar(255) null,
    constraint user_organization_fk_user
        foreign key (user_uid) references user (uid),
    constraint user_organization_fk_organization
        foreign key (organization_id) references organization (id)
);

create table if not exists user_organization_invitation
(
    id int auto_increment
        primary key,
    inverse bit not null,
    state int not null,
    organization_id int null,
    user_uid varchar(255) null,
    constraint FK86eexsueynnqibnmh6wy9sriw
        foreign key (organization_id) references organization (id),
    constraint FKrtt2etpy5mw7keavank98kh8l
        foreign key (user_uid) references user (uid)
);

create table if not exists user_star_courses
(
    user_uid varchar(255) not null,
    star_courses_id int not null,
    constraint user_star_courses_user
        foreign key (user_uid) references user (uid),
    constraint user_star_courses_course
        foreign key (star_courses_id) references course (id)
);

