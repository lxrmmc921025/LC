package stack;

import java.util.LinkedList;

/**
 * Created by az on 12/22/2020.
 */
public class maxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //LC239
        //!! index smaller than length by 1, k is length, array contains the length of index, so need + 1
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            //trick : calculate first int leftIndex = i - k + 1;
            //!! check empty stack;
            //   left side of window - directly minus k
            if (!stack.isEmpty() && stack.peekFirst() <= i - k) {
                stack.pollFirst();
            }
            //>= or > both are work
            while (!stack.isEmpty() && (nums[i] >= nums[stack.peekLast()])) {
                stack.pollLast();
            }
            //if ascending, remove all the digit smaller than this one
            //if descending, directly add, so i always need to add to the stack
            stack.offerLast(i);

            //when to add res? when index 0 exist, begin to add.
            //means current length >= k, current length =  index + 1
            if (i >=  k - 1) {
                res[i - k + 1] = nums[stack.peekFirst()];
            }
        }

        return res;
    }
}
