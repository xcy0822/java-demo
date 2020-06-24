package com.xcy.javademo.jdk8;

import com.xcy.javademo.model.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSort {

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

        // 流排序
        List<String> cities = Arrays.asList("Milan", "london", "San Francisco", "Tokyo", "New Delhi");
        cities.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);

        // 集合排序 String.CASE_INSENSITIVE_ORDER Comparator.naturalOrder()   Comparator.reverseOrder()
        List<Integer> numbers = Arrays.asList(6, 2, 1, 4, 9);
        numbers.sort(Comparator.naturalOrder());  //自然排序

        // Comparator链对List<Object>排序
        employees.sort(Comparator.comparing(Employee::getAge));
        // 如果我们希望List按照年龄age的倒序排序，就使用reversed()方法。如：
        employees.sort(Comparator.comparing(Employee::getAge).reversed());

        /* 都是正序 ，不加reversed
           都是倒序，最后面加一个reserved
           先是倒序（加reserved），然后正序
           先是正序（加reserved），然后倒序（加reserved）*/

        // 下面这段代码先是按性别的倒序排序，再按照年龄的倒序排序。
        employees.sort(
                Comparator.comparing(Employee::getGender)
                        .thenComparing(Employee::getAge)
                        .reversed()
        );
        employees.forEach(System.out::println);

        // 函数式接口
        /* 接口有且仅有一个抽象方法，Comparator抽象方法compare
        允许定义静态非抽象方法
        允许定义默认defalut非抽象方法（default方法也是java8才有的，见下文）
        允许java.lang.Object中的public方法，如上图的方法equals。
        FunctionInterface注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。
        如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错
        甚至可以说：函数式接口是专门为lambda表达式准备的，lambda表达式是只实现接口中唯一的抽象方法的匿名实现类。*/

        employees.sort((em1,em2) -> {
            if(em1.getAge() == em2.getAge()){
                return 0;
            }
            return em1.getAge() - em2.getAge() > 0 ? -1:1;
        });

    }
}
