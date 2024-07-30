package com.policeschool.algorithm.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

// https://www.nowcoder.com/practice/69ef2267aafd4d52b250a272fd27052c?tpId=37&tqId=21281&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=1&judgeStatus=undefined&tags=&title=
public class 输入n个整数输出其中最小的k个 {

    public static void exec() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int arrSize = scanner.nextInt();
            int newArrSize = scanner.nextInt();
            int[] arr = new int[arrSize];
            for (int i = 0; i < arrSize; i++) {
                arr[i] = scanner.nextInt();
            }
            Arrays.sort(arr);
            for (int i = 0; i < newArrSize; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        exec();
    }

}
