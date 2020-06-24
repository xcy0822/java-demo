package com.xcy.javademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Predicate;

@Data
@AllArgsConstructor
public class Employee {
    private Integer id;
    private Integer age;   //年龄
    private String gender;  //性别
    private String firstName;
    private String lastName;

    // Predicate 表示谓词逻辑（做什么，是什么）,作用同sql中where条件，可用作逻辑组合
    public static Predicate<Employee> ageGreaterThan70 = x -> x.getAge() >70;
    public static Predicate<Employee> genderM = x -> x.getGender().equals("M");
}
