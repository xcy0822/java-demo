package com.xcy.javademo.jdk8;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ForeachDemo {

    public static void main(String[] args) {

        // 我们可以通过实现Consumer接口的accept方法，用来表示一个接受单个输入参数且不返回结果的操作
        List<String> list = Arrays.asList("xiaozhou", "xiaohong", "xiaoming");
        Consumer<String> printlnConsumer = System.out::println;
        list.forEach(printlnConsumer);

        Consumer<String> printlnUpperConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toUpperCase());
            }
        };
        list.forEach(printlnUpperConsumer);

        // 遍历map
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A",1);
        hashMap.put("B",2);
        hashMap.put("C",3);

        BiConsumer<String, Integer> printlnBiConsumer = (k,v) -> System.out.println(k + "=" + v);
        hashMap.forEach(printlnBiConsumer);

        Consumer<Map.Entry<String, Integer>> entryConsumer = System.out::println;
        hashMap.entrySet().forEach(entryConsumer);

        Consumer<String> keyConsumer = System.out::println;
        hashMap.keySet().forEach(keyConsumer);

        Consumer<Integer> valueConsumer = System.out::println;
        hashMap.values().forEach(valueConsumer);

    }
}
