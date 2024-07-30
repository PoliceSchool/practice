package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/3ab09737afb645cc82c35d56a5ce802a?tpId=37&tqId=21230&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=1&judgeStatus=undefined&tags=&title=
 */
public class 取近似值 {

    public static void exec() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextDouble()) {
            double a = in.nextDouble();
            int b = (int) a;
            System.out.println((a - b) >= 0.5 ? b + 1 : b);
        }
    }

    public static void main(String[] args) {
        // 测试用例2.499 , 5.5
        exec();
    }
}
