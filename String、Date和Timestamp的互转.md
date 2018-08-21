`begin 2018年8月17日19:09:49`

## String、Date和Timestamp的互转

#### String和Date的互转

关于String和Date的互转，在java8后会有不同。因为java8添加java.time包及子包，其中主要API是关于日期、时间、时刻和时间段及它们之间的转换和打印输出，比较重要一点的是java.time中LocalDate、LocalTime、LocalDateTime都是线程安全的。有兴趣可以查看官网的描述：[Package java.time](https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html)和[Package java.time.format](https://docs.oracle.com/javase/8/docs/api/java/time/format/package-summary.html)。

java8前是java.text.SimpleDateFormat类中的两个方法：
``` java
// String -> Date
public Date parse(String source) throws ParseException;
// Date -> String
public final String format(Date date);
```

java8中java.time.format.DateTimeFormatter类中：
``` java
// 静态工厂方法构造DateTimeFormatter对象
public static DateTimeFormatter ofPattern(String pattern);
```

java.time中几乎所有的类都有parse、format这两个方法。如类LocalDataTime中：
``` java
// String -> DateTime的天然转化器
public static LocalDateTime parse(CharSequence text);
public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter);
// DataTime -> String
public String format(DateTimeFormatter formatter);
```

``` java
// 1.1 String -> Date 
@Test
public static void testStringToDate() throws ParseException {
	String str = "2018/08/16 20:07:56";
	// 1.1.1 java8前
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = sdf.parse(str); // Thu Aug 16 20:07:56 CST 2018
	System.out.println(date);
	
	// 1.1.2 java8 默认格式：yyyy-MM-dd'T'HH:mm:ss(即2007-12-03T10:15:30)
	DateTimeFormatter fommatter =DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime dateTime  = LocalDateTime.parse(str, fommatter); // 2018-08-16T20:07:56
	System.out.println(dateTime);
}

// 1.2 Date -> String
@Test
public static void testDateToString() {
	// 1.2.1 java8前
	Date date = new Date();
	// 1.2.1.1 直接打印，toString()方法在下面是多余的，只是为了显式地表示通过toString()方法转化为String，下同
	System.out.println(date.toString()); // Fri Aug 17 17:06:41 CST 2018
	// 1.2.1.2 格式化输出字符串
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String str1 = sdf1.format(date); // 2018/08/17 17:06:41
	String str2 = sdf2.format(date); // 2018-08-17 17:06:41
	System.out.println(str1);
	System.out.println(str2);
	
	// 1.2.2 java8
	LocalDateTime dateTime = LocalDateTime.now();
	// 1.2.2.1 toString()
	System.out.println(dateTime.toString()); // 2018-08-17T17:08:51.874
	// 1.2.2.2 格式化输出字符串
	// 2018/08/17 17:08:51
	String str3 = dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	// 2018-08-17 17:08:51.8
	String str4 = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
	System.out.println("yyyy/MM/dd HH:mm:ss: " + str3);
	System.out.println("yyyy-MM-dd HH:mm:ss.S: " + str4);
}
```

#### String和Timestamp的互转

``` java
// 2.1 String -> Timestamp
@Test	
public static void testStringToTimestamp() throws ParseException {
	// 2.1.1 参数为默认格式yyyy-[m]m-[d]d hh:mm:ss[.f...]
	// timestamp in format yyyy-[m]m-[d]d hh:mm:ss[.f...]
	// 时间戳格式是yyyy-[m]m-[d]d hh:mm:ss[.f...]。中括号部分可省略。[.f...]没有的话，会用".0"代替。
	String str = "2018-08-16 20:07:56";
	Timestamp ts = Timestamp.valueOf(str); //2018-08-16 20:07:56.0
	System.out.println(ts);
	
	// 2.1.2 参数为其他格式，需转化
	String str1 = "2018/08/17 09:42:36.23";
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SS");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
	// 先按照原来的格式转化为Date
	Date date = sdf1.parse(str1);
	// 再按照默认格式转化为String
	String str2 = sdf2.format(date);
	// 最后调用Timestamp.valueOf转化为timestamp
	Timestamp ts1 = Timestamp.valueOf(str2); // 2018-08-17 09:42:36.23
	System.out.println(ts1);
	
	// 2.1.3 参数为其他格式，需要转化（java8）
	String str3 = "2018/08/17 09:42:36.12";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SS");
	LocalDateTime dateTime = LocalDateTime.parse(str3, formatter);
	Timestamp ts2 = Timestamp.valueOf(dateTime); // 2018-08-17 09:42:36.12
	System.out.println(ts2);
}

// 2.2 Timestamp -> String
@Test
public static void testTimestampToString() {
	// 2.2.1 toString()
	// 2018-08-17 17:30:06.648
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	System.out.println(timestamp.toString());
	
	// 2.2.1 格式化输出字符串
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	// 2018/08/17 17:30:06
	String str = sdf.format(timestamp);
	System.out.println(str);
	
}
```


#### String和Timestamp的互转

``` java
// 3.1 Date -> Timestamp
@Test
public static void testDateToTimestamp() {
	Date date = new Date();
	Timestamp timestamp = new Timestamp(date.getTime()); // 2018-08-17 17:43:09.796
	System.out.println(timestamp);
}

// 3.2 Timestamp -> Date
@Test
public static void testTimestampToDate() {
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	// 3.2.1 直接赋值，Date是Timestamp父类
	Date date = timestamp; // 2018-08-17 17:51:30.507
	System.out.println(date);
	// 3.2.2 创建新对象Date
	date =  new Date(timestamp.getTime());// Fri Aug 17 17:49:28 CST 2018
	System.out.println(date);
}
```


参考：
[Java：String和Date、Timestamp之间的转换](http://yunnick.iteye.com/blog/1074495)

`end 2018年8月17日20:43:22`

