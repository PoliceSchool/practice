package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/a30bbc1a0aca4c27b86dd88868de4a4a?tpId=37&tags=&title=&difficulty=1&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
 */
public class 截取字符串 {

    public static void exec() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            int k = scanner.nextInt();
            // 这里要额外加多一个nextLine，因为nextInt不会消费掉换行符，所以下次的nextLine会读到这次的换行符，而下次的
            // nextInt会读到下次的字符串导致报错
            scanner.nextLine();
            char[] chars = str.toCharArray();
            for (int i = 0; i < k; i++) {
                System.out.print(chars[i]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 测试用例: abABCcDEF 6
        exec();
    }

}
