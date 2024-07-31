package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/649b210ef44446e3b1cd1be6fa4cab5e?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 蛇形矩阵 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int rowStart  = 0;
            int count = 0;
            for (int i = 0; i < a; i++) {
                rowStart += i;
                int columnStart = rowStart;
                for (int j = 0; j < a - i; j++) {
                    columnStart += (j+1);
                    System.out.print(columnStart + " ");
                    columnStart += count;
                }
                count++;
                System.out.println();
            }
        }
    }

}
