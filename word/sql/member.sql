#尚筹网-前台sql

#1.成员信息表 20241010-2
create table t_member(
	id int(11) not null auto_increment,
	loginacct varchar(255) not null,
	userpswd varchar(255) not null,
	username varchar(255),
	email varchar(255),
	authstatus int(4) comment '实名认证状态 0-未认证，1-申请中，2-已认证',
	usertype int(4) comment '0-个人，1-企业',
	realname varchar(255),
	cardnum varchar(255),
	accttype int(4) comment '0-企业，1-个体，2-个人，3-政府',
	primary key(id)
);

#2.成员信息表临时数据 20241010-470
INSERT INTO t_member(id,loginacct,userpswd) VALUES(1001,'16602083320','123456');





















#=======







