#测试表
CREATE TABLE crowd_test (
	id INT(100) NOT NULL AUTO_INCREMENT,
	user_name VARCHAR(100) NOT NULL ,
	pwd VARCHAR(100) NOT NULL,
	email VARCHAR(100),
	PRIMARY KEY (id) 
)

#造数
INSERT INTO crowd_test(id,user_name,pwd,email) VALUES(1002,'shuyun','123456','shuyun@qq.com');