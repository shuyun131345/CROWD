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


#3.分类信息表 20241017-470
CREATE TABLE t_type
(
    id     INT(11) NOT NULL AUTO_INCREMENT,
    NAME   VARCHAR(255) COMMENT '分类名称',
    remark VARCHAR(255) COMMENT '分类介绍',
    PRIMARY KEY (id)
);

#4.项目分类中间表 20241017-470
CREATE TABLE t_project_type
(
    id        INT NOT NULL AUTO_INCREMENT,
    projectid INT(11),
    typeid    INT(11),
    PRIMARY KEY (id)
);

#5.标签表 20241017-470
CREATE TABLE t_tag
(
    id   INT(11) NOT NULL AUTO_INCREMENT,
    pid  INT(11),
    NAME VARCHAR(255),
    PRIMARY KEY (id)
);

#6.项目标签中间表 20241017-470
CREATE TABLE t_project_tag
(
    id        INT(11) NOT NULL AUTO_INCREMENT,
    projectid INT(11),
    tagid     INT(11),
    PRIMARY KEY (id)
);

#7.项目表 20241017-470
CREATE TABLE t_project
(
    id                  INT(11) NOT NULL AUTO_INCREMENT,
    project_name        VARCHAR(255) COMMENT '项目名称',
    project_description VARCHAR(255) COMMENT '项目描述',
    money               BIGINT(11) COMMENT '筹集金额',
    DAY                 INT(11) COMMENT '筹集天数',
    STATUS              INT(4) COMMENT '0-即将开始，1-众筹中，2-众筹成功，3-众筹失败',
    deploydate          VARCHAR(10) COMMENT '项目发起时间',
    supportmoney        BIGINT(11) COMMENT '已筹集到的金额',
    supporter           INT(11) COMMENT '支持人数',
    COMPLETION          INT(3) COMMENT '百分比完成度',
    memberid            INT(11) COMMENT '发起人的会员 id',
    createdate          VARCHAR(19) COMMENT '项目创建时间',
    follower            INT(11) COMMENT '关注人数',
    header_picture_path VARCHAR(255) COMMENT '头图路径',
    PRIMARY KEY (id)
);

#8.项目表项目详情图片表 20241017-470
CREATE TABLE t_project_item_pic
(
    id            INT(11) NOT NULL AUTO_INCREMENT,
    projectid     INT(11),
    item_pic_path VARCHAR(255),
    PRIMARY KEY (id)
);

#9.项目发起人信息表 20241017-470
CREATE TABLE t_member_launch_info
(
    id                 INT(11) NOT NULL AUTO_INCREMENT,
    memberid           INT(11) COMMENT '会员 id',
    description_simple VARCHAR(255) COMMENT '简单介绍',
    description_detail VARCHAR(255) COMMENT '详细介绍',
    phone_num          VARCHAR(255) COMMENT '联系电话',
    service_num        VARCHAR(255) COMMENT '客服电话',
    PRIMARY KEY (id)
);


#10.回报信息表 20241017-470
CREATE TABLE t_return
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    projectid        INT(11),
    TYPE             INT(4) COMMENT '0 - 实物回报， 1 虚拟物品回报',
    supportmoney     INT(11) COMMENT '支持金额',
    content          VARCHAR(255) COMMENT '回报内容',
    COUNT            INT(11) COMMENT '回报产品限额，“0”为不限回报数量',
    signalpurchase   INT(11) COMMENT '是否设置单笔限购',
    purchase         INT(11) COMMENT '具体限购数量',
    freight          INT(11) COMMENT '运费，“0”为包邮',
    invoice          INT(4) COMMENT '0 - 不开发票， 1 - 开发票',
    returndate       INT(11) COMMENT '项目结束后多少天向支持者发送回报',
    describ_pic_path VARCHAR(255) COMMENT '说明图片路径',
    PRIMARY KEY (id)
);


#11.发起人确认信息表 20241017-470
CREATE TABLE t_member_confirm_info
(
    id       INT(11) NOT NULL AUTO_INCREMENT,
    memberid INT(11) COMMENT '会员 id',
    paynum   VARCHAR(200) COMMENT '易付宝企业账号',
    cardnum  VARCHAR(200) COMMENT '法人身份证号',
    PRIMARY KEY (id)
);

#=======







