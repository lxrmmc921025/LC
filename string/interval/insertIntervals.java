package interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 3/11/2020.
 */
public class insertIntervals {
    //LC 57 hard
    //Sort
    //https://leetcode.com/problems/insert-interval/discuss/21602/Short-and-straight-forward-Java-solution
    //Important assumption : already sort
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList();
        int i = 0;
        //non-overlap, before new interval
        while(i < intervals.length && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }
        //update new interval's range
        while(i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        //non-overlap, after new interval
        while (i < intervals.length) res.add(intervals[i++]);
        return res.stream().toArray(int[][]::new);
    }
}
