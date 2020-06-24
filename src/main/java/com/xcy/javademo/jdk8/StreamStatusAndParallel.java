package com.xcy.javademo.jdk8;

import com.xcy.javademo.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStatusAndParallel {

    public static void main(String[] args) {
        Employee e1 = new Employee(1, 23, "M", "Rick", "Beethovan");
        Employee e2 = new Employee(2, 13, "F", "Martina", "Hengis");
        Employee e3 = new Employee(3, 43, "M", "Ricky", "Martin");
        Employee e4 = new Employee(4, 26, "M", "Jon", "Lowman");
        Employee e5 = new Employee(5, 19, "F", "Cristine", "Maria");
        Employee e6 = new Employee(6, 15, "M", "David", "Feezor");
        Employee e7 = new Employee(7, 68, "F", "Melissa", "Roy");
        Employee e8 = new Employee(8, 79, "M", "Alex", "Gussin");
        Employee e9 = new Employee(9, 15, "F", "Neetu", "Singh");
        Employee e10 = new Employee(10, 45, "M", "Naveen", "Jain");


        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

        List<String> annimals = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");

        // 关联其他元素的，是有状态，如limit,sort，order; 无状态，如 map,filter,flatmap

        //  limit 和 skip 数据截取
        List<String> limitN = annimals.stream()
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(limitN); //[Monkey, Lion]

        List<String> skipN = annimals.stream()
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(skipN); //[Giraffe, Lemur]

        // distinct去重
        List<String> uniqueAnimals = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueAnimals);

        // sorted排序
        List<String> alphabeticOrder = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .sorted()
                .collect(Collectors.toList());
        System.out.println(alphabeticOrder);


        /*并行处理与串行处理
        串行的好处是可以保证顺序，通常速度慢
        并行通常快，但是顺序无法保证。可能会导致有状态操作，结果错误
        适用于无状态操作：每个元素的计算都不得依赖或影响任何其他元素的计算，的运算场景。
        基础数据源无变化：从文本文件里面边读边处理的场景，不适合parallel()并行处理。parallel()一开始就容量固定的集合，
        这样能够平均的拆分、同步处理。*/
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .parallel()
                .forEach(System.out::println);
    }
}
