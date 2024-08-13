package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/52d382c2a7164767bca2064c1c9d5361?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 密码强度等级 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            int length = str.length();
            int smallWord = 0;
            int bigWord = 0;
            int num = 0;
            int symbol = 0;
            int score = 0;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= 'a' && ch <= 'z') {
                    smallWord++;
                    continue;
                }
                if (ch >= 'A' && ch <= 'Z') {
                    bigWord++;
                    continue;
                }
                if (ch >= '0' && ch <= '9') {
                    num++;
                    continue;
                }
                symbol++;
            }
            if (length <= 4) {
                score += 5;
            } else if (length >= 5 && length <= 7) {
                score+=10;
            } else {
                score+=25;
            }
            if ((smallWord >= 1 && bigWord == 0) || (bigWord >= 1 && smallWord == 0)) {
                score+=10;
            }
            if (smallWord >= 1 && bigWord >= 1) {
                score += 20;
            }
            if (num == 1) {
                score += 10;
            }
            if (num > 1) {
                score += 20;
            }
            if (symbol == 1) {
                score += 10;
            }
            if (symbol > 1) {
                score += 25;
            }
            if (num >= 1 && symbol >= 1 && smallWord >= 1 && bigWord >= 1) {
                score += 5;
            } else if (num >= 1 && symbol >= 1 && (smallWord >= 1 || bigWord >= 1)) {
                score += 3;
            } else if (num >= 1 && (smallWord >= 1 || bigWord >= 1)) {
                score += 2;
            }
            if (score >= 90) {
                System.out.println("VERY_SECURE");
            } else if (score >= 80) {
                System.out.println("SECURE");
            } else if (score >= 70) {
                System.out.println("VERY_STRONG");
            } else if (score >= 60) {
                System.out.println("STRONG");
            } else if (score >= 50) {
                System.out.println("AVERAGE");
            } else if (score >= 25) {
                System.out.println("WEAK");
            } else if (score >= 0) {
                System.out.println("VERY_WEAK");
            }
        }
    }

}
