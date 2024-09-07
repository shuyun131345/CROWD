#角色与权限造数
INSERT INTO inner_role_auth (role_id, auth_id)
VALUES (3, 2);

INSERT INTO inner_role_auth (role_id, auth_id)
VALUES (3, 3);


#权限造数
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (8, '', '团队', 1);

INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (9, 'group:add', '班组1', 8);

INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (10, 'group:edit', '班组2', 8);


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













