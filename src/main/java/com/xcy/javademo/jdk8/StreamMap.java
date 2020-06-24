package com.xcy.javademo.jdk8;

import com.xcy.javademo.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMap {

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

        // map simple
        List<Integer> mapSimple = annimals.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(mapSimple); //[6, 4, 7, 5]

        // equal
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .mapToInt(String::length)
                .forEach(System.out::println);

        // 对象处理
        List<Employee> mapObject = employees.stream()
                .peek(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M")?"male":"female");
                }).collect(Collectors.toList());
        System.out.println(mapObject);

        // 多层流（管道）处理，需要flatmap
        List<String> words = Arrays.asList("hello", "word");
        words.stream()
               // .map(w -> Arrays.stream(w.split("")))    //[[h,e,l,l,o],[w,o,r,l,d]]
                .flatMap(w -> Arrays.stream(w.split("")))
                .forEach(System.out::println);

    }
}
