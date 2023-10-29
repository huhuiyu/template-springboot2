use db_student;

-- 用户角色表
drop table if exists tb_auth_role;

create table tb_auth_role
(
  role_id integer primary key auto_increment not null comment '角色编号' ,
  role_name varchar(20) unique not null comment '角色名称' ,
  role_info varchar(50) not null comment '角色描述' ,
  lastupdate timestamp on update now() default now() not null comment '信息最后修改时间'
)comment '用户角色表';

-- 内置角色信息
insert into tb_auth_role(role_name,role_info) values('admin','管理员');
insert into tb_auth_role(role_name,role_info) values('user','用户');

select * from tb_auth_role;

-- 托管api信息表
drop table if exists tb_auth_api;

create table tb_auth_api
(
  api_id integer primary key not null comment 'api编号' ,
  url varchar(255) not null comment 'api的url地址',
  method varchar(10) not null comment '请求的方式',
  info varchar(200) not null comment 'api描述信息',
  enable enum('y','n') default 'y' not null comment '是否启用',
  lastupdate timestamp on update now() default now() not null comment '信息修改时间',
  unique(url,method) comment 'api地址和请求方式的联合唯一约束'
)comment '托管api信息表';

select * from tb_auth_api;

-- 角色托管的api地址信息表  ====================================================================================
drop table if exists tb_auth_role_api;
create table tb_auth_role_api
(
  raid integer auto_increment primary key comment '主键',
  role_id integer comment '角色',
  api_id integer comment '托管的api',
  unique(role_id,api_id) comment '角色和托管api的联合唯一约束'
)comment '角色托管的api地址信息表';

select * from tb_auth_role_api;


-- 用户基本信息表
drop table if exists tb_auth_user;

create table tb_auth_user
(
  user_id integer primary key auto_increment not null comment '用户编号',
  username varchar(20) unique not null comment '登录名称',
  password varchar(50) not null comment '登录密码',
  salt varchar(10) not null comment '随机密码加密盐',
  enable enum('y','n') not null default 'y' comment '账号是否启用',
  role_id integer not null comment '角色编号' ,
  lastupdate timestamp default now() not null comment '用户注册时间'
)comment '用户信息表';

-- 默认用户信息
insert into tb_auth_user(username,password,salt,role_id) values('admin','d6d93e25dfd2e57854e73e25383008f4','1nexns',1);
insert into tb_auth_user(username,password,salt,role_id) values('user','bf20a03736e37345c6547ef9876c9b14','d66v76',1);

select * from tb_auth_user;

-- 用户附加信息表
drop table if exists tb_auth_user_info;

create table tb_auth_user_info
(
  user_id integer primary key not null comment '用户编号' ,
  nickname varchar(255) not null default '' comment '用户姓名' ,
  sex enum('m','f','n') not null default 'n' comment '性别，m：男，f：女，n：保密' ,
  email varchar(255) not null default '' comment '邮箱',
  phone varchar(50) not null default '' comment '手机号',
  imgurl varchar(255) not null default '' comment '头像的url地址',
  lastupdate timestamp on update now() default now() not null comment '信息修改时间'
)comment '用户附加信息表';

-- 默认用户信息
insert into tb_auth_user_info(user_id,nickname) values(1,'内置管理员');
insert into tb_auth_user_info(user_id,nickname) values(2,'内置用户');

select * from tb_auth_user_info;

