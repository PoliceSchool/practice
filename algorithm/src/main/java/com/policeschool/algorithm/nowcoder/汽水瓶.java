package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/fe298c55694f4ed39e256170ff2c205f?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 汽水瓶 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if (a == 0) {
                continue;
            }
            int count = 0;
            while (a >= 3) {
                count++;
                a -= 2;
            }
            System.out.println(a == 2 ? count + 1 : count);
        }
    }

}
