package com.xcy.javademo.jdk8;

import com.xcy.javademo.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduce {

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

/*      Identity标识：一个元素，它是归约操作的初始值，如果流为空，则为默认结果。
        Accumulator累加器：具有两个参数的函数：归约运算的部分结果和流的下一个元素。
        Combiner合并器（可选）：当归约并行化时，或当累加器参数的类型与累加器实现的类型不匹配时，用于合并归约操作的部分结果的函数*/

        // 累加器：基本数据类型
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println(result);  //21

        int result1 = numbers
                .stream()
                .reduce(0, Integer::sum);
        System.out.println(result); //21

        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String resultString = letters
                .stream()
                .reduce("", (partialString, element) -> partialString + element);
        System.out.println(result);  //abcde


        String resultString2 = letters
                .stream()
                .reduce("", String::concat);
        System.out.println(result);  //ancde

        // 累加器：对象
        Integer total = employees.stream().map(Employee::getAge).reduce(0,Integer::sum);
        System.out.println(total); //346


        // Combiner合并器：除了使用map函数实现类型转换后的集合归约，我们还可以用Combiner合并器来实现，这里第一次使用到了Combiner合并器。
        // 此处参数三（Integer::sum）是 BinaryOperator<U> combiner,只有和并行流处理才有作用，用于多个并行流的结果处理
        Integer total2 = employees.stream()
                .reduce(0,(totalAge,emp) -> totalAge + emp.getAge(),Integer::sum);
        //注意这里reduce方法有三个参数,参数3不起作用
        System.out.println(total2); //346

        // Combiner合并器：并行流数据规约
        Integer total3 = employees
                .parallelStream()
                .map(Employee::getAge)
                .reduce(0,Integer::sum,Integer::sum);  //注意这里reduce方法有三个参数

        System.out.println(total3); //346
    }
}
