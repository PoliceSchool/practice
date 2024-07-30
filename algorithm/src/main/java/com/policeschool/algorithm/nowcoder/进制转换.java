package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

// https://www.nowcoder.com/practice/8f3df50d2b9043208c5eed283d1d4da6?tpId=37&tqId=21228&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=undefined&tags=&title=
public class 进制转换 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            int res = 0;
            for (int i = a.length() - 1; i > 1; i--) {
                char ch = a.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    res += (Math.pow(16, a.length() - i - 1) * (ch - '0'));
                }
                if (ch >= 'A' && ch <= 'F') {
                    res += (Math.pow(16, a.length() - i - 1) * (ch - 'A' + 10));
                }
            }
            System.out.println(res);
        }
    }
}
