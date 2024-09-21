
#根据adminId查询已有权限信息
SELECT DISTINCT(auth_name)
FROM t_auth a
         INNER JOIN inner_role_auth b ON a.id = b.auth_id
         INNER JOIN inner_admin_role c ON b.role_id = c.roleId
WHERE c.adminId = 10001
  AND a.auth_name IS NOT NULL
  AND a.auth_name != '';

#修改t_admin表结构
ALTER TABLE t_admin MODIFY COLUMN user_pswd VARCHAR(100) NOT NULL;