package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

public class 走方格的方案数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int m = in.nextInt();
            int n = in.nextInt();
            System.out.println(cal(m,n));
        }
    }

    private static int cal(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }
        return cal(m, n-1) + cal(m-1, n);
    }

}
