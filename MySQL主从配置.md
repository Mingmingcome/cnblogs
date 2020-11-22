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



Slave failed to initialize relay log info structure from the repository




``` 
2020-09-21T02:12:11.668970Z 26 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 5, Error_code: MY-002061
2020-09-21T02:13:11.680100Z 26 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 6, Error_code: MY-002061
2020-09-21T02:14:11.699180Z 26 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 7, Error_code: MY-002061
2020-09-21T02:15:11.709678Z 26 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 8, Error_code: MY-002061
2020-09-21T02:16:11.724528Z 26 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 9, Error_code: MY-002061
2020-09-21T02:17:11.736252Z 26 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 10, Error_code: MY-002061
2020-09-21T02:18:11.746344Z 26 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 11, Error_code: MY-002061
2020-09-21T02:19:39.143623Z 23 [System] [MY-010597] [Repl] 'CHANGE MASTER TO FOR CHANNEL '' executed'. Previous state master_host='localhost', master_port= 3306, master_log_file='', master_log_pos= 4, master_bind=''. New state master_host='localhost', master_port= 3306, master_log_file='msyql-bin.000008', master_log_pos= 6489, master_bind=''.
2020-09-21T02:19:48.897659Z 28 [Warning] [MY-010897] [Repl] Storing MySQL user name or password information in the master info repository is not secure and is therefore not recommended. Please consider using the USER and PASSWORD connection options for START SLAVE; see the 'START SLAVE Syntax' in the MySQL Manual for more information.
2020-09-21T02:19:48.913207Z 28 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 1, Error_code: MY-002061
2020-09-21T02:20:48.919742Z 28 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 2, Error_code: MY-002061
2020-09-21T02:21:48.931887Z 28 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@localhost:3306' - retry-time: 60  retries: 3, Error_code: MY-002061
2020-09-21T02:22:34.752599Z 23 [System] [MY-010597] [Repl] 'CHANGE MASTER TO FOR CHANNEL '' executed'. Previous state master_host='localhost', master_port= 3306, master_log_file='', master_log_pos= 4, master_bind=''. New state master_host='192.168.102.148', master_port= 3306, master_log_file='msyql-bin.000008', master_log_pos= 6489, master_bind=''.
2020-09-21T02:22:39.095569Z 30 [Warning] [MY-010897] [Repl] Storing MySQL user name or password information in the master info repository is not secure and is therefore not recommended. Please consider using the USER and PASSWORD connection options for START SLAVE; see the 'START SLAVE Syntax' in the MySQL Manual for more information.
2020-09-21T02:22:39.228914Z 30 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 1, Error_code: MY-001130
2020-09-21T02:23:39.238597Z 30 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 2, Error_code: MY-001130
2020-09-21T02:24:39.244565Z 30 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 3, Error_code: MY-001130
2020-09-21T02:25:39.460918Z 30 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 4, Error_code: MY-002061
2020-09-21T02:27:19.639564Z 23 [System] [MY-010597] [Repl] 'CHANGE MASTER TO FOR CHANNEL '' executed'. Previous state master_host='192.168.102.148', master_port= 3306, master_log_file='', master_log_pos= 4, master_bind=''. New state master_host='192.168.102.148', master_port= 3306, master_log_file='msyql-bin.000008', master_log_pos= 6489, master_bind=''.
2020-09-21T02:27:44.599184Z 32 [Warning] [MY-010897] [Repl] Storing MySQL user name or password information in the master info repository is not secure and is therefore not recommended. Please consider using the USER and PASSWORD connection options for START SLAVE; see the 'START SLAVE Syntax' in the MySQL Manual for more information.
2020-09-21T02:27:44.611172Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 1, Error_code: MY-002061
2020-09-21T02:28:44.617038Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 2, Error_code: MY-002061
2020-09-21T02:29:44.627267Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 3, Error_code: MY-002061
2020-09-21T02:30:44.644815Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 4, Error_code: MY-002061
2020-09-21T02:31:44.660000Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 5, Error_code: MY-002061
2020-09-21T02:32:44.676447Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 6, Error_code: MY-002061
2020-09-21T04:47:39.982982Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 7, Error_code: MY-002061
2020-09-21T04:48:40.000860Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 8, Error_code: MY-002061
2020-09-21T04:49:40.010207Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 9, Error_code: MY-002061
2020-09-21T04:50:40.020233Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 10, Error_code: MY-002061
2020-09-21T04:51:40.033364Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 11, Error_code: MY-002061
2020-09-21T04:52:40.044491Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 12, Error_code: MY-002061
2020-09-21T04:53:40.052273Z 32 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 13, Error_code: MY-001130
2020-09-21T04:54:37.234649Z 23 [System] [MY-010597] [Repl] 'CHANGE MASTER TO FOR CHANNEL '' executed'. Previous state master_host='192.168.102.148', master_port= 3306, master_log_file='', master_log_pos= 4, master_bind=''. New state master_host='192.168.102.148', master_port= 3306, master_log_file='msyql-bin.000008', master_log_pos= 6489, master_bind=''.
2020-09-21T04:54:43.884182Z 34 [Warning] [MY-010897] [Repl] Storing MySQL user name or password information in the master info repository is not secure and is therefore not recommended. Please consider using the USER and PASSWORD connection options for START SLAVE; see the 'START SLAVE Syntax' in the MySQL Manual for more information.
2020-09-21T04:54:43.899411Z 34 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 1, Error_code: MY-002061
2020-09-21T04:55:43.909784Z 34 [ERROR] [MY-010584] [Repl] Slave I/O for channel '': error connecting to master 'slave@192.168.102.148:3306' - retry-time: 60  retries: 2, Error_code: MY-002061
2020-09-21T04:55:51.946766Z 23 [System] [MY-010597] [Repl] 'CHANGE MASTER TO FOR CHANNEL '' executed'. Previous state master_host='192.168.102.148', master_port= 3306, master_log_file='msyql-bin.000008', master_log_pos= 6489, master_bind=''. New state master_host='192.168.102.148', master_port= 3306, master_log_file='msyql-bin.000008', master_log_pos= 6489, master_bind=''.
2020-09-21T04:56:03.978055Z 23 [System] [MY-010597] [Repl] 'CHANGE MASTER TO FOR CHANNEL '' executed'. Previous state master_host='192.168.102.148', master_port= 3306, master_log_file='', master_log_pos= 4, master_bind=''. New state master_host='192.168.102.148', master_port= 3306, master_log_file='msyql-bin.000008', master_log_pos= 6489, master_bind=''.
2020-09-21T04:56:07.803991Z 36 [Warning] [MY-010897] [Repl] Storing MySQL user name or password information in the master info repository is not secure and is therefore not recommended. Please consider using the USER and PASSWORD connection options for START SLAVE; see the 'START SLAVE Syntax' in the MySQL Manual for more information.
2020-09-21T04:56:07.835255Z 36 [System] [MY-010562] [Repl] Slave I/O thread for channel '': connected to master 'slave@192.168.102.148:3306',replication started in log 'msyql-bin.000008' at position 6489
```







