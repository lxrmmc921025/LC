package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by az on 6/23/2020.
 */
public class StackWithMin {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public StackWithMin() {
        stack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
    }

    public Integer min() {
        if (minStack.isEmpty()) {
            return null;
        }
        return minStack.peekFirst();
    }

    public void push(int value) {
        stack.offerFirst(value);
        if (minStack.isEmpty() || value <= minStack.peekFirst()) {
            minStack.offerFirst(value);
        }
    }

    public Integer pop() {
        if (stack.isEmpty()) {
            return null;
        }
        Integer res = stack.pollFirst();
        if (minStack.peekFirst().equals(res)) {
            minStack.pollFirst();
        }
        return  res;
    }

    public Integer top() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peekFirst();
    }
}
