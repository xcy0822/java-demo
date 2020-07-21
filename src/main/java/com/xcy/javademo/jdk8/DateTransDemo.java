package com.xcy.javademo.jdk8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTransDemo {
    public static void main(String[] args) {

        // 策略模式
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.from(localDateTime);
        LocalTime localTime = LocalTime.from(localDateTime);
        System.out.println(localDate); // 2018-08-13
        System.out.println(localTime); // 16:04:48.356

        /////////////////////// 下面的会报错哟///////////////////////

        // LocalTime localTime = LocalTime.now();
        // LocalDate localDate = LocalDate.from(localTime); //这样转是会报错的 因为LocalTime不含有Date元素 Unable to obtain LocalDate
        // from TemporalAccessor: 16:01:47.541 of type java.time.LocalTime
        // LocalDateTime localDateTime = LocalDateTime.from(localTime); //这样转也是会报错的 因为不含有date元素
        // System.out.println(localTime);
        // System.out.println(localDateTime);

        // 这里面需要注意一个坑：他们转换的中间桥梁都是时间戳Instant对象，但是转换的时候如果没有考虑时区，就会报错的。
        // 比如下面这个例子，看起来顺滑，其实异常了：

        /*         Date date = new Date();
         Instant instant = date.toInstant();
         //看起来非常顺滑 但其实 异常：Unable to obtain LocalDate from TemporalAccessor: 2018-08-31T02:41:28.076Z of type java.time.Instant
         LocalDate from = LocalDate.from(date.toInstant());*/

        // 其实这个也好理解。人家Date是带有日期和时间的，然后突然来一个只需要日期的，LocalDate不知道咋处理
        // （或者说JDK8没考虑到这一点，其实不是，因为时区没定，LocalDate自己不好自己做定论），所以不允许直接转换也可以理解。
        // 所以各位使用起一定要小心使用了
        // Date和LocalDate、LocalTime等互相转化的的思想也很简单 借助LocalDateTime对象就万无一失了。

        Date date = new Date();
        Instant instant = date.toInstant();

        // 以ZoneId.systemDefault转换成LocalDateTime后，就可以随意转换了
        LocalDateTime defaultLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        // 方式一：使用LocalDate、LocalTime的from
        LocalDate fromLocalDate = LocalDate.from(defaultLocalDateTime);
        LocalTime fromLocalTime = LocalTime.from(defaultLocalDateTime);
        System.out.println(fromLocalDate); // 2018-08-31
        System.out.println(fromLocalTime); // 11:03:19.716

        // 方式二：直接to的方式
        LocalDate toLocalDate = defaultLocalDateTime.toLocalDate();
        LocalTime toLocalTime = defaultLocalDateTime.toLocalTime();
        System.out.println(toLocalDate); // 2018-08-31
        System.out.println(toLocalTime); // 11:03:19.716

        // 反向转换：借助的中间变量是Instant即可
        toDate();

    }

    private static void toDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        Instant instant = null;
        ZoneId zone = ZoneId.systemDefault();

        // LocalDateTime转Instant转Date
        instant = localDateTime.atZone(zone).toInstant();
        System.out.println(Date.from(instant));

        // LocalDate转Instant转Date
        instant = localDate.atStartOfDay().atZone(zone).toInstant();
        System.out.println(Date.from(instant));

        // LocalTime转Instant转Date（很麻烦 一般杜绝这样使用吧）
        // 必须先借助localDate转换成localDateTime 在转成instant 再转date
        LocalDateTime localDateTimeDate = LocalDateTime.of(localDate, localTime);
        instant = localDateTimeDate.atZone(zone).toInstant();
        System.out.println(Date.from(instant));
    }

}
