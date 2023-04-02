`2019年6月13日20:13:21`

## MySQL8.0 下载安装启动(Windows10)

#### 下载

下载地址：<a>https://dev.mysql.com/downloads/mysql/8.0.html</a>

现在的版本是：MySQL Community Server 8.0.16

#### 解压

解压到安装目录，比如F盘根目录，`F:\mysql-8.0.16-winx64`

#### 创建my.ini

my.ini是MySQL安装的配置文件

```ini
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
[mysqld]
# 设置3306端口
port=3306
# 设置mysql的安装目录
basedir=D:\\develop\\software\\mysql-8.0.13-winx64
# 设置 mysql数据库的数据的存放目录，MySQL 8+ 不需要以下配置，系统自己生成即可，否则有可能报错
# datadir=D:\\develop\\software\\mysql-8.0.13-winx64\\data
# 允许最大连接数
max_connections=20
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8mb4
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```

#### 初始化

以管理员权限打开cmd，在`F:\mysql-8.0.16-winx64\bin`目录下执行：

`mysqld --defaults-file=F:\mysql-8.0.16-winx64\my.ini --initialize --console`

控制台有输出如下，说明安装成功：

```dos
F:\mysql-8.0.16-winx64\bin>mysqld --defaults-file=F:\mysql-8.0.16-winx64\my.ini --initialize --console  
2019-06-13T13:05:12.739945Z 0 [System] [MY-013169] [Server] F:\mysql-8.0.16-winx64\bin\mysqld.exe (mysqld 8.0.16) initializing of server in progress as process 16556  
2019-06-13T13:05:53.869171Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: 8WEM&pMQk#xR  
2019-06-13T13:06:06.142279Z 0 [System] [MY-013170] [Server] F:\mysql-8.0.16-winx64\bin\mysqld.exe (mysqld 8.0.16) initializing of server has completed  
```

这里`root@localhost: 8WEM&pMQk#xR`中`8WEM&pMQk#xR`就是数据库的初始密码。稍后需要更改。

#### 启动、关闭

启动MySQL server，只需要执行`mysqld`即可，`mysqld --console`可以在控制台输出日志。

```dos
F:\mysql-8.0.16-winx64\bin>mysqld --console
2019-06-14T11:20:12.875518Z 0 [System] [MY-010116] [Server] F:\mysql-8.0.16-winx64\bin\mysqld.exe (mysqld 8.0.16) starting as process 13788
2019-06-14T11:20:17.477755Z 0 [Warning] [MY-010068] [Server] CA certificate ca.pem is self signed.
2019-06-14T11:20:17.998981Z 0 [System] [MY-010931] [Server] F:\mysql-8.0.16-winx64\bin\mysqld.exe: ready for connections. Version: '8.0.16'  socket: ''  port: 3306  MySQL Community Server - GPL.
2019-06-14T11:20:18.071514Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Bind-address: '::' port: 33060
```

在命令行中只需“CRTL + C”就可以关闭数据库，也可以使用`mysqladmin -u root shutdown`。


#### 登录

使用`mysql -uroot -p`登录，输入上面的那个密码`8WEM&pMQk#xR`即可

```
F:\mysql-8.0.16-winx64\bin>mysql -uroot -p
Enter password: ************
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.16

Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>
```

#### 修改密码


`ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '你的密码';`

注意：`mysql8新的密码策略，很多mysql客户端工具不支持，所以用原来的密码策略`。

`2019年6月14日20:00:06`