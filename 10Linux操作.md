### Linux操作



目标：

> 在Linux服务器上部署平台管理系统，实现外网访问。

#### 一、Linux简介

- **1.Linux系统概述**
>- Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和Unix的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的Unix工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。
- **2.Linux系统特点**
> - 性能稳定，用户可以自由裁剪，灵活性高，功能强大
  > - 开发源码、没有版权，使用成本很低
  > - 技术社区用户多
  > - 核心防火墙组件性能高效、配置简单，保证了系统的安全
- **3.Linux应用场景**
  
> - 在很多企业网络中，为了追求速度和安全，Linux操作系统不仅仅是被网络运维人员当作服务器使用
  > - Linux经过适当的配置就可实现路由器的功能，可以当作网络防火墙使用，是开发路由交换设备的理想开发平台
- **4.Linux系统版本**
> - centos
  > - ubuntu
  > - redhat
  > - SUSE
  > - DEbian
  > - OS X是基于Linux的

#### 二、Linux系统

> Linux操作系统的根目录
>
> - Linux中没有盘符的概念，Linux的根路径就是/

```
[root@localhost ~]$ cd /
[root@localhost /]$ ls
bin   dev  home  lib64  mnt  proc  run   srv  tmp  var boot  etc  lib   media  opt  root  sbin  sys  usr
```

| 文件夹                        | 说明                                                         |
| ----------------------------- | ------------------------------------------------------------ |
| bin                           | 系统命令文件夹                                               |
| sbin                          | 超级管理员的系统命令                                         |
| boot                          | 系统所需的文件(系统启动相关文件夹)                           |
| etc`[重要]`                   | 系统配置相关的文件（环境变量）                               |
| lib                           | 系统所需的依赖包                                             |
| lib64                         | 系统所需的依赖包                                             |
| home                          | 一般用户所在的文件夹（如果是一般用户登录操作系统，则进入系统之后的默认文件夹在home文件夹下） |
| root                          | 管理员所在的文件夹                                           |
| media                         | 多媒体(光驱)                                                 |
| mnt                           | u盘、移动硬盘等外设                                          |
| dev、proc、run、srv、var、sys | 系统相关                                                     |
| opt`[常用]`、tmp`[常用]`      | 可选的临时文件存储的文件夹，比如日志存在tmp或者opt           |
| usr`[常用]`                   | 一般用户需要的命令、安装的软件等等新信息在此文件夹下         |

> - root 超级管理员所在的目录
> - Java程序员需要关心的：home、usr、tmp、etc

##### 2.1 Linux文件管理

- **1.文件夹操作**
  > - 进入文件夹和退出文件夹
  >   - cd 文件夹的名称
  >   - cd .. 退出当前文件夹
  > - 显示文件夹中的内容
  >   - ls 显示内容
  >   - ll 显示详细内容(为ls -l的缩写)
  > - 创建和删除文件夹
  >   - mkdir 文件夹的名称
  >   - rm -rf 文件夹的名称 （r表示循环迭代、f强制）
- **2.文件操作**
  > - touch 1.txt 创建一个文件
  > - 创建文件和编辑文件(vi)
  >   - vi/vim 文件的名称 如果文件不存在就会新建，如果文件存在就会打开进入。
  >     - 进入可编辑：点击 i 按键
  >     - 退出编辑状态： 点击esc按键
  >     - 退出文件：
  >       - :q 没有做任何修改退出
  >       - :wq 保存并退出
  >       - :q！ 强制退出
  >     - 3yy复制3行， p粘贴
  >     - 4dd删除4行
  >     - /查找
  > - 阅读文件
  >   - cat：适合阅读小文件
  >   - more：可以分页，适合大文件。但是只能向下翻页
  >   - less：可以上下翻页，进入到文件内存进行阅读，退出less是点击q按键，可以查询文件中的内容
  >     - ?查询的内容：向上查询数据
  >     - /查询的内容：向下查询数据
  > - 查询文件或者查询指定文件中的内容
  >   - find -name 文件名称 指定目录 （查询包括子文件夹中的内容）
  >   - find 文件名称 （查询当前文件夹下的内容，不包含子文件夹）
  >   - grep 查询的内容 指定的文件
- **3.管道命令**
  > - 管道命令：多个命令的一个组合
  > - 特征：前面命令有输出、后面面临需要输入
  > - cat -n java.txt | grep qianfeng
  > - vi +59 java.txt 直接定位到59行
  > - ps -ef | grep tomcat 查找tomcat程序运行的进程`【重要】`
- **4.查询命令所在目录**
  
  > which 命令名称
- **5.复制和移动**
  > - 复制（拷贝粘贴）
  >   - cp srcFile destDir
  >   - cp srcFile destDir/fileName
  > - 移动（剪切粘贴）
  >   - mv srcFile destDir
  >   - 如果在当前文件夹中移动，相当于重命名
  >
  > `[注意：]`无论是复制还是移动，如果目的为一个目录路径，则不改名
- **6.压缩和解压缩**
  > - windows系统中压缩文件通常为（rar,zip）,Linux系统压缩文件为（test.tar.gz）
  > - tar -zxvf gzFile 解压缩
  > - tar -zcvf gzFileName srcFile 压缩
  > - 指令说明
  >   - z：采用gzip进行压缩或者解压缩，gzip是Linux中的压缩工具包。
  >   - x：解压缩
  >   - c：压缩
  >   - v：压缩或者解压缩过程中显示详细的压缩或者解压缩的过程
  >   - f：文件（压缩文件)-
  
- ##### 7.其他命令

  > pwd：查看当前文件夹全路径
  >
  > bin->usr/bin，软链接，类似于windows的快捷方式，即两个路径操作的是同一个文件夹
  >
  > ./ 执行sh文件

##### 2.2 Linux用户和权限

- **1.切换用户**

  | 指令        | 说明                                       |
  | ----------- | ------------------------------------------ |
  | su          | 切换至超级管理员账户(需输入超级管理员密码) |
  | su username | 切换至其他普通账户                         |

- **2.用户组**

  | 指令              | 说明       |
  | ----------------- | ---------- |
  | groupadd 用户组名 | 创建用户组 |

- **3.用户**

  | 指令                       | 说明                     |
  | -------------------------- | ------------------------ |
  | useradd -g 用户组名 用户名 | 在指定用户组下创建新用户 |
  | passwd 用户名              | 设置指定用户密码         |

- **4.权限**

  ![image](https://note.youdao.com/yws/public/resource/761be3201af0f3f2d4bc97f763ac08c5/xmlnote/1A7E78BEFFA540858A5E3C72573EE7ED/4627)

  > - 说明
  >   - d代表文件夹
  >   - -表示文件
  >   - r可读
  >   - w可写
  >   - x可执行
  >   - 第一个rwx：表示当前文件的所有者拥有的权限
  >   - 第二个rwx：代表和文件所有者同组用户拥有的权限
  >   - 第三个rwx：代表不同组的其他用户对当前文件拥有的权限
  > - 授权（需要超级管理员权限）
  >   - chmod 744 文件名称
  >     - r：4
  >     - w：2
  >     - x: 1
  > - 权限典型应用场景：不同的项目组之间的权限不同，可以避免没有必要的影响

##### 2.3 进程相关

- 1.查询进程

  | 指令                      | 说明                                             |
  | ------------------------- | ------------------------------------------------ |
  | lsof –i:8080              | 通过端口查询进程信息 (Ubuntu没有，centos6也没有) |
  | netstat -apn \| grep 8080 | 通过端口查询进程信息(centos6)                    |
  | ps –ef \| grep java       | 通过进程名称查询进度信息                         |

- 2.强杀进程

  | 指令        | 说明                             |
  | ----------- | -------------------------------- |
  | kill -9 PID | 通过进程ID强杀进程（-9表示强制） |

##### 2.4 查看Java内存和线程相关数据

- **1.内存和cpu分析**

  | 指令       | 说明                       |
  | ---------- | -------------------------- |
  | top -p PID | 根据进程ID查询内存消耗情况 |
  | ctrl+c     | 退出任何软件               |

  ![image](file:///C:/Users/wangliang/AppData/Local/YNote/data/migid@126.com/0da2a901946e4fe78ed97e1b5363758d/clipboard.png?ynotemdtimestamp=1598876564339)

- **2.Java内存分析（需安装JDK）**

  | 参数                                             | 说明                         |
  | ------------------------------------------------ | ---------------------------- |
  | jstat -gccapacity PID [刷新时间ms] [查询次数]    | 根据进程ID查看内存数据       |
  | jstat -gcnewcapacity PID [刷新时间ms] [查询次数] | 根据进程ID查看新生代内存数据 |
  | jstat -gcoldcapacity PID [刷新时间ms] [查询次数] | 根据进程ID查看老年代内存数据 |
  | jstat -compiler PID                              | 根据进程ID查询编译情况       |

  | 参数  | 说明               |
  | ----- | ------------------ |
  | NGCMN | 新生代最小容量     |
  | NGCMX | 新生代最大容量     |
  | NGC   | 当前新生代容量     |
  | S0C   | 第一个幸存区大小   |
  | S1C   | 第二个幸存区的大小 |
  | EC    | 伊甸园区的大小     |
  | OGCMN | 老年代最小容量     |
  | OGCMX | 老年代最大容量     |
  | OGC   | 当前老年代大小     |
  | OC    | 当前老年代大小     |
  | MCMN  | 最小元数据容量     |
  | MCMX  | 最大元数据容量     |
  | MC    | 当前元数据空间大小 |
  | CCSMN | 最小压缩类空间大小 |
  | CCSMX | 最大压缩类空间大小 |
  | CCSC  | 当前压缩类空间大小 |
  | YGC   | 年轻代gc次数       |
  | FGC   | 老年代GC次数       |

##### 2.5 shell脚本

- shell脚本文件后缀名为.sh

  | 语法操作                                        | 说明              |
  | ----------------------------------------------- | ----------------- |
  | vi [HelloWorld.sh](http://HelloWorld.sh)        | 创建shell脚本文件 |
  | name="zhangsan";                                | 定义变量          |
  | echo $name;                                     | 输出变量          |
  | chmod 744 [HelloWorld.sh](http://HelloWorld.sh) | 文件授权          |
  | ./HelloWorld.sh                                 | 执行shell脚本文件 |

#### 三、Linux命令

- 1.系统信息

  ```
  arch 显示机器的处理器架构
  uname -m 显示机器的处理器架构
  uname -r 显示正在使用的内核版本 
  dmidecode -q 显示硬件系统部件 - (SMBIOS / DMI) 
  hdparm -i /dev/hda 罗列一个磁盘的架构特性 
  hdparm -tT /dev/sda 在磁盘上执行测试性读取操作 
  cat /proc/cpuinfo 显示CPU info的信息 
  cat /proc/interrupts 显示中断 
  cat /proc/meminfo 校验内存使用 
  cat /proc/swaps 显示哪些swap被使用 
  cat /proc/version 显示内核的版本 
  cat /proc/net/dev 显示网络适配器及统计 
  cat /proc/mounts 显示已加载的文件系统 
  lspci -tv 罗列 PCI 设备 
  lsusb -tv 显示 USB 设备 
  date 显示系统日期 
  cal 2007 显示2007年的日历表 
  date 041217002007.00 设置日期和时间 - 月日时分年.秒 
  clock -w 将时间修改保存到 BIOS 
  ```

- **2.关机** (系统的关机、重启以及登出 )

  ```
  shutdown -h now 关闭系统
  init 0 关闭系统
  telinit 0 关闭系统
  shutdown -h hours:minutes & 按预定时间关闭系统 
  shutdown -c 取消按预定时间关闭系统 
  shutdown -r now 重启
  reboot 重启
  logout 注销 
  ```

- **3.文件和目录**

  ```
  cd /home 进入 '/ home' 目录' 
  cd .. 返回上一级目录 
  cd ../.. 返回上两级目录 
  cd 进入个人的主目录 
  cd ~user1 进入个人的主目录 
  cd - 返回上次所在的目录 
  pwd 显示工作路径 
  ls 查看目录中的文件 
  ls -F 查看目录中的文件 
  ls -l 显示文件和目录的详细资料 
  ls -a 显示隐藏文件 
  ls *[0-9]* 显示包含数字的文件名和目录名 
  tree 显示文件和目录由根目录开始的树形结构
  lstree 显示文件和目录由根目录开始的树形结构
  mkdir dir1 创建一个叫做 'dir1' 的目录' 
  mkdir dir1 dir2 同时创建两个目录 
  mkdir -p /tmp/dir1/dir2 创建一个目录树 
  rm -f file1 删除一个叫做 'file1' 的文件' 
  rmdir dir1 删除一个叫做 'dir1' 的目录' 
  rm -rf dir1 删除一个叫做 'dir1' 的目录并同时删除其内容 
  rm -rf dir1 dir2 同时删除两个目录及它们的内容 
  mv dir1 new_dir 重命名/移动 一个目录 
  cp file1 file2 复制一个文件 
  cp dir/* . 复制一个目录下的所有文件到当前工作目录 
  cp -a /tmp/dir1 . 复制一个目录到当前工作目录 
  cp -a dir1 dir2 复制一个目录 
  ln -s file1 lnk1 创建一个指向文件或目录的软链接 
  ln file1 lnk1 创建一个指向文件或目录的物理链接 
  touch -t 0712250000 file1 修改一个文件或目录的时间戳 - (YYMMDDhhmm) 
  file file1 outputs the mime type of the file as text 
  iconv -l 列出已知的编码 
  iconv -f fromEncoding -t toEncoding inputFile > outputFile creates a new from the given input file by assuming it is encoded in fromEncoding and converting it to toEncoding. 
  find . -maxdepth 1 -name *.jpg -print -exec convert "{}" -resize 80x60 "thumbs/{}" \; batch resize files in the current directory and send them to a thumbnails directory (requires convert from Imagemagick) 
  ```

- **4.文件搜索**

  ```
  find / -name file1 从 '/' 开始进入根文件系统搜索文件和目录 
  find / -user user1 搜索属于用户 'user1' 的文件和目录 
  find /home/user1 -name \*.bin 在目录 '/ home/user1' 中搜索带有'.bin' 结尾的文件 
  find /usr/bin -type f -atime +100 搜索在过去100天内未被使用过的执行文件 
  find /usr/bin -type f -mtime -10 搜索在10天内被创建或者修改过的文件 
  find / -name \*.rpm -exec chmod 755 '{}' \; 搜索以 '.rpm' 结尾的文件并定义其权限 
  find / -xdev -name \*.rpm 搜索以 '.rpm' 结尾的文件，忽略光驱、捷盘等可移动设备 
  locate \*.ps 寻找以 '.ps' 结尾的文件 - 先运行 'updatedb' 命令 
  whereis halt 显示一个二进制文件、源码或man的位置 
  which halt 显示一个二进制文件或可执行文件的完整路径 
  ```

- **5.挂载一个文件系统**

  ```
  mount /dev/hda2 /mnt/hda2 挂载一个叫做hda2的盘 - 确定目录 '/ mnt/hda2' 已经存在 
  umount /dev/hda2 卸载一个叫做hda2的盘 - 先从挂载点 '/ mnt/hda2' 退出 
  fuser -km /mnt/hda2 当设备繁忙时强制卸载 
  umount -n /mnt/hda2 运行卸载操作而不写入 /etc/mtab 文件- 当文件为只读或当磁盘写满时非常有用 
  mount /dev/fd0 /mnt/floppy 挂载一个软盘 
  mount /dev/cdrom /mnt/cdrom 挂载一个cdrom或dvdrom 
  mount /dev/hdc /mnt/cdrecorder 挂载一个cdrw或dvdrom 
  mount /dev/hdb /mnt/cdrecorder 挂载一个cdrw或dvdrom 
  mount -o loop file.iso /mnt/cdrom 挂载一个文件或ISO镜像文件 
  mount -t vfat /dev/hda5 /mnt/hda5 挂载一个Windows FAT32文件系统 
  mount /dev/sda1 /mnt/usbdisk 挂载一个usb 捷盘或闪存设备 
  mount -t smbfs -o username=user,password=pass //WinClient/share /mnt/share 挂载一个windows网络共享 
  ```

- 6.磁盘空间

  ```
  df -h 显示已经挂载的分区列表 
  ls -lSr |more 以尺寸大小排列文件和目录 
  du -sh dir1 估算目录 'dir1' 已经使用的磁盘空间' 
  du -sk * | sort -rn 以容量大小为依据依次显示文件和目录的大小 
  rpm -q -a --qf '%10{SIZE}t%{NAME}n' | sort -k1,1n 以大小为依据依次显示已安装的rpm包所使用的空间 (fedora, redhat类系统) 
  dpkg-query -W -f='${Installed-Size;10}t${Package}n' | sort -k1,1n 以大小为依据显示已安装的deb包所使用的空间 (ubuntu, debian类系统) 
  ```

- **7.用户和群组**

  ```
  groupadd group_name 创建一个新用户组 
  groupdel group_name 删除一个用户组 
  groupmod -n new_group_name old_group_name 重命名一个用户组 
  useradd -c "Name Surname " -g admin -d /home/user1 -s /bin/bash user1 创建一个属于 "admin" 用户组的用户 
  useradd user1 创建一个新用户 
  userdel -r user1 删除一个用户 ( '-r' 排除主目录) 
  usermod -c "User FTP" -g system -d /ftp/user1 -s /bin/nologin user1 修改用户属性 
  passwd 修改口令 
  passwd user1 修改一个用户的口令 (只允许root执行) 
  chage -E 2005-12-31 user1 设置用户口令的失效期限 
  pwck 检查 '/etc/passwd' 的文件格式和语法修正以及存在的用户 
  grpck 检查 '/etc/passwd' 的文件格式和语法修正以及存在的群组 
  newgrp group_name 登陆进一个新的群组以改变新创建文件的预设群组 
  ```

- **8.文件的权限** 使用 "+" 设置权限，使用 "-" 用于取消

  ```
  ls -lh 显示权限 
  ls /tmp | pr -T5 -W$COLUMNS 将终端划分成5栏显示 
  chmod ugo+rwx directory1 设置目录的所有人(u)、群组(g)以及其他人(o)以读（r ）、写(w)和执行(x)的权限 
  chmod go-rwx directory1 删除群组(g)与其他人(o)对目录的读写执行权限 
  chown user1 file1 改变一个文件的所有人属性 
  chown -R user1 directory1 改变一个目录的所有人属性并同时改变改目录下所有文件的属性 
  chgrp group1 file1 改变文件的群组 
  chown user1:group1 file1 改变一个文件的所有人和群组属性 
  find / -perm -u+s 罗列一个系统中所有使用了SUID控制的文件 
  chmod u+s /bin/file1 设置一个二进制文件的 SUID 位 - 运行该文件的用户也被赋予和所有者同样的权限 
  chmod u-s /bin/file1 禁用一个二进制文件的 SUID位 
  chmod g+s /home/public 设置一个目录的SGID 位 - 类似SUID ，不过这是针对目录的 
  chmod g-s /home/public 禁用一个目录的 SGID 位 
  chmod o+t /home/public 设置一个文件的 STIKY 位 - 只允许合法所有人删除文件 
  chmod o-t /home/public 禁用一个目录的 STIKY 位 
  ```

- 9.文件的特殊属性

   

  使用 "+" 设置权限，使用 "-" 用于取消

  ```
  chattr +a file1 只允许以追加方式读写文件 
  chattr +c file1 允许这个文件能被内核自动压缩/解压 
  chattr +d file1 在进行文件系统备份时，dump程序将忽略这个文件 
  chattr +i file1 设置成不可变的文件，不能被删除、修改、重命名或者链接 
  chattr +s file1 允许一个文件被安全地删除 
  chattr +S file1 一旦应用程序对这个文件执行了写操作，使系统立刻把修改的结果写到磁盘 
  chattr +u file1 若文件被删除，系统会允许你在以后恢复这个被删除的文件 
  lsattr 显示特殊的属性 
  ```

- 10.打包和压缩文件

  ```
  bunzip2 file1.bz2 解压一个叫做 'file1.bz2'的文件 
  bzip2 file1 压缩一个叫做 'file1' 的文件 
  gunzip file1.gz 解压一个叫做 'file1.gz'的文件 
  gzip file1 压缩一个叫做 'file1'的文件 
  gzip -9 file1 最大程度压缩 
  rar a file1.rar test_file 创建一个叫做 'file1.rar' 的包 
  rar a file1.rar file1 file2 dir1 同时压缩 'file1', 'file2' 以及目录 'dir1' 
  rar x file1.rar 解压rar包 
  unrar x file1.rar 解压rar包 
  tar -cvf archive.tar file1 创建一个非压缩的 tarball 
  tar -cvf archive.tar file1 file2 dir1 创建一个包含了 'file1', 'file2' 以及 'dir1'的档案文件 
  tar -tf archive.tar 显示一个包中的内容 
  tar -xvf archive.tar 释放一个包 
  tar -xvf archive.tar -C /tmp 将压缩包释放到 /tmp目录下 
  tar -cvfj archive.tar.bz2 dir1 创建一个bzip2格式的压缩包 
  tar -jxvf archive.tar.bz2 解压一个bzip2格式的压缩包 
  tar -cvfz archive.tar.gz dir1 创建一个gzip格式的压缩包 
  tar -zxvf archive.tar.gz 解压一个gzip格式的压缩包 
  zip file1.zip file1 创建一个zip格式的压缩包 
  zip -r file1.zip file1 file2 dir1 将几个文件和目录同时压缩成一个zip格式的压缩包 
  unzip file1.zip 解压一个zip格式压缩包 
  ```

- 11.RPM 包 - （Fedora, Redhat及类似系统）

  ```
  rpm -ivh package.rpm 安装一个rpm包 
  rpm -ivh --nodeeps package.rpm 安装一个rpm包而忽略依赖关系警告 
  rpm -U package.rpm 更新一个rpm包但不改变其配置文件 
  rpm -F package.rpm 更新一个确定已经安装的rpm包 
  rpm -e package_name.rpm 删除一个rpm包 
  rpm -qa 显示系统中所有已经安装的rpm包 
  rpm -qa | grep httpd 显示所有名称中包含 "httpd" 字样的rpm包 
  rpm -qi package_name 获取一个已安装包的特殊信息 
  rpm -qg "System Environment/Daemons" 显示一个组件的rpm包 
  rpm -ql package_name 显示一个已经安装的rpm包提供的文件列表 
  rpm -qc package_name 显示一个已经安装的rpm包提供的配置文件列表 
  rpm -q package_name --whatrequires 显示与一个rpm包存在依赖关系的列表 
  rpm -q package_name --whatprovides 显示一个rpm包所占的体积 
  rpm -q package_name --scripts 显示在安装/删除期间所执行的脚本l 
  rpm -q package_name --changelog 显示一个rpm包的修改历史 
  rpm -qf /etc/httpd/conf/httpd.conf 确认所给的文件由哪个rpm包所提供 
  rpm -qp package.rpm -l 显示由一个尚未安装的rpm包提供的文件列表 
  rpm --import /media/cdrom/RPM-GPG-KEY 导入公钥数字证书 
  rpm --checksig package.rpm 确认一个rpm包的完整性 
  rpm -qa gpg-pubkey 确认已安装的所有rpm包的完整性 
  rpm -V package_name 检查文件尺寸、 许可、类型、所有者、群组、MD5检查以及最后修改时间 
  rpm -Va 检查系统中所有已安装的rpm包- 小心使用 
  rpm -Vp package.rpm 确认一个rpm包还未安装 
  rpm2cpio package.rpm | cpio --extract --make-directories *bin* 从一个rpm包运行可执行文件 
  rpm -ivh /usr/src/redhat/RPMS/`arch`/package.rpm 从一个rpm源码安装一个构建好的包 
  rpmbuild --rebuild package_name.src.rpm 从一个rpm源码构建一个 rpm 包 
  ```

- 12.YUM 软件包升级器

  （Fedora, RedHat及类似系统）

  ```
  yum install package_name 下载并安装一个rpm包 
  yum localinstall package_name.rpm 将安装一个rpm包，使用你自己的软件仓库为你解决所有依赖关系 
  yum update package_name.rpm 更新当前系统中所有安装的rpm包 
  yum update package_name 更新一个rpm包 
  yum remove package_name 删除一个rpm包 
  yum list 列出当前系统中安装的所有包 
  yum search package_name 在rpm仓库中搜寻软件包 
  yum clean packages 清理rpm缓存删除下载的包 
  yum clean headers 删除所有头文件 
  yum clean all 删除所有缓存的包和头文件 
  ```

- 13.DEB 包 (Debian, Ubuntu 以及类似系统)

  ```
  dpkg -i package.deb 安装/更新一个 deb 包 
  dpkg -r package_name 从系统删除一个 deb 包 
  dpkg -l 显示系统中所有已经安装的 deb 包 
  dpkg -l | grep httpd 显示所有名称中包含 "httpd" 字样的deb包 
  dpkg -s package_name 获得已经安装在系统中一个特殊包的信息 
  dpkg -L package_name 显示系统中已经安装的一个deb包所提供的文件列表 
  dpkg --contents package.deb 显示尚未安装的一个包所提供的文件列表 
  dpkg -S /bin/ping 确认所给的文件由哪个deb包提供 
  ```

- 14.APT 软件工具 (Debian, Ubuntu 以及类似系统)

  ```
  apt-get install package_name 安装/更新一个 deb 包
  apt-cdrom install package_name 从光盘安装/更新一个 deb 包 
  apt-get update 升级列表中的软件包 
  apt-get upgrade 升级所有已安装的软件 
  apt-get remove package_name 从系统删除一个deb包 
  apt-get check 确认依赖的软件仓库正确 
  apt-get clean 从下载的软件包中清理缓存 
  apt-cache search searched-package 返回包含所要搜索字符串的软件包名称 
  ```

- **15.查看文件内容**

  ```
  cat file1 从第一个字节开始正向查看文件的内容 
  tac file1 从最后一行开始反向查看一个文件的内容 
  more file1 查看一个长文件的内容 
  less file1 类似于 'more' 命令，但是它允许在文件中和正向操作一样的反向操作 
  head -2 file1 查看一个文件的前两行 
  tail -2 file1 查看一个文件的最后两行 
  tail -f /var/log/messages 实时查看被添加到一个文件中的内容 
  ```

- **16.文本处理**

  ```
  cat file1 file2 ... | command <> file1_in.txt_or_file1_out.txt general syntax for text manipulation using PIPE, STDIN and STDOUT 
  cat file1 | command( sed, grep, awk, grep, etc...) > result.txt 合并一个文件的详细说明文本，并将简介写入一个新文件中 
  cat file1 | command( sed, grep, awk, grep, etc...) >> result.txt 合并一个文件的详细说明文本，并将简介写入一个已有的文件中 
  grep Aug /var/log/messages 在文件 '/var/log/messages'中查找关键词"Aug" 
  grep ^Aug /var/log/messages 在文件 '/var/log/messages'中查找以"Aug"开始的词汇 
  grep [0-9] /var/log/messages 选择 '/var/log/messages' 文件中所有包含数字的行 
  grep Aug -R /var/log/* 在目录 '/var/log' 及随后的目录中搜索字符串"Aug" 
  sed 's/stringa1/stringa2/g' example.txt 将example.txt文件中的 "string1" 替换成 "string2" 
  sed '/^$/d' example.txt 从example.txt文件中删除所有空白行 
  sed '/ *#/d; /^$/d' example.txt 从example.txt文件中删除所有注释和空白行 
  echo 'esempio' | tr '[:lower:]' '[:upper:]' 合并上下单元格内容 
  sed -e '1d' result.txt 从文件example.txt 中排除第一行 
  sed -n '/stringa1/p' 查看只包含词汇 "string1"的行 
  sed -e 's/ *$//' example.txt 删除每一行最后的空白字符 
  sed -e 's/stringa1//g' example.txt 从文档中只删除词汇 "string1" 并保留剩余全部 
  sed -n '1,5p;5q' example.txt 查看从第一行到第5行内容 
  sed -n '5p;5q' example.txt 查看第5行 
  sed -e 's/00*/0/g' example.txt 用单个零替换多个零 
  cat -n file1 标示文件的行数 
  cat example.txt | awk 'NR%2==1' 删除example.txt文件中的所有偶数行 
  echo a b c | awk '{print $1}' 查看一行第一栏 
  echo a b c | awk '{print $1,$3}' 查看一行的第一和第三栏 
  paste file1 file2 合并两个文件或两栏的内容 
  paste -d '+' file1 file2 合并两个文件或两栏的内容，中间用"+"区分 
  sort file1 file2 排序两个文件的内容 
  sort file1 file2 | uniq 取出两个文件的并集(重复的行只保留一份) 
  sort file1 file2 | uniq -u 删除交集，留下其他的行 
  sort file1 file2 | uniq -d 取出两个文件的交集(只留下同时存在于两个文件中的文件) 
  comm -1 file1 file2 比较两个文件的内容只删除 'file1' 所包含的内容 
  comm -2 file1 file2 比较两个文件的内容只删除 'file2' 所包含的内容 
  comm -3 file1 file2 比较两个文件的内容只删除两个文件共有的部分 
  ```

- 17.字符设置和文件格式转换

  ```
  dos2unix filedos.txt fileunix.txt 将一个文本文件的格式从MSDOS转换成UNIX 
  unix2dos fileunix.txt filedos.txt 将一个文本文件的格式从UNIX转换成MSDOS 
  recode ..HTML < page.txt > page.html 将一个文本文件转换成html 
  recode -l | more 显示所有允许的转换格式 
  ```

- 18.文件系统分析

  ```
  badblocks -v /dev/hda1 检查磁盘hda1上的坏磁块 
  fsck /dev/hda1 修复/检查hda1磁盘上linux文件系统的完整性 
  fsck.ext2 /dev/hda1 修复/检查hda1磁盘上ext2文件系统的完整性 
  e2fsck /dev/hda1 修复/检查hda1磁盘上ext2文件系统的完整性 
  e2fsck -j /dev/hda1 修复/检查hda1磁盘上ext3文件系统的完整性 
  fsck.ext3 /dev/hda1 修复/检查hda1磁盘上ext3文件系统的完整性 
  fsck.vfat /dev/hda1 修复/检查hda1磁盘上fat文件系统的完整性 
  fsck.msdos /dev/hda1 修复/检查hda1磁盘上dos文件系统的完整性 
  dosfsck /dev/hda1 修复/检查hda1磁盘上dos文件系统的完整性 
  ```

- 19.初始化一个文件系统

  ```
  mkfs /dev/hda1 在hda1分区创建一个文件系统 
  mke2fs /dev/hda1 在hda1分区创建一个linux ext2的文件系统 
  mke2fs -j /dev/hda1 在hda1分区创建一个linux ext3(日志型)的文件系统 
  mkfs -t vfat 32 -F /dev/hda1 创建一个 FAT32 文件系统 
  fdformat -n /dev/fd0 格式化一个软盘 
  mkswap /dev/hda3 创建一个swap文件系统 
  ```

- **20.SWAP文件系统**

  ```
  mkswap /dev/hda3 创建一个swap文件系统 
  swapon /dev/hda3 启用一个新的swap文件系统 
  swapon /dev/hda2 /dev/hdb3 启用两个swap分区 
  ```

- **21.备份**

  ```
  dump -0aj -f /tmp/home0.bak /home 制作一个 '/home' 目录的完整备份 
  dump -1aj -f /tmp/home0.bak /home 制作一个 '/home' 目录的交互式备份 
  restore -if /tmp/home0.bak 还原一个交互式备份 
  rsync -rogpav --delete /home /tmp 同步两边的目录 
  rsync -rogpav -e ssh --delete /home ip_address:/tmp 通过SSH通道rsync 
  rsync -az -e ssh --delete ip_addr:/home/public /home/local 通过ssh和压缩将一个远程目录同步到本地目录 
  rsync -az -e ssh --delete /home/local ip_addr:/home/public 通过ssh和压缩将本地目录同步到远程目录 
  dd bs=1M if=/dev/hda | gzip | ssh user@ip_addr 'dd of=hda.gz' 通过ssh在远程主机上执行一次备份本地磁盘的操作 
  dd if=/dev/sda of=/tmp/file1 备份磁盘内容到一个文件 
  tar -Puf backup.tar /home/user 执行一次对 '/home/user' 目录的交互式备份操作 
  ( cd /tmp/local/ && tar c . ) | ssh -C user@ip_addr 'cd /home/share/ && tar x -p' 通过ssh在远程目录中复制一个目录内容 
  ( tar c /home ) | ssh -C user@ip_addr 'cd /home/backup-home && tar x -p' 通过ssh在远程目录中复制一个本地目录 
  tar cf - . | (cd /tmp/backup ; tar xf - ) 本地将一个目录复制到另一个地方，保留原有权限及链接 
  find /home/user1 -name '*.txt' | xargs cp -av --target-directory=/home/backup/ --parents 从一个目录查找并复制所有以 '.txt' 结尾的文件到另一个目录 
  find /var/log -name '*.log' | tar cv --files-from=- | bzip2 > log.tar.bz2 查找所有以 '.log' 结尾的文件并做成一个bzip包 
  dd if=/dev/hda of=/dev/fd0 bs=512 count=1 做一个将 MBR (Master Boot Record)内容复制到软盘的动作 
  dd if=/dev/fd0 of=/dev/hda bs=512 count=1 从已经保存到软盘的备份中恢复MBR内容 
  ```

- **22.光盘**

  ```
  cdrecord -v gracetime=2 dev=/dev/cdrom -eject blank=fast -force 清空一个可复写的光盘内容 
  mkisofs /dev/cdrom > cd.iso 在磁盘上创建一个光盘的iso镜像文件 
  mkisofs /dev/cdrom | gzip > cd_iso.gz 在磁盘上创建一个压缩了的光盘iso镜像文件 
  mkisofs -J -allow-leading-dots -R -V "Label CD" -iso-level 4 -o ./cd.iso data_cd 创建一个目录的iso镜像文件 
  cdrecord -v dev=/dev/cdrom cd.iso 刻录一个ISO镜像文件 
  gzip -dc cd_iso.gz | cdrecord dev=/dev/cdrom - 刻录一个压缩了的ISO镜像文件 
  mount -o loop cd.iso /mnt/iso 挂载一个ISO镜像文件 
  cd-paranoia -B 从一个CD光盘转录音轨到 wav 文件中 
  cd-paranoia -- "-3" 从一个CD光盘转录音轨到 wav 文件中（参数-3） 
  cdrecord --scanbus 扫描总线以识别scsi通道 
  dd if=/dev/hdc | md5sum 校验一个设备的md5sum编码，例如一张 CD 
  ```

- **23.网络** （以太网和WIFI无线）

  ```
  ifconfig eth0 显示一个以太网卡的配置 
  ifup eth0 启用一个 'eth0' 网络设备 
  ifdown eth0 禁用一个 'eth0' 网络设备 
  ifconfig eth0 192.168.1.1 netmask 255.255.255.0 控制IP地址 
  ifconfig eth0 promisc 设置 'eth0' 成混杂模式以嗅探数据包 (sniffing) 
  dhclient eth0 以dhcp模式启用 'eth0' 
  route -n show routing table 
  route add -net 0/0 gw IP_Gateway configura default gateway 
  route add -net 192.168.0.0 netmask 255.255.0.0 gw 192.168.1.1 configure static route to reach network '192.168.0.0/16' 
  route del 0/0 gw IP_gateway remove static route 
  echo "1" > /proc/sys/net/ipv4/ip_forward activate ip routing 
  hostname show hostname of system 
  host www.example.com lookup hostname to resolve name to ip address and viceversa
  nslookup www.example.com lookup hostname to resolve name to ip address and viceversa
  ip link show show link status of all interfaces 
  mii-tool eth0 show link status of 'eth0' 
  ethtool eth0 show statistics of network card 'eth0' 
  netstat -tup show all active network connections and their PID 
  netstat -tupl show all network services listening on the system and their PID 
  tcpdump tcp port 80 show all HTTP traffic 
  iwlist scan show wireless networks 
  iwconfig eth1 show configuration of a wireless network card 
  hostname show hostname 
  host www.example.com lookup hostname to resolve name to ip address and viceversa 
  nslookup www.example.com lookup hostname to resolve name to ip address and viceversa 
  whois www.example.com lookup on Whois database 
  ```

- **24.JPS工具**

  - jps(Java Virtual Machine Process Status Tool)是JDK 1.5提供的一个显示当前所有java进程pid的命令，简单实用，非常适合在linux/unix平台上简单察看当前java进程的一些简单情况。
  - 我想很多人都是用过unix系统里的ps命令，这个命令主要是用来显示当前系统的进程情况，有哪些进程，及其 id。 jps也是一样，它的作用是显示当前系统的java进程情况，及其id号。我们可以通过它来查看我们到底启动了几个java进程（因为每一个java程序都会独占一个java虚拟机实例），和他们的进程号（为下面几个程序做准备），并可通过opt来查看这些进程的详细启动参数。
  - 使用方法：在当前命令行下打jps(需要JAVA_HOME，没有的话，到改程序的目录下打) 。
  - jps存放在JAVA_HOME/bin/jps,使用时为了方便请将JAVA_HOME/bin/加入到Path.

  ```
  $>jps
  23991 Jps
  23789 BossMain
  23651 Resin
   
  
  比较常用的参数：
  -q 只显示pid，不显示class名称,jar文件名和传递给main 方法的参数
  $>  jps -q
  28680
  23789
  23651
  -m 输出传递给main 方法的参数，在嵌入式jvm上可能是null
  $> jps -m
  28715 Jps -m
  23789 BossMain
  23651 Resin -socketwait 32768 -stdout /data/aoxj/resin/log/stdout.log -stderr /data/aoxj/resin/log/stderr.log
  -l 输出应用程序main class的完整package名 或者 应用程序的jar文件完整路径名
  $> jps -l
  28729 sun.tools.jps.Jps
  23789 com.asiainfo.aimc.bossbi.BossMain
  23651 com.caucho.server.resin.Resin
  -v 输出传递给JVM的参数
  $> jps -v
  23789 BossMain
  28802 Jps -Denv.class.path=/data/aoxj/bossbi/twsecurity/java/trustwork140.jar:/data/aoxj/bossbi/twsecurity/java/:/data/aoxj/bossbi/twsecurity/java/twcmcc.jar:/data/aoxj/jdk15/lib/rt.jar:/data/aoxj/jd
  k15/lib/tools.jar -Dapplication.home=/data/aoxj/jdk15 -Xms8m
  23651 Resin -Xss1m -Dresin.home=/data/aoxj/resin -Dserver.root=/data/aoxj/resin -Djava.util.logging.manager=com.caucho.log.LogManagerImpl -
  Djavax.management.builder.initial=com.caucho.jmx.MBeanServerBuilderImpl
  sudo jps看到的进程数量最全
  jps 192.168.0.77
  列出远程服务器192.168.0.77机器所有的jvm实例，采用rmi协议，默认连接端口为1099
  （前提是远程服务器提供jstatd服务）
  注：jps命令有个地方很不好，似乎只能显示当前用户的java进程，要
  ```

#### 四、环境安装

> 1、手动安装
>
> - 把安装包下载
> - 解压，安装，配置环境变量
> - 会把文件安装到你指定位置
>
> 2、使用Linux系统安装工具安装（命令安装）
>
> - 一键（命令）安装
> - 会把软件根据功能分散安装到对应目录中
> - 不同的Linux系统安装命令不同（centos：yum，ubuntu：apt）



##### 4.1 JDK安装

> 下载JDK，选择Linux版本，如果要直接在服务器上下载，可以使用wget命令
>
> 上传到Linux服务器，可以使用命令scp，也可以使用xftp工具直接拖动上传
>
> 解压到相关目录
>
> 配置环境变量

```shell
vim /etc/profile
export JAVA_HOME=/usr/local/jdk1.8.0_144
export PATH=.:$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
```

> 刷新配置
>
> source /etc/profile

##### 4.2 Tomcat安装

> 下载上传解压即可



##### 4.3 MySQL安装

> 0、先更新yum的列表
>
> ```
> yum update
> ```
>
> 1、先检查系统是否安装有mysql
>
> ```
> yum list installed mysql*
> ```
>
> 2、查看有没有安装包
>
> ```
> yum list mysql*
> ```
>
> 3、安装mysql客户端
>
> ```
> yum install mysql
> ```
>
> 4、安装mysql服务端
>
> ```
> yum install mysql-server
> ```
>
> 注意：此处错误提示为安装失败。
>
> 原因：CentOS7自带有MariaDB而不是MySQL，MariaDB和MySQL一样也是开元的数据库
>
> 解决方案：如果必须要安装MySQL，首先必须添加mysql社区repo通过输入命令：sudo rpm -Uvh http://dev.mysql.com/get/mysql-community-release-el7-5.noarch.rpm
>
> 5、执行完后,则继续执行：
>
> ```
> yum install mysql-server 
> ```
>
> 6、
>
> ```
> yum install mysql-devel
> ```
>
> 7、启动或关闭mysql服务
>
> ```java
> service mysqld start      --启动mysql
> service mysqld stop       --关闭mysql·
> lsof -i:3306              --数据库端口是否开启
> ```
>
> 8、
>
> ```
> --登录
> mysql -uroot -p
> -- 登录之后修改密码
> set password = password('admin123')
> ```

> [注意：]() MySQL5.7的Linux版本是默认区分大小写的，需要设置才会忽略。

#### 五、部署项目

##### 5.1 导入数据库

> 1、将本地数据库导出sql文件，并上传到服务器
>
> 2、在服务器上新建数据库
>
> ```sql
> create database db_wfx default charset=utf8;
> ```
>
> 3、切换数据库
>
> ```sql
> use db_wfx;
> ```
>
> 4、导入sql文件
>
> ```sql
> source /home/db_wfx.sql
> ```

##### 5.2 将项目打包并上传到服务器

> 使用maven的mvn:package命令

##### 5.3 配置安全组策略

> 阿里云默认只会开启一些常用的端口，如果需要外网访问某些端口，应该开启该端口的外网访问权限