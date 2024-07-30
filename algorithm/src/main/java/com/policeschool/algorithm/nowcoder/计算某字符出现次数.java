package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/a35ce98431874e3a820dbe4b2d0508b1?tpId=37&tqId=21225&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=undefined&tags=&title=
 */
public class 计算某字符出现次数 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            char item = in.nextLine().charAt(0);
            char[] arr = str.toCharArray();
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (item > '0' && item < '9') {
                    if (arr[i] == item) {
                        count++;
                    }
                } else {
                    if (arr[i] == item || (arr[i] - item) == 'A' - 'a' || (arr[i] - item) == 'a' - 'A') {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }
}
