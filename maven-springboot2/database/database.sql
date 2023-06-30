-- 创建数据库
use information_schema;
create database db_test default charset utf8mb4 collate utf8mb4_general_ci;
use db_test;

-- 演示用表
-- 员工部门关联 ====================================================================================
drop table if exists tb_dept;
create table tb_dept
(
  dept_id integer auto_increment primary key comment '主键，部门编号',
  dept_name varchar(20) unique not null comment '部门名称，带有唯一约束',
  dept_info varchar(50) not null comment '部门描述信息',
  lastupdate timestamp on update now() default now() not null comment '最后更新时间'
)comment '部门信息';

select * from tb_dept;

drop table if exists tb_employee;
create table tb_employee
(
  employee_id integer auto_increment primary key comment '主键，员工编号',
  dept_id integer not null  comment '所属部门编号',
  employee_name varchar(20) not null comment '员工姓名',
  phone varchar(50) not null comment '手机号码',
  lastupdate timestamp on update now() default now() not null comment '最后更新时间'
)comment '员工信息';

select * from tb_employee;

-- 部门信息
truncate table tb_dept;

insert into tb_dept(dept_name,dept_info) values
('人事部','hr，管理人员的部门')
,('开发部','包含神秘力量的部门')
,('测试部','限制包含神秘力量部门的部门');

select * from tb_dept;

-- 员工信息
truncate table tb_employee;

insert into tb_employee(dept_id,employee_name,phone) values
(1,'黑暗骑士','15973637383')
,(2,'DarkKnight','17363622707')
,(2,'huhuiyu','15080604020')
,(3,'user','17777777777')
,(2,'员工','13333333333')
,(2,'蜘蛛侠','13555555555')
,(3,'SpiderWoman','15888888888')
,(1,'超人','13666666666')
,(2,'罗夏','15999999999')
,(3,'x教授','15024681357')
,(1,'祖国人','13767676666')
,(2,'北极星','15595999995')
,(3,'金刚狼','15624661355');

select * from tb_employee;

-- 用户和令牌环
-- 用户表=========================================================
drop table if exists tb_user;

create table tb_user
(
  uid integer auto_increment primary key not null comment '主键',
  username varchar(20) unique not null comment '登录用户名',
  password varchar(50) not null comment '登录密码',
  nickname varchar(50) not null comment '昵称',
  enable enum('y','n') default 'y' not null comment '是否启用',
  lastupdate timestamp default now() not null comment '注册时间'
)comment '用户信息表';

insert into tb_user(username,password,nickname) values('admin','admin','内置管理员');
insert into tb_user(username,password,nickname) values('user','user','内置用户');
insert into tb_user(username,password,nickname,enable) values('test','test','测试用户','n');

select * from tb_user;

-- token信息=====================================================
drop table if exists tb_token;

create table tb_token
(
  token varchar(50) primary key not null comment '主键',
  token_info varchar(1000) not null comment 'token信息，json格式',
  lastupdate timestamp on update now() default now() not null comment 'token信息最后修改时间'
)comment 'token信息表';

select * from tb_token; 