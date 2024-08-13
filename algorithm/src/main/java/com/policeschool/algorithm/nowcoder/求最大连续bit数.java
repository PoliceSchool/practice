package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/4b1658fd8ffb4217bc3b7e85a38cfaf2?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 求最大连续bit数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int max = 0;
            int sub_max = 1;
            boolean lastTimeIsOne = false;
            while(a>0) {
                if (a%2==1) {
                    if (lastTimeIsOne) {
                        sub_max++;
                    }
                    lastTimeIsOne = true;
                } else {
                    max = max > sub_max ? max : sub_max;
                    lastTimeIsOne = false;
                    sub_max = 1;
                }
                a/=2;
            }
            System.out.println(max > sub_max ? max : sub_max);
        }
    }

}
