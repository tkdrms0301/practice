package SortAlgorithm;

import java.lang.Comparable;

public class SelectionSort extends Sort {
    public SelectionSort() {

    }

    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (isless(a[j], a[min]))
                    min = j;
            }
            swap(a, i, min);
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}