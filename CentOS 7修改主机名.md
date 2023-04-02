`begin 2020年7月13日00:10:34`

#### CentOS 7中的/etc/hosts文件

hosts文件是一个操作系统文件，记录了主机名到IP地址的映射。在以前，计算机还没有那么多的时候，hosts就是现在的DNS，用户只需要定时更新hosts就可以愉快地连接到主机名指定服务器上了。

```
127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4
::1         localhost localhost.localdomain localhost6 localhost6.localdomain6
```

127.0.0.1表示ipv4的本地地址，后面跟着用若干（n>=1）空格隔开的主机名或域名及主机别名

而::1表示的时ipv6的本地地址，也就是0000:0000:0000:0000:0000:0000:0000:0001，同样跟着若干（n>=1）空格隔开的主机名或域名及主机别名

#### hosts用途

1、科学|上网：某些已经被屏蔽的域名访问，可以在通过hosts解析成ip地址，直接连接。

2、双机互联：如前后端分离开发，在本地联调时修改hosts。局域网内监控如grafana，没有申请域名的情况下，可通过本地修改hosts访问。

#### CentOS 7修改主机名

1、查看当前主机名：

`hostname`

更多使用方法：`hostname -help`

2、修改主机名：

`hostnamectl set-hostname [hostname]`

3、修改/etc/hosts(可选)：

```
127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4 houming
::1         localhost localhost.localdomain localhost6 localhost6.localdomain6 houming
```

再次使用`hostname`可验证

`end 2020年7月13日01:12:47`
