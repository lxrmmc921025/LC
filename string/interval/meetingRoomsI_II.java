package string.interval;

import java.util.*;

/**
 * Created by az on 3/10/2020.
 */
public class meetingRooms {
    //LC 252 easy - 482
    //Sort
    //https://www.programcreek.com/2014/07/leetcode-meeting-rooms-java/
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }

    //LC 253 medium - 494
    //M1: sort - using 【string.array】 to implement
    //T : O(nlgn) S : O(n)
    //https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda
    //easy and smart, hard to explain and understand
    public int minMeetingRooms1(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0, endsIdx = 0;
        for (int i = 0; i < starts.length; i++) {
            //track how many meetings start before a "end" event happen
            if (starts[i] < ends[endsIdx]) rooms++;
            else endsIdx++;
        }
        return rooms;
    }

    //M2: sweep line - using 【PQ】 to implement
    //T : O(nlogn) S : O(n)
    //https://leetcode.com/problems/meeting-rooms-ii/discuss/67857/AC-Java-solution-using-min-heap
    public int minMeetingRooms2(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            //sort the intervals by start time (smaller first)
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>(intervals.length, new Comparator<int[]>() {
            @Override
            //track min end time of merged intervals
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            //first end meeting in the PQ
            int[] interval = pq.poll();
            //if cur meeting's start > first end time, then can share same room, so update end time
            if (intervals[i][0] >= interval[1]) {
                interval[1] = intervals[i][1];
            } else {
                //if cannot, require another new room
                pq.offer(intervals[i]);
            }
        }
        return pq.size();
    }

    //M3: Sweep Line -- using 【Map】 to implement
    //T : O(nlgn) S : O(n);
    //how to think : consider "sweep line" is string.array(treemap here), "event start" means + 1, "event close" means - 1,
    //track how many event is in progress at the same time.
    public int minMeetingRooms3(int[][] intervals) {
        Map<Integer, Integer> record = new TreeMap<>();
        for (int[] interval : intervals) {
            if (!record.containsKey(interval[0])) {
                record.put(interval[0], 0);
            }
            record.put(interval[0], record.get(interval[0]) + 1);
            if (!record.containsKey(interval[1])) {
                record.put(interval[1], 0);
            }
            record.put(interval[1], record.get(interval[1]) - 1);
        }
        int res = 0, sum = 0;
        for (Map.Entry<Integer, Integer> entry : record.entrySet()) {
            sum += entry.getValue();
            res = Math.max(res, sum);
        }
        return res;
    }
}
