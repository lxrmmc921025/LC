package stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by az on 6/27/2020.
 */
public class SortNumbersInTreeStacks {
    //more like a merge sort
    public static void main(String[] args) {
        Integer[] arr = {1,5,2};
        sort(new LinkedList<Integer>(Arrays.asList(arr)));
    }

    public static void sort(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<>();
        LinkedList<Integer> s3 = new LinkedList<>();
        sort(s1, s2, s3, s1.size());
    }

    private static void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int length) {
        if (length <= 1) return;
        //first half
        int mid1 = length / 2;
        //second half
        int mid2 = length - length / 2;
        for (int i = 0; i < mid1; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        sort(s2, s3, s1, mid1);
        sort(s1, s3, s2, mid2);
        int i = 0, j = 0;
        while (i < mid1 && j < mid2) {
            //shui xiao yi shui
            if (s2.peekFirst() < s1.peekFirst()) {
                s3.offerFirst(s2.pollFirst());
                i++;
            } else {
                s3.offerFirst(s1.pollFirst());
                j++;
            }
        }
        //copy rest of data
        while (i < mid1) {
            s3.offerFirst(s2.pollFirst());
            i++;
        }
        while (j < mid2) {
            s3.offerFirst(s1.pollFirst());
            j++;
        }
        for (int index = 0; index < length; index++) {
            s1.offerFirst(s3.pollFirst());
        }
    }
}
