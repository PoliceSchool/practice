package com.policeschool.algorithm.sort;

import java.util.Arrays;

/**
 * 参考实现：https://algo.itcharge.cn/01.Array/02.Array-Sort/01.Array-Bubble-Sort/
 * 冒泡排序,时间复杂度O(n*n),空间复杂度O(1)
 */
public class BubbleSort {

    /**
     * 冒泡排序,时间复杂度O(n*n),空间复杂度O(1)
     * 第一个for循环控制的是有多少次子序列排序，比如数组有n个元素则需要循环n-1次子序列排序；
     * 第二个for循环控制的是单次的子序列排序，每次子序列排序找出一个最大值；
     * 比如 {5, 2, 3, 6, 1, 4} 这个数组，历次的排序结果如下：
     * {2, 3, 5, 1, 4, 6}
     * {2, 3, 1, 4, 5, 6}
     * {2, 1, 3, 4, 5, 6}
     * {1, 2, 3, 4, 5, 6}
     * {1, 2, 3, 4, 5, 6}
     * 每多一次子序列排序，那么该次子序列排序的数组元素就少一个，
     * 因为每多一次子序列排序，就说明数组末尾就多了一个确定的数字了，
     * 所以第二个for循环 j < length - i - 1
     *
     * @param arr 未排序数组
     */
    public static void sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int max = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = max;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        sort(new int[]{5, 2, 3, 6, 1, 4});
    }
}
