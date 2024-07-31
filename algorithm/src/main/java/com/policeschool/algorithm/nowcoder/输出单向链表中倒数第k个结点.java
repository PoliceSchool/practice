package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;

public class 输出单向链表中倒数第k个结点 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            ListNode head = new ListNode(-1);
            ListNode temp = head;
            for (int i = 0; i < n; i++) {
                temp = temp.addNext(in.nextInt());
            }
            temp = head;
            int index = in.nextInt();
            index = n - index + 1;
            while (index-- > 0 && temp != null) {
                temp = temp.mpNext;
            }
            if (temp != null) {
                System.out.println(temp.mnKey);
            }
        }
    }
}

class ListNode {
    int mnKey;
    ListNode mpNext;

    public ListNode() {}

    public ListNode(int mnKey) {
        this.mnKey = mnKey;
    }

    public ListNode addNext(int nextKey) {
        mpNext = new ListNode(nextKey);
        return mpNext;
    }
}
