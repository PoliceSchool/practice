package com.policeschool.code.collections;

import java.util.ArrayList;
import java.util.List;

public class ListRemoveAll {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        // 填充数据
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        list2.add(3);
        list2.add(5);

        // 删除list1中与list2相同的元素
        list2.removeAll(list1);

        System.out.println(list2);
    }
}