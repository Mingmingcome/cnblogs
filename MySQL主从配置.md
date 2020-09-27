``` SQL
CREATE USER 'slave'@'192.168.102.148' IDENTIFIED BY 'slave'; 
GRANT REPLICATION SLAVE ON *.* TO 'slave'@'192.168.102.148';
flush privileges;
```


（1） mysqldump常用于数据库的备份与还原，在备份的过程中我们可以根据自己的实际情况添加以上任何参数，假设有数据库test_db，执行以下命令，即可完成对整个数据库的备份：

    mysqldump -u root -p test_db > test_db.sql    

（2）如要对数据进行还原，可执行如下命令：

    mysql -u username -p test_db < test_db.sql    

（3）还原数据库操作还可以使用以下方法：

    mysql> sourcetest_db.sql   

    # 设置主服务器ip，同步账号密码，同步位置
change master to master_host='192.168.102.148',master_user='slave',master_password='slave',master_log_file='msyql-bin.000008',master_log_pos=6489;

# 开启同步功能
start slave;

2020-09-21T02:27:44.611172Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 1, Error_code: MY-002061

https://dba.stackexchange.com/questions/218207/mysql-database-replication-error-my-002061






