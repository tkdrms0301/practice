package SortAlgorithm;

import java.util.Arrays;

public class ArraysSort extends Sort {
    public ArraysSort() {

    }

    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        Arrays.sort(a);
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}