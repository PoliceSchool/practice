package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

// https://www.nowcoder.com/practice/d9162298cb5a437aad722fccccaae8a7?tpId=37&tqId=21227&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=undefined&tags=&title=
public class 字符串分隔 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            if (str.equals("")) {
                continue;
            }
            char[] arr = str.toCharArray();
            int len = arr.length;
            int loop = len / 8;
            for (int i = 0; i < loop; i++) {
                for (int j = i * 8; j < i * 8 + 8; j++) {
                    System.out.print(arr[j]);
                }
                System.out.println();
            }
            if (len % 8 != 0) {
                for (int i = loop * 8; i < loop * 8 + 8; i++) {
                    if (i < len) {
                        System.out.print(arr[i]);
                    } else {
                        System.out.print('0');
                    }
                }
                System.out.println();
            }
        }
    }
}
