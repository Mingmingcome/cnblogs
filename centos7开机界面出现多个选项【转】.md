`2020年6月27日10:04:18`

转载：<a>https://www.cnblogs.com/jcblog/p/6431252.html</a>

![开机界面出现多个选项](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200627033554linux-mutil-option-to-startup.JPG)

第一个选项正常启动，第二个选项是yum upgrade之后多出来的选项，第三个选项急救模式启动（系统出项问题不能正常启动时使用并修复系统）

在CentOS更新后,并不会自动删除旧内核。所以在启动选项中会有多个内核选项,可以手动使用以下命令删除多余的内核:

1.查看系统当前内核版本:

`# uname -a`
```
Linux localhost.localdomain 3.10.0-1127.13.1.el7.x86_64 #1 SMP Tue Jun 23 15:46:38 UTC 2020 x86_64 x86_64 x86_64 GNU/Linux
```

2.查看系统中全部的内核RPM包:

`# rpm -qa | grep kernel`
```linux
kernel-3.10.0-957.el7.x86_64
kernel-3.10.0-1127.13.1.el7.x86_64
kernel-tools-3.10.0-1127.13.1.el7.x86_64
kernel-tools-libs-3.10.0-1127.13.1.el7.x86_64
```

3.删除旧内核的RPM包

`# kernel-3.10.0-957.el7.x86_64`

4.重启系统

`# reboot`
注意:不需要手动修改/boot/grub/menu.lst

5.启动后
![开机界面正常](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_200627034439linux-mutil-option-to-startup-after.JPG)