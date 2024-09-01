
#尚筹网

#菜单建表语句
CREATE TABLE t_menu
(
    id        INT(11)      NOT NULL AUTO_INCREMENT,
    pid       INT(11),
    menu_name VARCHAR(100) NOT NULL,
    menu_url  VARCHAR(200),
    icon      VARCHAR(200),
    PRIMARY KEY (id)
);

#菜单目录结构
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('1',NULL,'系统权限菜单','glyphiconglyphicon-th-list',NULL);
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('2','1',' 控制面板','glyphiconglyphicon-dashboard','main.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('3','1','权限管理','glyphicon glyphiconglyphicon-tasks',NULL);
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('4','3',' 用户维护','glyphiconglyphicon-user','user/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('5','3',' 角色维护','glyphiconglyphicon-king','role/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('6','3',' 菜单维护','glyphiconglyphicon-lock','permission/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('7','1',' 业务审核','glyphiconglyphicon-ok',NULL);
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('8','7',' 实 名认证审核','glyphiconglyphicon-check','auth_cert/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('9','7',' 广告审核','glyphiconglyphicon-check','auth_adv/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('10','7',' 项目审核','glyphiconglyphicon-check','auth_project/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('11','1',' 业务管理','glyphiconglyphicon-th-large',NULL);
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('12','11',' 资质维护','glyphiconglyphicon-picture','cert/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('13','11',' 分类管理','glyphiconglyphicon-equalizer','certtype/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('14','11',' 流程管理','glyphiconglyphicon-random','process/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('15','11',' 广告管理','glyphiconglyphicon-hdd','advert/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('16','11',' 消息模板','glyphiconglyphicon-comment','message/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('17','11',' 项目分类','glyphiconglyphicon-list','projectType/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('18','11',' 项目标签','glyphiconglyphicon-tags','tag/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url) VALUES('19','1',' 参数管理','glyphiconglyphicon-list-alt','param/index.htm');



#角色表建表语句
use crowd;
create table t_role(
	id int(11) not null auto_increment,
	name varchar(100),
	primary key (id)
);

#改角色表字段名和约束
ALTER TABLE t_role CHANGE COLUMN NAME roleName VARCHAR(100) UNIQUE;

#角色造数
INSERT INTO t_role(id,NAME) VALUES(NULL,'shuyun');

#角色批量造数
#开启函数
SET GLOBAL log_bin_trust_function_creators=1;

DELIMITER $$

CREATE
    FUNCTION `crowd`.`insertRole`()
    RETURNS INT
    BEGIN
    
    DECLARE totalNum INT DEFAULT 200;
    DECLARE startNum INT DEFAULT 0;
    
    WHILE startNum < totalNum DO
    INSERT INTO t_role (id,roleName) 
    VALUES(NULL,CONCAT('shuyun',startNum));   
    SET startNum = startNum +1;
    END WHILE;
    RETURN startNum;
    
    END$$

DELIMITER ;
#调用
SELECT insertRole();




#admin管理员造数
INSERT INTO t_admin (id,login_acct,user_pswd,user_name,email,create_time) VALUES(NULL,'16602083320','123456','shuyun','shuyun123@qq.com',NOW());

#密码123456
UPDATE t_admin SET user_pswd='E10ADC3949BA59ABBE56E057F20F883E' WHERE id=1;

#批量插入

#开启函数
SET GLOBAL log_bin_trust_function_creators=1;

DELIMITER $$

CREATE
    FUNCTION `crowd`.`insertAdm`()
    RETURNS INT
    BEGIN
    
    DECLARE totalNum INT DEFAULT 200;
    DECLARE startNum INT DEFAULT 0;
    
    WHILE startNum < totalNum DO
    INSERT INTO t_admin (id,login_acct,user_pswd,user_name,email,create_time) 
    VALUES(NULL,CONCAT('',FLOOR(RAND()*(136000000000-13000000000+1)+13000000000)),'123456',CONCAT('shuyun',startNum),CONCAT('shuyun',startNum,'@qq.com'),NOW());   
    SET startNum = startNum +1;
    END WHILE;
    RETURN startNum;
    
    END$$

DELIMITER ;

#调用
SELECT insertAdm();





























#










