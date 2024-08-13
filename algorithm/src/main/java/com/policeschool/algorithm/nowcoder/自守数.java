package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/88ddd31618f04514ae3a689e83f3ab8e?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 自守数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            int count = 0;
            for (int i = 0; i <= n; i++) {
                int sqrt = i * i;
                if (i < 10) {
                    if ((sqrt - i) % 10 == 0) {
                        count++;
                    }
                } else if (i < 100) {
                    if ((sqrt - i) % 100 == 0) {
                        count++;
                    }
                } else if (i < 1000) {
                    if ((sqrt - i) % 1000 == 0) {
                        count++;
                    }
                } else if (i < 10000) {
                    if ((sqrt - i) % 10000 == 0) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

}
