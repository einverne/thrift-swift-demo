# 创建数据库
CREATE SCHEMA `spring_demo` DEFAULT CHARACTER SET utf8 ;

# 创建用户
CREATE USER 'spring_user'@'localhost' IDENTIFIED BY 'spring_password';
# 授权
GRANT ALL ON spring_demo.* TO 'spring_user'@'localhost';


