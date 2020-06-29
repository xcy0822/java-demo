package com.xcy.javademo.jdk8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

public class StreamApiOperate {

    public static void main(String[] args) throws IOException {

        // 终端操作
        // parallel并行处理不保证顺序:forEach不保证顺序,forEachOrdered 保证输出和进入管道流顺序一致
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .parallel()
                .forEach(System.out::println);
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .parallel()
                .forEachOrdered(System.out::println);

        //最终collectToSet 中的元素是:[Monkey, Lion, Giraffe, Lemur]，注意Set会去重。
        Set<String> collectToSet = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .collect(Collectors.toSet());

        // 通用收集示例（String[]::new,PriorityQueue::new）
        LinkedList<String> collectToCollection = Stream.of(
                "Monkey", "Lion", "Giraffe", "Lemur", "Lion"
        ).collect(Collectors.toCollection(LinkedList::new));

        // 收集map,最终toMap的结果是: {Monkey=6, Lion=4, Lemur=5, Giraffe=6}
        Map<String, Integer> toMap = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),   //元素输入就是输出，作为key
                        s -> (int) s.chars().distinct().count()// 输入元素的不同的字母个数，作为value
                ));

        // 分组收集 groupingBy第一个参数作为分组条件，第二个参数是子收集器
        Map<Character, List<String>> groupingByList =  Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0) //根据元素首字母分组，相同的在一组
                        // counting()        // 加上这一行代码可以实现分组统计
                ));
        System.out.println(groupingByList.toString());// 最终groupingByList内的元素: {G=[Giraffe], L=[Lion, Lemur, Lion], M=[Monkey]}
        Map<Object, Long> groupingCountingByList =  Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0) ,//根据元素首字母分组，相同的在一组
                        counting()        // 加上这一行代码可以实现分组统计
                ));
        System.out.println(groupingCountingByList.toString());//如果加上counting() ，结果是:  {G=1, L=3, M=1}


        // 其他操作
        boolean containsTwo = IntStream.of(1, 2, 3).anyMatch(i -> i == 2);
        long nrOfAnimals = Stream.of("Monkey", "Lion", "Giraffe", "Lemur").count();
        int sum = IntStream.of(1, 2, 3).sum();
        OptionalDouble average = IntStream.of(1, 2, 3).average();
        int max = IntStream.of(1, 2, 3).max().orElse(0);

        // 全面的统计结果statistics: IntSummaryStatistics{count=3, sum=6, min=1, average=2.000000, max=3}
        IntSummaryStatistics statistics = IntStream.of(1, 2, 3).summaryStatistics();
        System.out.println(statistics);
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getSum());

    }

}
