package SortAlgorithm;

import java.lang.Comparable;

public class BubbleSort extends Sort {
    public BubbleSort() {

    }

    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (!isless(a[j], a[j + 1])) {
                    swap(a, j, j + 1);
                }
            }
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}