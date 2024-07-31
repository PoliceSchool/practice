package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/48b3cb4e3c694d9da5526e6255bb73c3?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 句子逆序 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] result = str.split(" ");
        for (int index = result.length - 1; index >= 0; index--) {
            System.out.print(result[index] + " ");
        }
    }
}
