package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * https://www.nowcoder.com/practice/5af18ba2eb45443aa91a11e848aa6723?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 字符串排序 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            in.nextLine();
            TreeMap<String, Integer> map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                map.compute(in.nextLine(), (k, v) -> {
                    if (v == null) {
                        v = 0;
                    }
                    v += 1;
                    return v;
                });
            }
            map.forEach((k, v) -> {
                if (v > 1) {
                    for (int i = 0; i < v; i++) {
                        System.out.println(k);
                    }
                } else {
                    System.out.println(k);
                }
            });
        }
    }
}
