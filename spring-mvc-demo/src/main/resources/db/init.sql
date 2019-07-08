
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(256) NOT NULL,
  `avatar` VARCHAR(256) NULL,
  `age` INT NULL DEFAULT 18,
  `gender` CHAR(1) NULL DEFAULT 'o',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC));
