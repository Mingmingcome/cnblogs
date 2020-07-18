`begin 2020年7月5日10:55:03`

#### NTP简介（What）

网络时间协议（英语：Network Time Protocol，缩写：NTP）是在数据网络潜伏时间可变的计算机系统之间通过分组交换进行时钟同步的一个网络协议，位于OSI模型的应用层。

明明说：时间同步协议

#### NTP的作用（Why）

NTP的作用是为了所有参与计算机的协调世界时（UTC）时间同步到几毫秒的误差内。

明明说：小时候家里会报点的老钟，跑了一段时间后就会慢几分钟，然后对着电视里校准。类似地，现在所有的计算机、手机都会跟时钟服务器同步，给我们提供更准确的时间。

#### CentOS基于NTP的时间同步（How）

1、安装ntp:

`yum install ntp`

2、修改/etc/ntp.conf

```
# Use public servers from the pool.ntp.org project.
# Please consider joining the pool (http://www.pool.ntp.org/join.html).

# new(新增)
server ntp1.aliyun.com prefer
server ntp2.aliyun.com

# old(原有)
server 0.centos.pool.ntp.org iburst
server 1.centos.pool.ntp.org iburst
server 2.centos.pool.ntp.org iburst
server 3.centos.pool.ntp.org iburst
```

3、设置时区

` timedatectl set-timezone Asia/Shanghai`

或者

`cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime`


4、时间同步

手工发起同步：

`ntpdate ntp1.aliyun.com`

`date`查看时间是否已经同步

启动NTP服务：

`service ntpd start`

设置开机启动：

`chkconfig ntpd on`

`end 2020年7月5日11:33:50`