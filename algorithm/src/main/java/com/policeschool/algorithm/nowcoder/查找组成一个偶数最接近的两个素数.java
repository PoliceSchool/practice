package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/f8538f9ae3f1484fb137789dec6eedb9?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 查找组成一个偶数最接近的两个素数 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int num = in.nextInt();
            int min = Integer.MAX_VALUE;
            int res = 2;
            for (int i = 4; i <= num; i++) {
                boolean isAllSuShu = true;
                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        isAllSuShu = false;
                        break;
                    }
                }
                for (int k = 2; k < (num - i); k++) {
                    if ((num - i) % k == 0) {
                        isAllSuShu = false;
                        break;
                    }
                }
                if (isAllSuShu) {
                    if (Math.abs(num - i - i) < min) {
                        min = Math.abs(num - i - i);
                        res = i;
                    }
                }
            }
            System.out.println(res);
            System.out.print(num - res);
        }
    }
}
