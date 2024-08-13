package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/64f6f222499c4c94b338e588592b6a62?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 记负均正II {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum = 0;
        int negetiveCount = 0;
        int count = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            count++;
            int a = in.nextInt();
            if (a >= 0) {
                sum += a;
            } else {
                negetiveCount++;
            }
        }
        System.out.println(negetiveCount);
        System.out.println((count - negetiveCount) == 0 ? 0.0 : String.format("%.1f", sum*1.0 / (count - negetiveCount)));
    }

}
