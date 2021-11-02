`2020年6月27日12:10:26`

#### 安装

下载
```
wget https://mirrors.tuna.tsinghua.edu.cn/apache/zookeeper/zookeeper-3.6.1/apache-zookeeper-3.6.1-bin.tar.gz
```

[luhaoming@localhost bin]$ ./zkServer.sh start
Error: JAVA_HOME is not set and java could not be found in PATH.


#### 配置Java环境

https://wxnacy.com/2018/12/27/centos7-install-java11/

1、查看yum库中都有哪些jdk版本
OpenJDK
首先搜索可安装的 JDK
$ sudo yum search java-11
从结果中我们可以找出两个条目
java-11-openjdk.x86_64 : OpenJDK Runtime Environment 11
java-11-openjdk-devel.x86_64 : OpenJDK Development Environment 11
他们分别是 JRE 和 JDK，根据你的需求安装即可，比如安装 JRE

$ sudo yum install java-11-openjdk -y
安装目录为
/usr/lib/jvm/java-11-openjdk-11.0.1.13-3.el7_6.x86_64
查看版本

$ java -version

openjdk version "11.0.1" 2018-10-16 LTS
OpenJDK Runtime Environment 18.9 (build 11.0.1+13-LTS)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.1+13-LTS, mixed mode, sharing)

#### 无法加载主类错误

https://blog.csdn.net/jiangxiulilinux/article/details/96433560

#### 集群 

https://zookeeper.apache.org/doc/current/zookeeperStarted.html

https://zookeeper.apache.org/doc/current/zookeeperObservers.html

Observer模式：
作为Observer的服务器的配置文件zoo.cfg需要添加：
peerType=observer

集群内所有服务器的配置文件zoo.cfg需要添加：
initLimit=5
syncLimit=2
server.1=192.168.0.199:2888:3888:observer
server.2=192.168.0.188:2888:3888
server.3=192.168.0.177:2888:3888
server.4=192.168.0.166:2888:3888

创建myid
vi ${dataDir}/myid
配置文件zoo.cfg里只记录server.x中x，dataDir默认为/tmp/zookeeper

使用zkServer.sh status查看集群中节点的角色，如`Mode: leader`
``` bash
[luhaoming@localhost bin]$ ./zkServer.sh status
/usr/bin/java
ZooKeeper JMX enabled by default
Using config: /home/luhaoming/apache-zookeeper-3.6.1-bin/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Mode: leader
```

完






