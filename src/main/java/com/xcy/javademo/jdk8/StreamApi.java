package com.xcy.javademo.jdk8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi {

    public static void main(String[] args) throws IOException {
        List<String> nameStrs = Arrays.asList("Monkey", "Lion", "Giraffe","Lemur");

        // 替代循环且不改变数据
        List<String> list = nameStrs.stream()
                .filter(s -> s.startsWith("L"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(list);

        // Stream.of()将数组转换为管道流
        String[] array = {"Monkey", "Lion", "Giraffe", "Lemur"};
        Stream<String> nameStrs2 = Stream.of(array);
        Stream<String> nameStrs3 = Stream.of("Monkey", "Lion", "Giraffe", "Lemur");

        // stream()将集合类对象转化为管道流
        Stream<String> streamFromList = list.stream();
        Set<String> set = new HashSet<>(list);
        Stream<String> streamFromSet = set.stream();

        // Files.lines方法将文本文件转换为管道流
        Stream<String> lines = Files.lines(Paths.get("file.txt"));

    }

}
