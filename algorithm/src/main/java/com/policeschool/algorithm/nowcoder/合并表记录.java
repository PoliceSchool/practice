package com.policeschool.algorithm.nowcoder;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

// https://www.nowcoder.com/practice/de044e89123f4a7482bd2b214a685201?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D1%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
public class 合并表记录 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int loop = in.nextInt();
            Map<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < loop; i++) {
                int first = in.nextInt();
                int second = in.nextInt();
                Integer v = map.get(first);
                if (Objects.isNull(v)) {
                    v = 0;
                }
                v += second;
                map.put(first, v);
            }
            map.forEach((k,v) -> System.out.println(k + " " + v));
        }
    }


}
