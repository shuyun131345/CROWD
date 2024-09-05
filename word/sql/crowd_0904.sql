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

#查询角色已拥有权限
SELECT * FROM t_auth WHERE id IN (SELECT auth_id FROM inner_role_auth WHERE role_id =1);



