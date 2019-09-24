package array;

/**
 * Created by az on 9/23/2019.
 */
public class RangeSumQuery {
    //improvement: don't need extra space, use nums instead
    int[] count;
    public RangeSumQuery(int[] nums) {
        count = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //[!!] corner case; i == 0
            count[i] = i == 0 ? nums[0]: count[i-1] + nums[i];
        }
    }
    public int sumRange(int i, int j) {
        return i == 0 ? count[j] : count[j] - count[i - 1];
    }
}
