package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * https://www.nowcoder.com/practice/c4f11ea2c886429faf91decfaf6a310b?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 整形数组合并 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < a; i++) {
                set.add(in.nextInt());
            }
            int b = in.nextInt();
            for (int i = 0; i < b; i++) {
                set.add(in.nextInt());
            }
            set.forEach(System.out::print);
        }
    }

}
