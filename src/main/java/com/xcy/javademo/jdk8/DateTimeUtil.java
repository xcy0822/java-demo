package com.xcy.javademo.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateTimeUtil {

    public static boolean isEqual(LocalDate localDate1, LocalDate localDate2) {
        if (Objects.isNull(localDate1) || Objects.isNull(localDate2)) {
            return localDate1 == localDate2;
        }

        return localDate1.equals(localDate2);
    }

    /**
     * @description 使用场景示例：生日判断
     * @param localDate1
     * @param localDate2
     * @return
     */
    public static boolean isRepreatMonthDay(LocalDate localDate1, LocalDate localDate2) {
        if (Objects.isNull(localDate1) || Objects.isNull(localDate2)) {
            return false;
        }

        return MonthDay.from(localDate1).equals(MonthDay.from(localDate2));
    }

    public static LocalDate getPlusedLocalDate(long amountToAdd, ChronoUnit unit){
        // LocalDate是用来表示无时间的日期，他又一个plus()方法可以用来增加日，星期，月，ChronoUnit则用来表示时间单位
       LocalDate localDate = LocalDate.now();
       
       return localDate.plus(amountToAdd, unit);
    }
    
    /**
     * @description 使用场景示例：信用卡年月过期问题
     * @param yearMonth
     * @return
     */
    public static boolean isYearMonthPass(YearMonth yearMonth) {
        
        YearMonth nowYearMonth = YearMonth.now();
        return nowYearMonth.isAfter(yearMonth);
    }
    
    public static long getPeroidUnit(LocalDate startDateInclusive, LocalDate endDateExclusive,ChronoUnit unit){
        
        Period period = Period.between(startDateInclusive, endDateExclusive);
        return period.get(unit);
    }
    
    public static long getPeroidTotalUnit(LocalDate startDateInclusive, LocalDate endDateExclusive,ChronoUnit unit){
        
        return endDateExclusive.until(startDateInclusive, unit);
    }

    /**
     * @description 测试 ZoneOffset.of("+05:00")
     * @param localDateTime
     * @param offset
     * @return
     */
    public static OffsetDateTime getZoneOffsetTime(LocalDateTime localDateTime, ZoneOffset offset) {
        
        // OffsetDateTime主要是用来给机器理解的，平时使用就用前面结束的ZoneDateTime类就可以了
        return OffsetDateTime.of(localDateTime, offset);
    }
    
    /**
     * @description 获取两个日期中间的所有日期
     * @param start
     * @param end
     * @return
     */
    public static List<LocalDate> getPeriodDayList(LocalDate start, LocalDate end) {
        
        long distance = ChronoUnit.DAYS.between(start, end);
        
        return Stream.iterate(start, d -> d.plusDays(1)).limit(distance + 1).collect(Collectors.toList());
    }
    
}
