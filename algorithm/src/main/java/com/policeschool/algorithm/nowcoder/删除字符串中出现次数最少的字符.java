package com.policeschool.algorithm.nowcoder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class 删除字符串中出现次数最少的字符 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                map.compute(str.charAt(i), (k, v) -> {
                    if (v == null) {
                        v = 0;
                    }
                    v += 1;
                    return v;
                });
            }
            int min = Collections.min(map.values());
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (map.get(ch) != min) {
                    System.out.print(ch);
                }
            }
        }
    }
}
