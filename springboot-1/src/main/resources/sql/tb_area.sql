CREATE DATABASE xiang

USE xiang

DROP TABLE tb_area

CREATE TABLE tb_area (
	area_id int(2) NOT NULL AUTO_INCREMENT,
	area_name VARCHAR(200) NOT NULL,
	priority int(2) NOT NULL DEFAULT '0',
	create_time datetime DEFAULT NULL,
	last_edit_time datetime DEFAULT NULL,
	PRIMARY KEY (area_id),
	UNIQUE KEY uk_area (area_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

DELETE FROM tb_area

insert into tb_area (area_name, priority, create_time, last_edit_time) values('东苑', 2, SYSDATE(), SYSDATE())
insert into tb_area (area_name, priority, create_time, last_edit_time) values('北苑', 1, SYSDATE(), SYSDATE())

SELECT * FROM tb_area
