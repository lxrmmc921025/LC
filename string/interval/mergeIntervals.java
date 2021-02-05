package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by az on 3/11/2020.
 */
public class mergeIntervals {
    //LC 56 medium
    //M1: Sort
    //https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        //bug3: interval refer to a position, every time max, update its end value
        int[] interval = intervals[0];
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (interval[1] >= intervals[i][0]) {
                //bug1: don't know contain relationship between interval and intervals[i], need to compare max
                interval[1] = Math.max(intervals[i][1], interval[1]);
            } else {
                //bug2: smaller range add first, larger second
                interval = intervals[i];
                res.add(interval);
            }
        }
        //convert List to string.array : return result.toArray(new int[result.size()][]);
        return res.stream().toArray(int[][]::new);
    }

    //M2: Sort - hard to understand
    //https://leetcode.com/problems/merge-intervals/discuss/21223/Beat-98-Java.-Sort-start-and-end-respectively.
}
