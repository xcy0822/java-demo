package com.xcy.javademo.jdk8;

import java.time.*;

/**
 * Local(本地) − 简化了日期时间的处理，没有时区的问题。
 *
 * Zoned(时区) − 通过制定的时区处理日期时间。
 *
 * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
 */
public class DateTimeApi {
   public static void main(String args[]){
      DateTimeApi dateTimeApi = new DateTimeApi();

      System.out.println("本地时间输出：");
      dateTimeApi.testLocalDateTime();

      System.out.println("带时区时间输出：");
      dateTimeApi.testZonedDateTime();
   }
    
   public void testLocalDateTime(){
    
      // 获取当前的日期时间
      LocalDateTime currentTime = LocalDateTime.now();
      System.out.println("当前时间: " + currentTime);
        
      LocalDate date1 = currentTime.toLocalDate();
      System.out.println("date1: " + date1);
        
      Month month = currentTime.getMonth();
      System.out.println("Month_num：" + month.getValue());
      System.out.println("月: " + month +", 日: " + currentTime.getDayOfMonth() +", 秒: " + currentTime.getSecond());
        
      LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
      System.out.println("date2: " + date2);
        
      // 12 december 2014
      LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
      System.out.println("date3: " + date3);
        
      // 22 小时 15 分钟
      LocalTime date4 = LocalTime.of(22, 15);
      System.out.println("date4: " + date4);
        
      // 解析字符串
      LocalTime date5 = LocalTime.parse("20:15:30");
      System.out.println("date5: " + date5);
   }

   public void testZonedDateTime(){

      // 获取当前时区日期
      ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
      System.out.println("date1: " + date1);

      ZoneId id = ZoneId.of("Europe/Paris");
      System.out.println("ZoneId: " + id);

      ZoneId currentZone = ZoneId.systemDefault();
      System.out.println("当期时区: " + currentZone);
   }
}