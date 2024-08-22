package com.policeschool.code.collections;

import java.util.ArrayList;
import java.util.List;

public class ListCopy {
    public static void main(String[] args) {
        // 原始列表
        List<String> originalList = new ArrayList<>();
        originalList.add("item1");
        originalList.add("item2");
        originalList.add("item3");

        // 使用构造函数复制列表
        List<String> copiedList = new ArrayList<>(originalList);
        copiedList.remove(1);

        // 输出复制后的列表
        for (String item : copiedList) {
            System.out.println(item);
        }
        for (String item : originalList) {
            System.out.println(item);
        }
    }
}
