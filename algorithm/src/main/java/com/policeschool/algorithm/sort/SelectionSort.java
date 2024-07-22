package com.policeschool.algorithm.sort;

import java.util.Arrays;

/**
 * 参考实现：https://algo.itcharge.cn/01.Array/02.Array-Sort/02.Array-Selection-Sort/
 * 选择排序,时间复杂度O(n*n),空间复杂度O(1)
 */
public class SelectionSort {

    /**
     * 选择排序,时间复杂度O(n*n),空间复杂度O(1)
     * 第一个for循环控制的是有多少次子序列排序，比如数组有n个元素则需要循环n-1次子序列排序；
     * 第二个for循环控制的是单次的子序列排序，每次子序列排序找出一个最小值所在的数组下标，然后将最小值与i位置上的元素交换；
     * 比如 {5, 2, 3, 6, 1, 4} 这个数组，历次的排序结果如下：
     * 1, 2, 3, 6, 5, 4
     * 1, 2, 3, 6, 5, 4
     * 1, 2, 3, 6, 5, 4
     * 1, 2, 3, 4, 5, 6
     * 1, 2, 3, 4, 5, 6
     *
     * @param arr 未排序数组
     */
    public static void sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        sort(new int[]{5, 2, 3, 6, 1, 4});
    }
}
