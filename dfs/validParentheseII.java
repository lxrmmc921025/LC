package dfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by az on 10/23/2019.
 */
public class validParentheseII {
    private static final char[] PS = new char[] {'(', ')', '[', ']','{','}'};
    public List<String> validParentheses(int l, int m, int n) {
        int[] remain = new int[] {l, l, m, m, n, n};
        int total = 2 * (m + n + l);
        StringBuilder sb = new StringBuilder();
        //stack is a global variable to store parenthesis
        Deque<Character> stack = new LinkedList<Character>();
        List<String> res = new ArrayList<>();
        dfs(sb, stack, remain, total, res);
        return res;
    }

    public void dfs(StringBuilder sb, Deque<Character> stack, int[] remain, int total, List<String> res) {
        if (sb.length() == total) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < remain.length; i++) {
            //left parenthesis
            if (i % 2 == 0) {
                if (remain[i] > 0) {
                    sb.append(PS[i]);
                    stack.offerFirst(PS[i]);
                    remain[i]--;
                    dfs(sb, stack, remain, total, res);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else {
                if(!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
                    sb.append(PS[i]);
                    stack.pollFirst();
                    remain[i]--;
                    dfs(sb, stack, remain, total, res);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.offerFirst(PS[i - 1]);
                    remain[i]++;
                }
            }
        }
    }
}
