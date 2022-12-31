package SortAlgorithm;

import java.util.*;
import java.lang.Comparable;

public class CollectionsSort extends Sort {
    ArrayList<Comparable> array;

    public CollectionsSort() {
        array = new ArrayList<Comparable>();
    }

    public void sort(Comparable[] a) {
        add(a);
        start = System.currentTimeMillis();
        Collections.sort(array);
        end = System.currentTimeMillis();
        setTime(end - start);
    }

    public void add(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            array.add(a[i]);
        }
    }
}
