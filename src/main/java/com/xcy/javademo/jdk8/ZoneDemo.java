 package com.xcy.javademo.jdk8;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class ZoneDemo {
     
    /*ZoneOffset 于 ZoneId,不随着由夏令时导致的区域偏移的更改。
    ZoneOffset 表示与UTC时区偏移的固定区域。不随着由夏令时导致的区域偏移的更改。
    UTC是UTC的时区偏移常量（Z用作UtC时区的区域偏移指示符。）。MAX和MIN是最大和最小支持的区域偏移。*/


    public static void main(String[] args) {
        // 一般只会用到Hours的偏移
        ZoneOffset zoneOffset1 = ZoneOffset.ofHours(-1); // -01:00
        System.out.println(zoneOffset1);
        ZoneOffset zoneOffset2 = ZoneOffset.ofHoursMinutes(6, 30); // +06:30
        System.out.println(zoneOffset2);
        ZoneOffset zoneOffset3 = ZoneOffset.ofHoursMinutesSeconds(9, 30, 45); // +09:30:45
        System.out.println(zoneOffset3);

        // 以下代码显示如何从偏移创建区域偏移。
        ZoneOffset zoneOffset4 = ZoneOffset.of("+05:00"); // +05:00
        ZoneOffset zoneOffset5 = ZoneOffset.of("Z"); // Z 效果同：ZoneOffset.UTC
        System.out.println(zoneOffset4);
        System.out.println(zoneOffset5);

        /*API支持-18:00到+18:00之间的区域偏移。
        ZoneId 表示区域偏移及其用于更改区域偏移的规则夏令时。 每个时区都有一个ID，可以用三种格式定义：
        ①在区域偏移中，可以是“Z”，“+ hh:mm:ss”或“-hh:mm:ss”，例如“+01:00”。
        ②前缀为“UTC”，“GMT”或“UT”，后跟区域偏移量，例如“UTC + 01:00”。
        ③在区域名称中，例如，“美洲/芝加哥”。（比较常用)
        */

        // 备注：此字符串必须合法 否则报错
        ZoneId usChicago = ZoneId.of("Asia/Shanghai"); // Asia/Shanghai
        System.out.println(usChicago);
        ZoneId fixedZoneId = ZoneId.of("+01:00");
        System.out.println(fixedZoneId); // +01:00

        // ZoneId 中的 getAvailableZoneIds()返回所有已知时区ID。
        System.out.println(ZoneId.systemDefault()); // Asia/Shanghai
        System.out.println(ZoneId.getAvailableZoneIds()); // [Asia/Aden, America/Cuiaba, Etc/GMT+9, Etc/GMT+8

        // 使用java8我们知道使用ZoneId.default()可以获得系统默认值ZoneId，但如何获取默认值ZoneOffset？
        System.out.println(ZoneOffset.of("+8")); // +08:00
        System.out.println(ZoneOffset.ofHours(8)); // +08:00

        // 获取系统的默认值==================推荐使用
        System.out.println(OffsetDateTime.now().getOffset()); // +08:00
        System.out.println(ZoneId.systemDefault()); // Asia/Shanghai
    }

}
