package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

// https://www.nowcoder.com/practice/dd0c6b26c9e541f5b935047ff4156309?tpId=37&tqId=21324&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=1&judgeStatus=undefined&tags=&title=
public class 数组排序 {

    private static void sort(int[] arr, int ascOrDesc) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (ascOrDesc == 0) {
                    if (arr[j + 1] < arr[j]) {
                        int tmp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = tmp;
                    }
                } else {
                    if (arr[j + 1] > arr[j]) {
                        int tmp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int size = scanner.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
            }
            int ascOrDesc = scanner.nextInt();
            sort(arr, ascOrDesc);
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }

}
