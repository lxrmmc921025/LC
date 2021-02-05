package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by az on 2/5/2021.
 */
public class validateStackSequences {
    //LC946 Validate Stack Sequences
    //T : O(n), S : O(n)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int index = 0;
        for (int i : pushed) {
            stack.offerLast(i);
            while (!stack.isEmpty() && stack.peekLast() == popped[index]) {
                stack.pollLast();
                index++;
            }
        }
        return stack.isEmpty();
    }

    //T : O(n), S : O(1)
    //https://leetcode.com/problems/validate-stack-sequences/discuss/197685/C%2B%2BJavaPython-Simulation-O(1)-Space
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        //use original pushed array as stack
        for (int x : pushed) {
            pushed[i++] = x;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                --i; ++j;
            }
        }
        return i == 0;
    }
}

