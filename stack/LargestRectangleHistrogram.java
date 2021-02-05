package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by az on 7/8/2020.
 */
public class LargestRectangleHistrogram {
    //LC84
    public int largest(int[] array) {
        int res = 0;
        //store ascending order
        Deque<Integer> stack = new LinkedList<Integer>();
        //comment 1 : suppose only one height, to calculate it, i-max is length
        for (int i = 0; i <= array.length; i++) {
            int cur = i == array.length ? 0 : array[i];
            //comment 2: here ">=" or ">", both are work
            while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
                int height = array[stack.pollFirst()];
                //comment 3: poll above, so here might be empty, need to check
                //comment 4: left is stack.peekFirst() + 1
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                res = Math.max(res, height * (i - left));
            }
            stack.offerFirst(i);
        }
        return res;
    }

    //Sol : traversal from center
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            for (int j = i; j >= 0; j--) {
                if (heights[j] < height) {
                    height = heights[j];
                }
                int area = (i - j + 1) * height;
                max = Math.max(max, area);
            }
        }
        return max;
    }
}
