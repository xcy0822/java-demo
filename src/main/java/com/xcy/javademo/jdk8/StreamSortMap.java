package com.xcy.javademo.jdk8;

import com.xcy.javademo.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class StreamSortMap {

    public static void main(String[] args) {
        String k = "key";
        HashMap<String, Integer> map = new HashMap<String, Integer>() {{
            put(k, 1);
        }};
        map.merge(k, 2, (oldVal, newVal) -> oldVal + newVal);
        System.out.println(map.toString());

        // 创建一个Map，并填入数据
        Map<String, Integer> codes = new HashMap<>();
        codes.put("United States", 1);
        codes.put("Germany", 49);
        codes.put("France", 33);
        codes.put("China", 86);
        codes.put("Pakistan", 92);

        // 按照Map的键进行排序
        Map<String, Integer> sortedMap = codes.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        )
                );
        // 将排序后的Map打印
        sortedMap.entrySet().forEach(System.out::println);

        // sort the map by values
        Map<String, Integer> sorted = codes.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new));
        sorted.entrySet().forEach(System.out::println);

    }
}
