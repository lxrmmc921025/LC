package stack;

import java.util.LinkedList;

/**
 * Created by az on 12/10/2020.
 */
public class calculator {
    public static void main(String[] args) {
        int[] test = {7,5,1,4};
        int res = calculate("(1-(4+5+2)-3)+(6+8)");
        System.out.print(test);
    }
    //LC 224
    //https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack -- each iteration calculator character
    //https://leetcode.com/problems/basic-calculator/discuss/62362/JAVA-Easy-Version-To-Understand!!!!!  -- each iteration calculator number

    /*
      comment - 1. number end, need to reset number, and calculate res(only "(", must follow a sign, don't need to reset)
                2. sign end, need to reset sign (only ")"", must follow a number, don't need to reset)
                3. what stack contains : A - prevous number B - previous sign,
                   so when pop out (")"), first calculate sign, then calculate number
                4. when end the string forloop, calculate num if num is not empty
     */
    public static int calculate(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int res = 0, number = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int) (c - '0');
            } else if (c == '+') {
                res += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                res += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                stack.offerLast(res);
                stack.offerLast(sign);
                // for "-(1 + 1)", need to reset sign
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res += sign * number;
                number = 0;
                //see comment 3
                res *= stack.pollLast();
                res += stack.pollLast();
            }
        }
        //for "1+1", if number is not empty, need to calculate
        if (number != 0) {
            res += sign * number;
        }
        return res;
    }

    //LC227
    //https://leetcode.com/problems/basic-calculator-ii/discuss/63003/Share-my-java-solution
    public int calculateII(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int res = 0, number = 0;
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (int)(c - '0');
            }
            //corner case 1: character can be empty
            //corner case 2: if last character, need to add to the stack
            if (!Character.isDigit(c) && c != ' '|| i == s.length() - 1) {
                //trick is we need to record the last sign, so here checks the sign instead of c
                if (sign == '+') {
                    stack.offerLast(number);
                } else if (sign == '-') {
                    stack.offerLast(-number);
                } else if (sign == '*') {
                    stack.offerLast(stack.pollLast() * number);
                } else if (sign == '/') {
                    stack.offerLast(stack.pollLast() / number);
                }
                sign = s.charAt(i);
                number = 0;
            }
        }

        while (!stack.isEmpty()) {
            //can use num instead of res, because last digit have to reset num to 0
            res += stack.pollLast();
        }

        return res;
    }


}
