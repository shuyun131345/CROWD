











--角色表建表语句
use crowd;
create table t_role(
	id int(11) not null auto_increment,
	name varchar(100),
	primary key (id)
);

--角色造数
INSERT INTO t_role(id,NAME) VALUES(NULL,'shuyun');

--角色批量造数

--开启函数
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
--调用
SELECT insertRole();




--admin管理员造数
INSERT INTO t_admin (id,login_acct,user_pswd,user_name,email,create_time) VALUES(NULL,'16602083320','123456','shuyun','shuyun123@qq.com',NOW());

--密码123456
UPDATE t_admin SET user_pswd='E10ADC3949BA59ABBE56E057F20F883E' WHERE id=1;

--批量插入

--开启函数
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

--调用
SELECT insertAdm();





























--










