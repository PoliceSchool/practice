package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/637062df51674de8ba464e792d1a0ac6?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 表示数字 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            StringBuilder sb = new StringBuilder();
            boolean lastTimeIsNum = false;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    if (!lastTimeIsNum) {
                        sb.append('*');
                    }
                    lastTimeIsNum = true;
                } else {
                    if (i > 0) {
                        char lastChar = str.charAt(i - 1);
                        if (lastChar >= '0' && lastChar <= '9') {
                            sb.append('*');
                        }
                    }
                    lastTimeIsNum = false;
                }
                sb.append(ch);
            }
            if (str.charAt(str.length() - 1) >= '0' &&
                    str.charAt(str.length() - 1) <= '9') {
                sb.append('*');
            }
            System.out.println(sb.toString());
        }
    }

}
