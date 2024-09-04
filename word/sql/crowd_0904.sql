CREATE TABLE t_auth
(
    id          INT(11) NOT NULL AUTO_INCREMENT,
    auth_name   VARCHAR(200) DEFAULT NULL,
    title       VARCHAR(200) DEFAULT NULL,
    category_id INT(11)      DEFAULT NULL,
    PRIMARY KEY (id)
);

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


