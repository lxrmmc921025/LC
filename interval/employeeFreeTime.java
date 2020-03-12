package interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by az on 3/11/2020.
 */
public class employeeFreeTime {
    //LC 759 hard
    //M1: Sort
    //https://leetcode.com/problems/employee-free-time/discuss/113134/Simple-Java-Sort-Solution-Using-(Priority-Queue)-or-Just-ArrayList
    public List<Interval> employeeFreeTime1(List<Interval> avail) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        avail.forEach( e -> pq.add(e));

        Interval tmp = pq.poll();
        while (!pq.isEmpty()) {
            if (tmp.end < pq.peek().start) {
                res.add(new Interval(tmp.end, pq.peek().start));
                tmp = pq.poll();
            } else {
                tmp = tmp.end < pq.peek().end ? pq.peek() : tmp;
                pq.poll();
            }
        }
        return res;
    }

    //M2: Sweep Line
    //https://leetcode.com/problems/employee-free-time/discuss/113134/Simple-Java-Sort-Solution-Using-(Priority-Queue)-or-Just-ArrayList
    public List<Interval> employeeFreeTime2(List<List<Interval>> avails) {
        List<Interval> res = new ArrayList<>();
        List<Interval> timeLine = new ArrayList<>();
        avails.forEach(e -> timeLine.addAll(e));
        Collections.sort(timeLine, ((a, b) -> a.start - b.start));

        Interval temp = timeLine.get(0);
        for(Interval each : timeLine) {
            if(temp.end < each.start) {
                res.add(new Interval(temp.end, each.start));
                temp = each;
            }else{
                temp = temp.end < each.end ? each : temp;
            }
        }
        return res;
    }

    class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
