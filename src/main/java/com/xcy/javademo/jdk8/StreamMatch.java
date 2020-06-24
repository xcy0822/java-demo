package com.xcy.javademo.jdk8;

import com.xcy.javademo.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMatch {

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

        boolean isExistAgeThan70 = employees.stream().anyMatch(Employee.ageGreaterThan70);
        System.out.println(isExistAgeThan70);
        boolean isExistAgeThan10 = employees.stream().allMatch(e -> e.getAge() > 10);
        System.out.println(isExistAgeThan10);
        boolean isExistAgeLess18 = employees.stream().noneMatch(e -> e.getAge() < 18);
        System.out.println(isExistAgeLess18);


        // 从列表中按照顺序查找第一个年龄大于40的员工。
        // findFirst用于查找第一个符合“匹配规则”的元素，返回值为Optional
        // findAny用于查找任意一个符合“匹配规则”的元素，返回值为Optional
        Optional<Employee> employeeOptional =  employees.stream().filter(e -> e.getAge() > 40).findFirst();
        System.out.println(employeeOptional.get());


        /* Optional类代表一个值存在或者不存在。在java8中引入，这样就不用返回null了。
        isPresent() 将在 Optional 包含值的时候返回 true , 否则返回 false 。
        ifPresent(Consumer block) 会在值存在的时候执行给定的代码块。我们在第3章
        介绍了 Consumer 函数式接口；它让你传递一个接收 T 类型参数，并返回 void 的Lambda
        表达式。
        T get() 会在值存在时返回值，否则?出一个 NoSuchElement 异常。
        T orElse(T other) 会在值存在时返回值，否则返回一个默认值。*/

    }
}
