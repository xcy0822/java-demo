package com.xcy.javademo.jdk8;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DateTimeDemo {

    public static void main(String[] args) {

        clockDemo();
        instantDemo();
        
        localDateDemo();
        localTimeDemo();
        localDateTimeDemo();
        
        zonedDateTimeDemo();
        
        constantDemo(args);

    }

    public static void clockDemo() {

        // 简单的说下clock：时钟提供给我们用于访问某个特定 时区的 瞬时时间、日期 和 时间的。

        Clock c1 = Clock.systemUTC(); // 系统默认UTC时钟（当前瞬时时间 System.currentTimeMillis()）

        // 这么来会采用系统默认的时区
        Clock c2 = Clock.systemDefaultZone(); // 系统默认时 区时钟（当前瞬时时间）

        // 输出那两个能看到效果
        System.out.println(c1); // SystemClock[Z] 这个其实用得最多
        System.out.println(c2); // SystemClock[Asia/Shanghai]

        // 可以获取到和时区敏感的对象
        Clock c3 = Clock.system(ZoneId.of("Europe/Paris")); // 巴黎时区
        Clock c5 = Clock.offset(c1, Duration.ofSeconds(2)); // 相对于系统默认时钟两秒的时钟
    }

    private static void instantDemo() {
        Instant instant = Instant.now();
        System.out.println(instant); // 2018-08-04T06:35:59.354Z
        System.out.println(instant.getEpochSecond()); // 1533364559
        System.out.println(instant.getNano()); // 354000000

        // 下面是几种获取时间戳(毫秒值)的方法 推荐使用高逼格的toEpochMilli()去做
        System.out.println(instant.toEpochMilli());
        System.out.println(System.currentTimeMillis());

        // 自带的解析 若需要自定义格式，可以这么来
        Instant temp = Instant.parse("2020-07-06T01:19:20.346Z");

        Instant now = Instant.now();
        Instant plusInstant = now.plusSeconds(TimeUnit.HOURS.toSeconds(25));
        // 希望得到两个时间戳，他们相隔了几个小时、几天、几个月？
        System.out.println(now.until(plusInstant, ChronoUnit.HOURS)); // 25
        System.out.println(now.until(plusInstant, ChronoUnit.DAYS)); // 1（这里显示1不是2哦）
        System.out.println(plusInstant.until(now, ChronoUnit.HOURS)); // -25（注意，这里是负数哦）

        Instant start = Instant.now();
        // doSomething();
        Instant end = Instant.now();
        // 计算时间差 采用Duration来处理时间戳的差
        // java.time.Duration表示一段时间。所以像电影持续多久，要做同步字幕的话，用这个类可以很好的解决问题。
        // Duration可以进行multipliedBy()乘法和dividedBy()除法运算。negated()做取反运算，即1.2秒取反后为-1.2秒。
        Duration timeElapsed = Duration.between(start, end);
        long millis = timeElapsed.toMillis();
        System.out.println("millis = " + millis);

    }

    private static void localDateDemo() {
        // 可以使用静态方法now()和of()创建LocalDate。
        // 获取当前日期
        LocalDate now = LocalDate.now();
        // 2017-01-01
        LocalDate newYear = LocalDate.of(2017, 1, 1);
        System.out.println(now); // 2018-08-04
        System.out.println(newYear); // 2017-01-01

        // 显然，内置很多plus、minus的基本计算。with方法相当于修改，但返回的是一个新的日期对象哦
        // 三天后
        now.plusDays(3);
        // 一周后

        now.plusWeeks(1);
        // 两天前
        now.minusDays(2);

        // 备注：增加一个月不会出现2017-02-31 而是会返回该月的最后一个有效日期，即2017-02-28,这点特别的人性化有木有
        LocalDate feb = LocalDate.of(2017, 1, 31).plusMonths(1);

        // LocalDate对应的表示时间段的是Period, Period内部使用三个int值分表表示年、月、日。
        // Duration和Period都是TemporalAmount接口的实现，该接口表示时间量
        // LocalDate 也可以增加或减少一段时间（自由度更高）：

        // 2019-02-01
        feb.plus(Period.ofYears(2));
        // 2015-02-01
        feb.minus(Period.ofYears(2));

        System.out.println(feb.minus(Period.ofYears(2)));
        System.out.println(feb.minus(Period.of(2, 1, 1)));

        // 使用until获得两个日期之间的Period对象
        feb.until(LocalDate.of(2017, 2, 10));// 输出---> P9D

        // 提供isLeapYear判断是否是闰年,这个太友好了

        // DayOfWeek 是个枚举，并且实现了TemporalAccessor/TemporalAdjuster接口，所以也可以直接plus，minus等，非常方便。
        // Java8还提供了Year MonthDay YearMonth来表示部分日期，例如MonthDay可以表示1月1日。
        LocalDate.of(2017, 1, 1).getDayOfWeek();
        DayOfWeek.SUNDAY.plus(2); // TUESDAY

    }

    private static void localTimeDemo() {
        LocalTime now = LocalTime.now();
        LocalTime evning = LocalTime.of(21, 0);
        System.out.println(now); //17:03:13.728
        System.out.println(evning); //10:00
    }
    
    private static void localDateTimeDemo() {
        // LocalDateTime表示一个日期和时间，它适合用来存储确定时区的某个时间点。不适合跨时区的问题。

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println(now); // 2018-08-04T18:33:37.478
        System.out.println(of); // 2018-08-04T18:33:37.478

    }
    
    private static void zonedDateTimeDemo() {
        // ZonedDateTime(带时区的 日期和时间) Java8使用ZoneId来标识不同的时区.
        // 获得所有可用的时区 size=600 这个数字不是固定的
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        // 获取默认ZoneId对象 系统当前所在时区
        ZoneId defZoneId = ZoneId.systemDefault();
        // 获取指定时区的ZoneId对象
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        // ZoneId.SHORT_IDS返回一个Map<String, String> 是时区的简称与全称的映射。下面可以得到字符串 Asia/Shanghai
        String shanghai = ZoneId.SHORT_IDS.get("CTT");
        System.out.println(shanghai); // Asia/Shanghai

        // IANA(Internet Assigned Numbers Authority，因特网拨号管理局)维护着一份全球所有已知的时区数据库，
        // 每年会更新几次，主要处理夏令时规则的改变。Java使用了IANA的数据库。

        // 2017-01-20T17:35:20.885+08:00[Asia/Shanghai]
        ZonedDateTime now = ZonedDateTime.now();
        // 2017-01-01T12:00+08:00[Asia/Shanghai]
        ZonedDateTime of = ZonedDateTime.of(2017, 1, 1, 12, 0, 0, 0, ZoneId.of("Asia/Shanghai"));
        // 使用一个准确的时间点来创建ZonedDateTime，下面这个代码会得到当前的UTC时间，会比北京时间早8个小时
        ZonedDateTime utc = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
        System.out.println(now); // 2018-08-04T18:53:24.686+08:00[Asia/Shanghai]
        System.out.println(of); // 2017-01-01T12:00+08:00[Asia/Shanghai]
        System.out.println(utc); // 2018-08-04T10:53:24.687Z[UTC]

        // LocalDateTime转换为带时区的ZonedDateTime
        // atZone方法可以将LocalDateTime转换为ZonedDateTime，下面的方法将时区设置为UTC。
        // 假设现在的LocalDateTime是2017-01-20 17:55:00 转换后的时间为2017-01-20 17:55:00[UTC]
        LocalDateTime.now().atZone(ZoneId.of("UTC"));
        // 使用静态of方法创建zonedDateTime
        ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"));

    }

    public static void constantDemo(String[] args) {
        // Instant的常量
        System.out.println(Instant.MIN); // -1000000000-01-01T00:00:00Z
        System.out.println(Instant.MAX); // +1000000000-12-31T23:59:59.999999999Z

        // LocaDate的常量
        System.out.println(LocalDate.MIN); // -999999999-01-01
        System.out.println(LocalDate.MAX); // +999999999-12-31

        // LocalTime的常量
        System.out.println(LocalTime.MIN); // 00:00
        System.out.println(LocalTime.MAX); // 23:59:59.999999999
        System.out.println(LocalTime.MIDNIGHT); // 00:00
        System.out.println(LocalTime.NOON); // 12:00

        // LocalDateTime的常量
        System.out.println(LocalDateTime.MIN); // -999999999-01-01T00:00
        System.out.println(LocalDateTime.MAX); // +999999999-12-31T23:59:59.999999999

        // ZoneOffset的常量
        System.out.println(ZoneOffset.UTC); // Z
        System.out.println(ZoneOffset.MIN); // -18:00
        System.out.println(ZoneOffset.MAX); // +18:00

        // ZoneId的常量
        System.out.println(ZoneId.SHORT_IDS); // {CTT=Asia/Shanghai, ART=Africa/Cairo, CNT=America/St_Johns,
                                              // PRT=America/Puerto_Rico
    }


}
