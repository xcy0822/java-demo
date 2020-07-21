package com.xcy.javademo.jdk8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjusterDemo {

    public static void main(String[] args) {
        LocalDateTime ldt1 = LocalDateTime.now();
        // 本月第一天
        LocalDateTime ldt2 = ldt1.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(ldt2); // 2018-08-01T17:34:42.039
        // 本月的第一个周五
        LocalDateTime ldt3 = ldt1.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
        System.out.println(ldt3); // 2018-08-03T17:41:07.619
        
        
        LocalDate localDate = LocalDate.now();
        //下一个工作日（不考虑法定节假日的情况）  自己实现一个时间矫正器
        LocalDate with = localDate.with(x -> {
            LocalDate date = LocalDate.class.cast(x);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                return date.plusDays(3);
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                return date.plusDays(2);
            } else {
                return date.plusDays(1);
            }
        });
        System.out.println(with); //2020-07-07
        
    }

}
