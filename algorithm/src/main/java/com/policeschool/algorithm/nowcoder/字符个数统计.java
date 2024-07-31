package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/eb94f6a5b2ba49c6ac72d40b5ce95f50?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 字符个数统计 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            int res = 0;
            byte[] arr = new byte[128];
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= 0 && ch <= 127 && arr[ch] == 0) {
                    arr[ch] = 1;
                    res += 1;
                }
            }
            System.out.println(res);
        }
    }
}
