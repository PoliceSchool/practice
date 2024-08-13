package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/6abde6ffcc354ea1a8333836bd6876b8?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 记负均正 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            int negetiveCount = 0;
            int sum = 0;
            int positiveCount = 0;
            for (int i = 0; i < n; i++) {
                int num = in.nextInt();
                if (num > 0) {
                    sum += num;
                    positiveCount++;
                } else if (num < 0) {
                    negetiveCount++;
                }
            }
            System.out.println(negetiveCount + " " + (positiveCount == 0 ? 0.0 : String.format("%.1f", sum*1.0/positiveCount, 1)));
        }
    }

}
