package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/ae809795fca34687a48b172186e3dafe?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 数字颠倒 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            for (int i = str.length() - 1; i >= 0; i--) {
                System.out.print(str.charAt(i));
            }
        }
    }
}
