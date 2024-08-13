package com.policeschool.algorithm.nowcoder;

import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/9566499a2e1546c0a257e885dfdbf30d?tpId=37&tqId=21277&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D2%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=2&judgeStatus=undefined&tags=&title=
 */
public class 表达式求值 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String expression = scanner.nextLine();
            Stack<Integer> numStack = new Stack<>();
            Stack<Character> operatorStack = new Stack<>();
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);
                if (Character.isDigit(ch)) {
                    int num = 0;
                    while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                        num = 10 * num + (expression.charAt(i) - '0');
                        ++i;
                    }
                    --i;
                    numStack.push(num);
                    continue;
                }
                if (operatorStack.isEmpty() || ch == '(') {
                    operatorStack.push(ch);
                    continue;
                }
                if (ch == ')') {
                    while (operatorStack.peek() != '(') {
                        numStack.push(exec(numStack.pop(), numStack.pop(), operatorStack.pop()));
                    }
                    operatorStack.pop();
                    continue;
                }
                if (order(ch) <= order(operatorStack.peek())) {
                    while (!operatorStack.isEmpty() && order(ch) <= order(operatorStack.peek())) {
                        numStack.push(exec(numStack.pop(), numStack.pop(), operatorStack.pop()));
                    }
                }
                operatorStack.push(ch);
            }
            while(!operatorStack.isEmpty()) {
                numStack.push(exec(numStack.pop(), numStack.pop(), operatorStack.pop()));
            }
            System.out.println(numStack.pop());
        }
    }

    private static int order(Character ch) {
        if (ch == '(') {
            return 0;
        }
        if (ch == '+' || ch == '-') {
            return 1;
        }
        if (ch == '*' || ch == '/') {
            return 2;
        }
        return 0;
    }

    private static Integer exec(Integer second, Integer first, Character operator) {
        if ('+' == operator) {
            return first + second;
        }
        if ('-' == operator) {
            return first - second;
        }
        if ('*' == operator) {
            return first * second;
        }
        if ('/' == operator) {
            return first / second;
        }
        return 0;
    }

}
