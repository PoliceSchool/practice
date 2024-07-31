package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/7960b5038a2142a18e27e4c733855dac?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 简单密码 {
    private static char translate(char ch) {
        if (ch == 'a' || ch == 'b' || ch == 'c') {
            return '2';
        }
        if (ch == 'd' || ch == 'e' || ch == 'f') {
            return '3';
        }
        if (ch == 'g' || ch == 'h' || ch == 'i') {
            return '4';
        }
        if (ch == 'j' || ch == 'k' || ch == 'l') {
            return '5';
        }
        if (ch == 'm' || ch == 'n' || ch == 'o') {
            return '6';
        }
        if (ch == 'p' || ch == 'q' || ch == 'r' || ch == 's') {
            return '7';
        }
        if (ch == 't' || ch == 'u' || ch == 'v') {
            return '8';
        }
        if (ch == 'w' || ch == 'x' || ch == 'y' || ch == 'z') {
            return '9';
        }
        return ch;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= 'A' && ch < 'Z') {
                    ch = (char)(ch - 'A' + 'a' + 1);
                    System.out.print(ch);
                } else if (ch == 'Z') {
                    System.out.print('a');
                } else {
                    System.out.print(translate(ch));
                }
            }
        }
    }
}
