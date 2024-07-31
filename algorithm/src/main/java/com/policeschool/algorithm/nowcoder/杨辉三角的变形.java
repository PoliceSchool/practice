package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

// https://www.nowcoder.com/practice/8ef655edf42d4e08b44be4d777edbf43?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
public class 杨辉三角的变形 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            // 第一行和第二行如图没有偶数
            if (n < 3) {
                System.out.println(-1);
                continue;
            }
            // 根据规律奇数行第2个数就是偶数
            if (n % 2 == 1) {
                System.out.println(2);
                continue;
            }
            // 接下来只需要分析偶数行；按照下面的分布规律，偶数行的第一个偶数位置不是3就是4；分析可得偶数行除以2的结果如果是奇数那么就是4，否则就是3
            // 1 5 15 20 （出现在第4个位置）
            // 1 6 21 40
            // 1 7 28 67 （出现在第3个位置）
            // 1 8 36 102
            // 1 9 45 146 （出现在第4个位置）
            // 1 10 55 200
            // 1 11 66 265 （出现在第3个位置）
            n /= 2;
            if (n % 2 == 1) {
                System.out.println(4);
            } else {
                System.out.println(3);
            }
        }
    }
}
