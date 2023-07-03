-- 创建数据库
use information_schema;
create database db_test default charset utf8mb4 collate utf8mb4_general_ci;
use db_test;

-- 权限相关
-- 角色表=========================================================
drop table if exists tb_security_role;

create table tb_security_role
(
  rid integer auto_increment primary key not null comment '主键',
  role_name varchar(20) unique not null comment '角色名',
  role_info varchar(50) not null comment '角色描述',
  lastupdate timestamp on update now() default now() not null comment '信息最后修改时间'
)comment '用户信息表';

insert into tb_security_role(role_name,role_info) values('admin','内置管理员');
insert into tb_security_role(role_name,role_info) values('user','内置用户');

select * from tb_security_role;

-- 用户表=========================================================
drop table if exists tb_security_user;

create table tb_security_user
(
  uid integer auto_increment primary key not null comment '主键',
  username varchar(20) unique not null comment '登录用户名',
  password varchar(50) not null comment '登录密码',
  nickname varchar(50) not null comment '昵称',
  rid integer not null comment '用户角色',
  enable enum('y','n') default 'y' not null comment '是否启用',
  lastupdate timestamp default now() not null comment '注册时间'
)comment '用户信息表';

insert into tb_security_user(username,password,nickname) values('admin','admin','内置管理员');
insert into tb_security_user(username,password,nickname) values('user','user','内置用户');
insert into tb_security_user(username,password,nickname,enable) values('test','test','测试用户','n');

select * from tb_security_user;

-- token信息=====================================================
drop table if exists tb_security_token;

create table tb_security_token
(
  token varchar(50) primary key not null comment '主键',
  token_info varchar(1000) not null comment 'token信息，json格式',
  lastupdate timestamp on update now() default now() not null comment 'token信息最后修改时间'
)comment 'token信息表';

select * from tb_security_token;