package com.policeschool.algorithm.nowcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/c1f9561de1e240099bdb904765da9ad0?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 字符统计 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            HashMap<Character, Node> map = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (map.containsKey(ch)) {
                    map.compute(ch, (k, v) -> {v.add(); return v;});
                } else {
                    map.put(ch, new Node(1, ch));
                }
            }
            ArrayList<Node> list = new ArrayList<>(map.values());
            Collections.sort(list);
            list.forEach(item -> System.out.print(item.ch));
        }
    }

    static class Node implements Comparable<Node> {
        int count;
        char ch;

        public Node(){}

        public Node(int count, char ch) {
            this.count = count;
            this.ch = ch;
        }

        public void add() {
            this.count++;
        }

        public int compareTo(Node node) {
            if (this.count < node.count) {
                return 1;
            } else if (this.count > node.count) {
                return -1;
            }
            if (this.ch > node.ch) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
