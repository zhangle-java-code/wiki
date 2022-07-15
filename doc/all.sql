drop table if exists `test`;
create table `test` (
    `id` bigint not null COMMENT 'id',
    `name` varchar(50) COMMENT '名称',
    `password` varchar(50) COMMENT '密码',
    primary key (`id`)
) engine=InnoDB default charset=utf8mb4 comment= '测试';


insert into `test` (id, name, password) values (1,'测试','password');
insert into `test` (id, name, password) values (3,'测试1','password');

select * from `test`;


# Demo表
drop table if exists `demo`;
create table `demo` (
                        `id` bigint not null COMMENT 'id',
                        `name` varchar(50) COMMENT '名称',
                        `password` varchar(50) COMMENT '名称',
                        primary key (`id`)
) engine=InnoDB default charset=utf8mb4 comment= '测试';
insert into `demo` (id, name) values (1,'测试');


# 电子书表

drop table if exists `ebook`;
create table `ebook` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description` varchar(200) comment '描述',
    `cover` varchar(200) comment '封面',
    `doc_count` int comment '文档数',
    `view_count` int comment '阅读数',
    `vote_count` int comment '点赞数',
    primary key (`id`)
) engine=InnoDB default charset=utf8mb4 comment= '电纸书';

insert into`ebook`(id, name, description) values(1,'Spring Boot入门教程','零基础入门Java开发, 企业级应
用开发最佳首选框架');
insert into`ebook`(id, name, description) values(2, 'Vue入门教程', '零基础入门Vue开发, 企业级应用开发最佳
首选框架');
insert into`ebook`(id, name, description) values(3, 'Python入门教程', '零基础入门Python开发, 企业级应用
开发最佳首选框架');
insert into`ebook`(id, name, description) values(4, 'Mysql入门教程', '零基础入门Mysql开发, 企业级应用开发
最佳首选框架');
insert into`ebook`(id, name, description) values(5,'Oracle入门教程','零基础入门Oracle开发, 企业级应用
开发最佳首选框架');
insert into`ebook`(id, name, description) values(6,'前端入门教程','大前端入门库康佳');


# 电子书分类表

drop table if exists `category`;
create table `category` (
    `id` bigint not null comment 'id',
    `parent` bigint not null default 0 comment '父级id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '排序',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment= '电子书分类';

insert into `category` (id, parent, name, sort) values (100, 000, '前端开发', 100);
insert into `category` (id, parent, name, sort) values (101, 100, 'Vue', 101);
insert into `category` (id, parent, name, sort) values (102, 100, 'Vue', 102);
insert into `category` (id, parent, name, sort) values (200, 000, 'Vue', 200);
insert into `category` (id, parent, name, sort) values (201, 200, 'Vue', 201);
insert into `category` (id, parent, name, sort) values (202, 200, 'Vue', 202);



# 文档表

drop table if exists `doc`;
create  table `doc` (
    `id` bigint not null comment 'id',
    `ebook_id` bigint not null default 0 comment '电子书id',
    `parent` bigint not null default 0 comment '父级id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '排序',
    `view_count` int default 0 comment '阅读数',
    `vote_count` int default 0 comment '点赞数',
    primary key (`id`)
) engine =innodb default charset=utf8mb4 comment= '文档';

insert into `doc` (id, ebook_id, parent, name, sort,view_count,vote_count) values (1, 1, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort,view_count,vote_count) values (2, 1, 1, '文档2', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort,view_count,vote_count) values (3, 1, 0, '文档2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort,view_count,vote_count) values (4, 1, 3, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort,view_count,vote_count) values (5, 1, 3, '文档1', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort,view_count,vote_count) values (6, 1, 5, '文档1', 1, 0, 0);

# 文档内容

drop table if exists `content`;
create table `content` (
    `id` bigint not null comment '文档id',
    `content` mediumtext not null comment '内容',
    primary key (`id`)
) engine=innodb default charset =utf8mb4 comment '文档内容';


#  用户名 unique key 唯一
# ?varchar和char区别
# ?unique key 索引中查看
drop table if exists `user`;

create table `user` (
    `id` bigint not null comment 'ID',
    `login_name` varchar(50) not null comment '登录名',
    `name` varchar(50) comment '昵称',
    `password` char(32) not null comment '密码'   ,
    primary key(`id`),
    unique key `login_name_unique` (`login_name`)
) engine=innodb default charset =utf8mb4 comment='用户';


insert into `user` (id, login_name, name, password) values (1,'test', '测试','测试');



# 电子书快照表
# !auto_increment 分库分表不适用
drop table if exists `ebook_snapshot`;
create table `ebook_snapshot` (
    `id` bigint auto_increment not null comment 'id',
    `ebook_id` bigint not null default 0 comment '电子书id',
    `date` date not null comment '快照日期',
    `view_count` int not null default 0 comment '阅读数',
    `vote_count` int not null default 0 comment '点赞数',
    `view_increase` int not null default 0 comment '阅读数增长',
    `vote_increase` int not null default 0 comment '点赞数增长',
    primary key (`id`),
    unique key `ebook_id_date_unique` (`ebook_id`,`date`)
)engine=innodb default charset=utf8mb4 comment= '电子书快照表';





















