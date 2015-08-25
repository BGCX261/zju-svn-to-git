-- my.ini 's content
-- [mysql]
-- default-character-set=gbk
-- [mysqld]
-- default-character-set=utf8
-- default-storage-engine=innodb

--==================================
--  create database
--==================================
DROP DATABASE IF EXISTS `${database.name}`;
CREATE DATABASE `${database.name}`;

--==================================
--  use database
--==================================
USE `${database.name}`;

--==================================
--  create user
--==================================
GRANT ALL ON `${database.name}`.* TO '${database.username}'@'localhost' IDENTIFIED BY '${database.password}';
GRANT ALL ON `${database.name}`.* TO '${database.username}'@'%' IDENTIFIED BY '${database.password}';
