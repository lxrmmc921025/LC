package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * Created by az on 12/24/2020.
 */
public class NestedIterator implements Iterator<Integer> {
    //LC341
    //Tutorial - youtube.com/watch?v=R2dohSHOWXQ&ab_channel=HuifengGuan
    //https://leetcode.com/problems/flatten-nested-list-iterator/discuss/?currentPage=1&orderBy=most_votes&query=
    Deque<NestedInteger> stack = new ArrayDeque<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        prepareStack(nestedList);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            //has to be getList();
            List<NestedInteger> list = stack.pop().getList();     //getList is belong to the interface
            prepareStack(list);
        }
        return !stack.isEmpty();
    }

    private void prepareStack(List<NestedInteger> nestedList) {
        //error1 : reverse order
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }
}
