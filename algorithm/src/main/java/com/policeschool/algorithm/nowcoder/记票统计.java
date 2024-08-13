package com.policeschool.algorithm.nowcoder;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/3350d379a5d44054b219de7af6708894?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=&tags=&title=&gioEnter=menu
 */
public class 记票统计 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            in.nextLine();
            String str = in.nextLine();
            int invalid = 0;
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            for (String item : str.split(" ")) {
                map.put(item, 0);
            }
            int m = in.nextInt();
            in.nextLine();
            str = in.nextLine();
            for (String key : str.split(" ")) {
                if (map.containsKey(key)) {
                    map.compute(key, (k, v) -> {return v + 1;});
                } else {
                    invalid++;
                }
            }
            map.forEach((k, v) -> System.out.println(k + " : " + v));
            System.out.println("Invalid : " + invalid);
        }
    }
}
