package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/253986e66d114d378ae8de2e6c4577c1?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=1&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 提取不重复的整数 {

    public static void exec() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int[] flagArr = new int[10];
            int result = 0;
            while(a>0) {
                int num = a % 10;
                if (flagArr[num] == 0) {
                    result = (result*10) + num;
                    flagArr[num] = 1;
                }
                a /= 10;
            }
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        // 测试用例：9876673
        exec();
    }

}
