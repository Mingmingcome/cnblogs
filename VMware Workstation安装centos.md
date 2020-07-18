`begin 2020年7月4日16:32:34`

今天我们是食神，来做一道菜，名曰VMware Workstation安装centos。

首先我们需要准备好锅和食材。

#### 锅和食材

锅：VMware Workstation，可在官网下载，我使用的是12.5.9 build-7535481破解版本（仅用于学习）

食材：CentOS7，官网：<http://isoredirect.centos.org/centos/7/isos/x86_64/>

![镜像地址](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704090058CentOS-mirrors.png)

<b>点击</b>清华大学开源软件镜像站：<https://mirrors.tuna.tsinghua.edu.cn/centos/7.8.2003/isos/x86_64/>

![清华大学开源软件镜像站](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704090503tsinghua-centos.png)

0_README.txt中有对各个版本ISO镜像文件的说明：

CentOS-7-x86_64-DVD-2003.iso            标准安装版（推荐）

CentOS-7-x86_64-NetInstall-2003.iso     网络安装版（从网络安装或者救援系统）

CentOS-7-x86_64-Everything-2003.iso     完整版，集成所有软件（以用来补充系统的软件或者填充本地镜像）

CentOS-7-x86_64-LiveGNOME-2003.iso      GNOME桌面版

CentOS-7-x86_64-LiveKDE-2003.iso        KDE桌面版

CentOS-7-x86_64-Minimal-2003.iso        精简版，自带的软件最少

推荐标准版，我下载的精简版

#### 把食材放到锅中

虚拟机配置：

主页>>创建新的虚拟机》》典型（推荐）》》安装程序光盘镜像文件（ISO）【选择你下载镜像的文件路径】》》名称及位置（CentOS7）》》下一步》》下一步》》完成。

完成后便进入系统安装界面：

![安装CentOS](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704102726CentOS-install.png)

#### 加入调味品

安装CentOS系统：

1、选择语言，默认下一步

![选择语言](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704102949CentOS-install-language.png)

2、网络和主机名（NETWORK & HOSTNAME)

![网络和主机名](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704103049CentOS-install-network-detail.png)

将Ethernet开关设置为ON，为了能联网设置时区，这里设置后是动态ip

Host name可修改为你喜欢的名称，此处默认localhost，也可进入CentOS系统后修改，修改方法见：<https://www.cnblogs.com/mingmingcome/p/13291261.html>

3、设置日期和时间（DATE & TIME）

先打开Network Time，点击齿轮，添加ntp1#aliyun#com（#换成.)，用来时间同步

![时间服务器](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704153653CentOs-install-ntp.png)

后选择Asian，Shanghai

![时区](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704103151CentOS-install-time&zone-shanghai.png)

也可进入CentOS系统后修改，见CentOS 7时间同步<https://www.cnblogs.com/mingmingcome/p/13239125.html>

4、安装位置（INSTALLATION DESTINATION)

因为有个告警（！），点击进去，默认不修改，然后点击done。


5、开始安装

右下角Begin Installation

![创建用户](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704103241CentOS-install-user.png)


ROOTPASSWORD：设置root密码

USER CREATION：创建用户，如luhaoming

先喝杯茶，等待出锅

#### 出锅

安装后成功登录

![登录系统](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200704160302CentOS-login.png)

`end 2020年7月5日00:18:41`

