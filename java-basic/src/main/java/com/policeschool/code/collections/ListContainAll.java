package com.policeschool.code.collections;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;

import java.util.*;

public class ListContainAll {
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        List<String> subList1 = new ArrayList<>();
        subList1.add("11");
        subList1.add("12");
        list.add(subList1);
        List<String> subList2 = new ArrayList<>();
        subList2.add("21");
        subList2.add("22");
        list.add(subList2);
        List<String> subList3 = new ArrayList<>();
        subList3.add("31");
        subList3.add("32");
        list.add(subList3);

//        Set<String> set = new HashSet<>();
//        list.stream().flatMap(Collection::stream).forEach(set::add);
//        System.out.println(set);

        Set<String> set1 = new LinkedHashSet<>();
        set1.add("11");
        set1.add("12");
        set1.add("13");
        List<String> set2 = new ArrayList<>();
        set2.add("21");
        set2.add("12");
        set2.add("13");
        set2.add("22");
        set1.addAll(set2);
        System.out.println(set1);

    }

    public static void eee() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        // 填充数据
        list1.add(1 + "");
        list1.add(2 + "");
        list1.add(3 + "");
        list1.add(4 + "");

        list2.add(3 + "");
        list2.add(5 + "");


        System.out.println(CollUtil.intersection(list1, list2));
    }
}
