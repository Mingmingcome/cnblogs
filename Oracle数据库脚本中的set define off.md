`2018年8月6日15:11:34`

## Oracle数据库脚本中的set define off

#### 前言

最近在公司写需求，接触到脚本，第一句`set define off;`就不知道什么意思了，查询后记录之。

#### 名称

SET DEFINE

#### 概要

SET DEFINE命令改变标记替代变量的前缀字符。你可以使用SET DEFINE关闭替代变量。

#### 语法

```sql
SET DEF[INE] {OFF | ON | prefix_char}
```

#### 参数

<dl>
	<dt>SET DEF[INE]</dt>
	<dd>是一个命令，可以缩写为SET DEF。</dd>
	<dt>OFF</dt>
	<dd>禁用替代变量。</dd>
	<dt>ON</dt>
	<dd>启用替代变量，并重置替代前缀字符为默认`&`符号。默认情况下，替代变量是开启的。</dd>
	<dt>prefix_char</dt>
	<dd>是新替代前缀字符</dd>
</dl>

当你开启`SQL*Plus`，替代变量会默认开启，且默认前缀字符是`&`字符。如果你运行一个在文本字符串中使用了`&`的脚本，你也许想改变这个前缀字符。如果你的脚本没有使用替代变量，你可以发现关闭这个功能非常容易。

#### 例子

开启替代变量：
```sql
SQL> insert into customers (customer_name) values ('Marks & Spencers Ltd');
Enter value for spencers: 
old   1: insert into customers (customer_name) values ('Marks & Spencers Ltd')
new   1: insert into customers (customer_name) values ('Marks  Ltd')

1 row created.

SQL> select customer_name from customers;

CUSTOMER_NAME
------------------------------
Marks  Ltd
```

禁用替代变量：
```sql
SQL> set define off
SQL> insert into customers (customer_name) values ('Marks & Spencers Ltd');

1 row created.

SQL> select customer_name from customers;

CUSTOMER_NAME
------------------------------
Marks & Spencers Ltd
```

上面的例子你需要customers表，但是有一个表是所有Oracle数据库都拥有的，没错就是dual。如果想了解dual，可以查看本人的文章[select 1 from dual](https://www.cnblogs.com/mingmingcome/p/9310371.html)。
```sql
SQL> set define off;
SQL> select * from dual where dummy='&var';

no rows selected

SQL> set define on
SQL> /
Enter value for var: X
old   1: select * from dual where dummy='&var'
new   1: select * from dual where dummy='X'

D
-
X
```

#### 总结

`set define off;`的作用就是关闭替代变量，默认情况下是开启。

#### 参考
[SET-DEFINE - Oracle `SQL*Plus`: The Definitive Guide, 2nd Edition by Jonathan Gennick](https://www.safaribooksonline.com/library/view/oracle-sqlplus-the/0596007469/re56.html)

[When or Why to use a “SET DEFINE OFF” in Oracle Database](https://stackoverflow.com/questions/34332639/when-or-why-to-use-a-set-define-off-in-oracle-database)

`2018年8月6日16:37:24`