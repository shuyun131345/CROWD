#尚筹网


#菜单与权限关系表
#建表语句
CREATE TABLE crowd.inner_menu_auth
(
    id      INT(11) NOT NULL AUTO_INCREMENT,
    menu_id INT(11) NOT NULL,
    auth_id INT(11) NOT NULL,
    PRIMARY KEY (id)
);

#造数
INSERT INTO inner_menu_auth (id,menu_id,auth_id) VALUES(NULL,2,1);
INSERT INTO inner_menu_auth (id,menu_id,auth_id) VALUES(NULL,2,2);
INSERT INTO inner_menu_auth (id,menu_id,auth_id) VALUES(NULL,2,3);


#角色与权限关系表，建表语句
CREATE TABLE crowd.inner_role_auth
(
    id      INT(11) NOT NULL AUTO_INCREMENT,
    role_id INT(11),
    auth_id INT(11),
    PRIMARY KEY (id)
);

#角色与权限造数
INSERT INTO inner_role_auth (role_id,auth_id)
VALUES(1,2);
INSERT INTO inner_role_auth (role_id,auth_id)
VALUES(1,3);
INSERT INTO inner_role_auth (role_id, auth_id)
VALUES (3, 2);
INSERT INTO inner_role_auth (role_id, auth_id)
VALUES (3, 3);

#查询角色已拥有权限
SELECT * FROM t_auth WHERE id IN (SELECT auth_id FROM inner_role_auth WHERE role_id =1);

#权限表，建表语句
CREATE TABLE t_auth
(
    id          INT(11) NOT NULL AUTO_INCREMENT,
    auth_name   VARCHAR(200) DEFAULT NULL,
    title       VARCHAR(200) DEFAULT NULL,
    category_id INT(11)      DEFAULT NULL,
    PRIMARY KEY (id)
);

#权限表造数
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (1, '', '用户模块', NULL);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (2, 'user:delete', '删除', 1);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (3, 'user:get', '查询', 1);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (4, '', '角色模块', NULL);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (5, 'role:delete', '删除', 4);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (6, 'role:get', '查询', 4);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (7, 'role:add', '新增', 4);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (8, '', '团队', 1);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (9, 'group:add', '班组1', 8);
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (10, 'group:edit', '班组2', 8);



#改管理员数据
UPDATE inner_admin_role SET adminId=10001;
#管理员角色信息表


#根据管理员id查询其拥有角色和未拥有角色
SELECT id,roleName  FROM t_role a WHERE id IN( SELECT roleId FROM inner_admin_role WHERE adminId=1);
SELECT id,roleName  FROM t_role a WHERE id NOT IN( SELECT roleId FROM inner_admin_role WHERE adminId=1);


#建表语句
CREATE TABLE inner_admin_role
(
    id      INT(11) NOT NULL AUTO_INCREMENT,
    adminId INT(11),
    roleId  INT(11),
    PRIMARY KEY (id)
);

#造数
INSERT INTO inner_admin_role (adminId,roleId) VALUES(1,1);
INSERT INTO inner_admin_role (adminId,roleId) VALUES(1,2);
INSERT INTO inner_admin_role (adminId,roleId) VALUES(1,3);
INSERT INTO inner_admin_role (adminId,roleId) VALUES(1,4);
INSERT INTO inner_admin_role (adminId,roleId) VALUES(1,5);
INSERT INTO inner_admin_role (adminId,roleId) VALUES(1,6);




#菜单维护
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
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('1', NULL, '系统权限菜单', 'glyphicon glyphicon-th-list', NULL);
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('2', '1', ' 控制面板', 'glyphicon glyphicon-dashboard', 'main.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('3', '1', '权限管理', 'glyphicon glyphiconglyphicon-tasks', NULL);
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('4', '3', ' 用户维护', 'glyphicon glyphicon-user', 'user/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('5', '3', ' 角色维护', 'glyphicon glyphicon-king', 'role/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('6', '3', ' 菜单维护', 'glyphicon glyphicon-lock', 'permission/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('7', '1', ' 业务审核', 'glyphicon glyphicon-ok', NULL);
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('8', '7', ' 实名认证审核', 'glyphicon glyphicon-check', 'auth_cert/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('9', '7', ' 广告审核', 'glyphicon glyphicon-check', 'auth_adv/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('10', '7', ' 项目审核', 'glyphicon glyphicon-check', 'auth_project/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('11', '1', ' 业务管理', 'glyphicon glyphicon-th-large', NULL);
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('12', '11', ' 资质维护', 'glyphicon glyphicon-picture', 'cert/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('13', '11', ' 分类管理', 'glyphicon glyphicon-equalizer', 'certtype/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('14', '11', ' 流程管理', 'glyphicon glyphicon-random', 'process/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('15', '11', ' 广告管理', 'glyphicon glyphicon-hdd', 'advert/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('16', '11', ' 消息模板', 'glyphicon glyphicon-comment', 'message/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('17', '11', ' 项目分类', 'glyphicon glyphicon-list', 'projectType/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('18', '11', ' 项目标签', 'glyphicon glyphicon-tags', 'tag/index.htm');
INSERT INTO t_menu (id, pid, menu_name, icon, menu_url)
VALUES ('19', '1', ' 参数管理', 'glyphicon glyphicon-list-alt', 'param/index.htm');


#菜单表调整节点名唯一
ALTER TABLE t_menu
    ADD UNIQUE (menu_name);


#角色表建表语句
use crowd;
create table t_role
(
    id   int(11) not null auto_increment,
    name varchar(100),
    primary key (id)
);

#改角色表字段名和约束
ALTER TABLE t_role
    CHANGE COLUMN NAME roleName VARCHAR(100) UNIQUE;

#角色造数
INSERT INTO t_role(id, NAME)
VALUES (NULL, 'shuyun');

#角色批量造数
#开启函数
SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $$

CREATE FUNCTION `crowd`.`insertRole`()
    RETURNS INT
BEGIN

    DECLARE totalNum INT DEFAULT 200;
    DECLARE startNum INT DEFAULT 0;

    WHILE startNum < totalNum
        DO
            INSERT INTO t_role (id, roleName)
            VALUES (NULL, CONCAT('shuyun', startNum));
            SET startNum = startNum + 1;
        END WHILE;
    RETURN startNum;

END$$

DELIMITER ;
#调用
SELECT insertRole();


#admin管理员造数
INSERT INTO t_admin (id, login_acct, user_pswd, user_name, email, create_time)
VALUES (NULL, '16602083320', '123456', 'shuyun', 'shuyun123@qq.com', NOW());

#密码123456
UPDATE t_admin
SET user_pswd='E10ADC3949BA59ABBE56E057F20F883E'
WHERE id = 1;

#批量插入

#开启函数
SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $$

CREATE FUNCTION `crowd`.`insertAdm`()
    RETURNS INT
BEGIN

    DECLARE totalNum INT DEFAULT 200;
    DECLARE startNum INT DEFAULT 0;

    WHILE startNum < totalNum
        DO
            INSERT INTO t_admin (id, login_acct, user_pswd, user_name, email, create_time)
            VALUES (NULL, CONCAT('', FLOOR(RAND() * (136000000000 - 13000000000 + 1) + 13000000000)), '123456',
                    CONCAT('shuyun', startNum), CONCAT('shuyun', startNum, '@qq.com'), NOW());
            SET startNum = startNum + 1;
        END WHILE;
    RETURN startNum;

END$$

DELIMITER ;

#调用
SELECT insertAdm();


#










