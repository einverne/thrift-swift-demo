# 创建数据库
CREATE SCHEMA `spring_demo` DEFAULT CHARACTER SET utf8 ;

# 创建用户
CREATE USER 'spring_user'@'localhost' IDENTIFIED BY 'spring_password';
# 授权
GRANT ALL ON spring_demo.* TO 'spring_user'@'localhost';


CREATE TABLE `spring_demo`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(256) NOT NULL,
  `avatar` VARCHAR(256) NULL,
  `age` INT NULL DEFAULT 18,
  `gender` VARCHAR(1) NULL DEFAULT 'o',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC));
