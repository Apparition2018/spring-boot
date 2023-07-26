CREATE TABLE `DEMO`
(
    `ID`   NUMBER NOT NULL,
    `NAME` VARCHAR2(255 BYTE) NOT NULL
);
-- 创建序列，用作实现自增主键
-- https://cnblogs.com/godzzz/p/16018088.html
CREATE SEQUENCE DEMO_SEQ MINVALUE 1 NOMAXVALUE START WITH 1 INCREMENT BY 1 NOCACHE;
