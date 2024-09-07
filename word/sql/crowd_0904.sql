

#角色与权限造数
INSERT INTO inner_role_auth (role_id,auth_id)
VALUES(3,2);

INSERT INTO inner_role_auth (role_id,auth_id)
VALUES(3,3);


#权限造数
INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (8, '', '团队', 1);

INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (9, 'group:add', '班组1', 8);

INSERT INTO t_auth(id, auth_name, title, category_id)
VALUES (10, 'group:edit', '班组2', 8);

