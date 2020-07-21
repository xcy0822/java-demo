package com.xcy.javademo.jdk8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeFormatDemo {

    public static void main(String[] args) {
        // 字符串转化为日期对象
        String dateStr = "2016年10月25日";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // 日期转换为字符串
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm a");
        String nowStr = now.format(format);
        System.out.println(nowStr); // 2018年08月07日 12:15 上午

        // DateTimeFormatter预定义了一些格式，可以直接调用format方法，方便调用者使用
        // 2017-01-01
        DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(2017, 1, 1));
        // 20170101
        DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.of(2017, 1, 1));
        // 2017-01-01T09:10:00
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.of(2017, 1, 1, 9, 10, 0));

        // 根据当前操作系统语言环境，有SHORET MEDIUM LONG FULL 四种不同的风格来格式化。
        // 可以通过DateTimeFormatter的静态方法ofLocalizedDate ofLocalizedTime ofLocalizedDateTime

        // 使用自定义模式格式化
        // 2017-02-27 22:48:52
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        // 使用的ISO_LOCAL_DATE格式解析 2017-01-01
        LocalDate.parse("2017-01-01");
        // 使用自定义格式解析 2017-01-01T08:08:08
        LocalDateTime.parse("2017-01-01 08:08:08", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 在DateTimeFormatter中还有很多定义好的格式，有兴趣的可以自己去看一下
        
        
        //时间戳转instant就很简单了
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println(instant); //2018-08-06T16:26:08.539Z（其实已经24点了，所以直接输出是有时区问题的 需要注意）

        //Date直接转Instant
        System.out.println(new Date().toInstant()); //2018-08-06T16:26:08.539Z
        //Instant --> Date
        Date.from(Instant.now());

        //Calendar --> Instant（这个用得很少）
        Calendar.getInstance().toInstant();

        
        // 建议JDK8之前格式化
        format(new Date());
        
        // spring 实体值 解析  ： @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        // private LocalDateTime localDateTime;
        
        // spring mvc 参数 解析
        // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime localDateTime

    }
    
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
 
    //和线程绑定 保证安全
    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

}
