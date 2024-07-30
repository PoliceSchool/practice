package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

public class 质数因子 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLong()) { // 注意 while 处理多个 case
            long a = in.nextLong();
            long n = (long) Math.sqrt(a);
            for (long i = 2; i <= n; i++) {
                if (a % i == 0) {
                    a /= i;
                    System.out.print(i + " ");
                    i--;
                }
            }
            System.out.println(a == 1 ? "" : a + "");
        }
    }
}
