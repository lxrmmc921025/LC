package stack;

import java.util.LinkedList;

/**
 * Created by az on 6/23/2020.
 */
public class QueueByTwoStack {
    //LC 232
    private LinkedList<Integer> in;
    private LinkedList<Integer> out;

    public QueueByTwoStack() {
        in = new LinkedList<Integer>();
        out = new LinkedList<Integer>();
    }

    public Integer poll() {
        move();
        return out.isEmpty() ? null : out.pollFirst();
    }

    public void offer(int value) {
        in.offerFirst(value);
    }

    public Integer peek() {
        move();
        return out.isEmpty() ? null : out.peekFirst();
    }

    private void move() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.offerFirst(in.pollFirst());
            }
        }
    }

    public int size() {
        return in.size() + out.size();
    }

    public boolean isEmpty() {
        return in.size() == 0 && out.size() == 0;
    }
}
