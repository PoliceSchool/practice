package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

public class 计算日期到天数转换 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int year = in.nextInt();
            int month = in.nextInt();
            int day = in.nextInt();
            int[] month_day = {31,28,31,30,31,30,31,31,30,31,30,31};
            int sum = 0;
            for (int i = 0; i < month - 1; i++) {
                sum += month_day[i];
            }
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
            if (month > 2 && isLeapYear) {
                sum += 1;
            }
            System.out.println(sum + day);
        }
    }

}
