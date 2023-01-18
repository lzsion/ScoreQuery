# 基于Java以及MySQL的学习成绩管理系统

## 前言

本项目为课程作业，已经作提交，时间有限，程序界面比较简陋，仍有未修复的bug，但不影响正常使用。

[TOC]

## 一、题目要求

使用Java语言编写一个数据库程序，可以是控制台命令行，也可以是有GUI面的程序，或者是基于Web的系统，能够实现数据库里信息的查询，例如学生成绩查询，输入学号和姓名可以查询出，学生的各个科目的成绩单。可以通过PHPStudy来安装配置MySQL数据库，也可以自行单独安装。使用Navicat来连接到你自己的MySQL数据库服务器，创建数据库和数据库表。也可以完成比较复杂的数据库应用系统开发。

## 二、设计思路

设计目标为可以对建立一个数据库存放学生成绩信息，并且可以对学生的成绩信息进行录入，删除，修改，不同方式的查询等功能的学生成绩管理系统，有一定的GUI界面。

### 2.1 建立mysql服务器

数据库挂载在阿里云的Ubuntu18.04服务器上，使用如下语句安装mysql服务器。

```
sudo apt update
sudo apt install mysql-server
```

此服务器为sql版本为

```
server version: 5.7.38-0ubuntu0.18.04.1 (Ubuntu)
```

由于mysql-5.7版本默认不支持utf-8，出于方便，所有数据以及表名数据库名均为英文。

在阿里云防火墙开放3306端口，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_1.png)

配置远程连接文件。

创建用户“aliyun”，用于外部访问，语句如下。

```mysql
CREATE USER 'aliyun'@'%' IDENTIFIED BY 'root';
```

给访问权限，语句如下。

```mysql
GRANT ALL PRIVILEGES ON *.* TO 'aliyun'@'%';
FLUSH PRIVILEGES;
```

查看所有用户，语句如下，“aliyun”用户已经添加。

```
mysql> select user from mysql.user;
+------------------+
| user             |
+------------------+
| aliyun           |
| debian-sys-maint |
| mysql.session    |
| mysql.sys        |
| root             |
+------------------+
```

为方便对数据库进行操作，使用Navicat Premium 16 连接数据库，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_2.png)

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_3.png)

使用脚本语句建立“student”数据库，代码如下。

```mysql
CREATE DATABASE 'student';
```

在数据库内建立学生信息表“class1”，代码如下。

```mysql
DROP TABLE IF EXISTS `class1`;
CREATE TABLE `class1`  (
  `id` int(20) NOT NULL DEFAULT '0',
  `name` varchar(20) DEFAULT NULL,
  `chinese` int(3) NOT NULL DEFAULT '0',
  `math` int(3) NOT NULL DEFAULT '0',
  `english` int(3) NOT NULL DEFAULT '0',
  `physics` int(3) NOT NULL DEFAULT '0',
  `chemistry` int(3) NOT NULL DEFAULT '0',
  `biology` int(3) NOT NULL DEFAULT '0',
  `total` int(3) NOT NULL DEFAULT '0'
);
```

同理建立“class2”。

方便起见，使用Navicat从excel表格中直接导入数据。

### 2.2 使用java连接mysql

导入相关库。

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
```

连接至mysql数据库。

```java
String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
Class.forName(JDBC_DRIVER);
connection = DriverManager.getConnection(DB_URL,USER,PASS);
statement = connection.createStatement();
```

其中，*DB_URL*为服务器数据库连接名，*USER*为用户名，*PASS*为密码，Connection为java.sql.Connection的一个实例化对象，statement为java.sql.Statement的实例化对象。

使用Statement的executeQuery函数，传入查询的sql语句sqlStatement，从而实现sql数据库查询的操作，得到查询的结果集，代码如下。

``` java
ResultSet resultSet = statement.executeQuery(sqlStatement);
```

使用循环将查询得到的结果集展开，获取结果集内的数据。

也可以使用Statement的executeUpdate函数，传入添加，更改，删除等sql语句sqlStatement，从而实现对sql数据库进行添加，更改，删除等操作，代码如下。

```java
statement.executeUpdate(sqlStatement.get(i));
```

该语句返回值为整型，反应sql语句执行的结果。

操作完成后，关闭结果集，关闭statement对象，关闭连接，代码如下。

```java
resultSet.close();//先关闭结果集
statement.close();//关闭Statement对象
connection.close();//最后关闭连接
```

可以用try-catch语句捕获异常并处理。将上述操作语句放入try，并定义catch。

```
catch (Exception e) {
    e.printStackTrace();
}
```

这样，就可以通过Java实现对sql数据库的增加、删除、修改、查询等操作。

### 2.3 使用java的GUI编程完善学生成绩管理系统

使用Java的GUI编程，完善学生成绩管理系统。

导入相关库。

```java
import javax.swing.*;
import java.awt.*;
```

使用主窗口以及6个Panel以及若干Dialog实现学生成绩管理系统。

通过MainFrame主窗口类来管理所有面板。

通过Const常数接口定义所有用到的常数。

通过MysqlConnect类实现对sql数据库的连接以及其他操作。

LoginPanel类为登录界面面板类，用户输入用户名，密码，数据库名进行登录，登录失败抛出异常并弹出登陆失败弹窗，登录成功则直接进入StartPanel。

StartPanel类为开始面板类，可以通过按钮链接到四种功能面板，通过返回按钮可以返回至次面板。

InsertPanel类为录入信息面板类，通过若干文本域，实现对学生成绩的录入。对“录入”按钮监听，点击“录入”按钮则获取文本域信息，生成sql语句，调用函数插入信息。如果输入的学号重复则停止录入并弹出“学号重复”提示弹窗，如果输入信息有误则停止录入并且弹出提示弹窗，输入合法时弹出“录入成功”弹窗，并清空输入的文本域内的信息。

QueryPanel类为查询信息面板类，通过若干选择下拉框，实现不同对象（可以是一个班、所有班、输入学号、输入姓名），不同范围（所有学科、单个学科、总分）、不同排序对象、不同排序方式的查询。对“查询”按钮监听，点击“查询”按钮则根据排序的选项生成sql语句，调用函数查询信息。查询结果通过弹窗显示，如果查询失败则弹出“查无此人”弹窗。

ChangePanel类为修改信息面板类，用户先通过学号或者姓名的方式，查找需要修改的数据。对“查询”按钮监听，点击“查询”按钮则根据排序的选项生成sql语句，调用函数查询信息。如果查询失败则弹出“查无此人”弹窗，查询成功则将查询结果写入文本域等待用户修改。通过对“确认修改”按钮监听，点击“确认修改”按钮则获取文本域信息，生成sql语句，调用函数对信息进行修改。修改成功则弹出“修改成功”弹窗，修改失败则弹出“修改失败”弹窗。

DeletePanel类为删除信息面板类，同查询信息面板，用户先通过学号或者姓名的方式，查找需要修改的数据。查询成功则展示查询的数据，通过对“确认删除”按钮监听，点击“确认删除”按钮则生成sql语句，调用函数对信息进行修改。

## 三、程序结果展示

### 3.1 登录界面

输入用户名，密码，数据库名进行登录，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_4.png)

输入信息错误则登录失败，弹出“登录失败弹窗”，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_5.png)

### 3.2 开始界面

登录成功则进入开始界面，有四个按钮实现不同功能的选择，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_6.png)

### 3.3 信息录入界面

信息录入界面，选择录入的班级，输入相关信息进行录入，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_7.png)

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_8.png)

输入信息不合法则录入失败，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_9.png)

### 3.4 查询信息界面

查询信息界面，选择查询选项进行查询，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_11.png)

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_12.png)

查询单个人，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_13.png)

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_14.png)

查询失败，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_16.png)

### 3.5 信息修改界面

信息修改界面，输入姓名进行修改，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_17.png)

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_18.png)

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_19.png)

### 3.6 信息删除界面

信息删除界面，输入姓名找到对应信息，确定后进行删除，如下图。

![](https://lzs-imgs.oss-cn-hangzhou.aliyuncs.com/score-query/ScoreQuery_20.png)

重新查询查无此人。

## 四、参考资料

[1] [Ubuntu完全卸载与安装Mysql](https://blog.csdn.net/leacock1991/article/details/110406708)

[2] [Fixed] [Host is not allowed to connect to this MySQL server](https://www.linuxandubuntu.com/home/sqlstatehy000-1130-host-not-allowed-to-connect-to-this-mysql-server)

[3] [在Ubuntu 18.04 下安装mysql，没有初始密码，重设root密码](https://www.cnblogs.com/williamjie/p/11126486.html)

[4] [Ubuntu 服务器安装 MySQL 教程](https://www.jianshu.com/p/cc01f7773346)

[5] [在ubuntu下修改Mysql字符集的方法](https://blog.csdn.net/yeya24/article/details/81836218)

[6] [MySQL中授权(grant)和撤销授权(revoke)](https://blog.csdn.net/u012349696/article/details/77197284)

[7] [MySQL删除用户](https://www.yiibai.com/mysql/drop-user.html)

[8] [【狂神说Java】GUI编程入门到游戏实战](https://www.bilibili.com/video/BV1DJ411B75F?vd_source=e286040537c5500e12000fae06a47465)

[9] [MySQL数据库教程天花板，mysql安装到mysql高级，强！硬！](https://www.bilibili.com/video/BV1iq4y1u7vj?vd_source=e286040537c5500e12000fae06a47465)

[10] [Java连接MySQL数据库及简单的增删查改操作](https://www.cnblogs.com/wkfvawl/p/11784167.html)

[11] [Java连接MySQL数据库——含详细步骤和代码](https://www.cnblogs.com/town123/p/8336244.html)

[12] [MySQL Connector/J (Archived Versions)](https://downloads.mysql.com/archives/c-j/)

[13] [Java连接MySQL数据库](https://zhuanlan.zhihu.com/p/214178954)

[14] [JAVA通过jdbc连接MYSQL数据库（详细版）](https://www.upstudy.top/index.php/archives/10/)
