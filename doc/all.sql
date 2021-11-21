drop table if exists `test`;
create table `test` (
    `id` bigint not null COMMENT 'id',
    `name` varchar(50) COMMENT '名称',
    `password` varchar(50) COMMENT '密码',
    primary key (`id`)
) engine=InnoDB default charset=utf8mb4 comment= '测试';


insert into `test` (id, name, password) values (1,'测试','password');

select * from `test`;


# Demo表
drop table if exists `demo`;
create table `demo` (
                        `id` bigint not null COMMENT 'id',
                        `name` varchar(50) COMMENT '名称',
                        primary key (`id`)
) engine=InnoDB default charset=utf8mb4 comment= '测试';
insert into `demo` (id, name) values (1,'测试');